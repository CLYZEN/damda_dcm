package kr.damda.dcm.iotcore.service;

import kr.damda.dcm.dto.request.svc.RegisterRequestDto;
import kr.damda.dcm.dto.response.svc.RegisterResponseDto;
import kr.damda.dcm.dto.response.svc.component.ResultResponseDto;
import kr.damda.dcm.iot.repository.IotDeviceModelRepository;
import kr.damda.dcm.service.DeviceService;
import kr.damda.dcm.util.DeviceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.AttributePayload;
import software.amazon.awssdk.services.iot.model.CreateKeysAndCertificateResponse;
import software.amazon.awssdk.services.iot.model.CreateThingResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class IotCoreDeviceService implements DeviceService {

    private final ThingService thingService;
    private final CertificateService certificateService;
    private final AttrService attrService;
    private final PolicyService policyService;
    private final IotDeviceModelRepository iotDeviceModelRepository;
    private final DeviceUtil deviceUtil;

    public RegisterResponseDto registerDevice(RegisterRequestDto registerRequestDto) {
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        IotClient iotClient = null;
        String deviceId = null;
        CreateKeysAndCertificateResponse createKeysResponse = null;
        String policyName = null;

        try {
            log.info("Iot Core Register Start");
            deviceId = deviceUtil.buildDeviceId(registerRequestDto.getDeviceDto());
            String deviceType = iotDeviceModelRepository.findByManufacturerAndModelName(
                registerRequestDto.getDeviceDto().getMaker(),
                registerRequestDto.getDeviceDto().getModel()).getDeviceType();

            iotClient = createIotClient();

            if (!thingService.validationThing(deviceId, iotClient)) {
                ResultResponseDto resultResponseDto = new ResultResponseDto();
                resultResponseDto.setCode(400);
                resultResponseDto.setDescription(deviceId + " already exists");
                registerResponseDto.setResultDto(resultResponseDto);
                return registerResponseDto;
            }

            CreateThingResponse createThingResponse = thingService.createThing(deviceId, iotClient);
            createKeysResponse = certificateService.attachKey(iotClient, deviceId);

            AttributePayload attributePayload = attrService.getAttrPayload(registerRequestDto.getAccountDto());
            attrService.setThingAttr(attributePayload, deviceId, iotClient);
            log.info("-------------------------------------------------");
            log.info("| 사물, 인증서 등록 완료");
            log.info("| 사물 이름: {}", deviceId);
            log.info("-------------------------------------------------");

            /* 정책 생성 */
            policyName = policyService.createPolicy(deviceId, deviceType, iotClient);
            /* 정책 등록 */
            policyService.attachPolicyToCertificate(policyName, createKeysResponse.certificateArn(), iotClient);

        } catch (Exception e) {
            rollbackRegistration(iotClient, deviceId, createKeysResponse, policyName);
            ResultResponseDto resultResponseDto = new ResultResponseDto();
            resultResponseDto.setCode(500);
            resultResponseDto.setDescription(e.getMessage());
            registerResponseDto.setResultDto(resultResponseDto);
            return registerResponseDto;
        }

        ResultResponseDto resultResponseDto = new ResultResponseDto();
        resultResponseDto.setCode(0);
        registerResponseDto.setResultDto(resultResponseDto);

        return registerResponseDto;
    }


    private void rollbackRegistration(IotClient iotClient, String deviceId,
        CreateKeysAndCertificateResponse createKeysResponse,
        String policyName) {
        try {
            if (policyName != null) {
                policyService.detachPolicyFromCertificate(policyName, createKeysResponse.certificateArn(), iotClient);
                policyService.deletePolicy(policyName, iotClient);
            }
            if (createKeysResponse != null) {
                certificateService.detachCertificate(iotClient, deviceId, createKeysResponse.certificateArn());
                certificateService.deleteCertificate(iotClient, createKeysResponse.certificateId());
            }
            if (deviceId != null) {
                thingService.deleteThing(iotClient, deviceId);
            }
        } catch (Exception rollbackException) {
            log.error("Failed to rollback device registration: {}", rollbackException.getMessage());
        }
    }

    private IotClient createIotClient() {
        return IotClient.builder()
            .region(Region.AP_NORTHEAST_2)
            .credentialsProvider(DefaultCredentialsProvider.create())
            .build();
    }
}
