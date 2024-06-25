package kr.damda.dcm.iotcore.service;

import kr.damda.dcm.dto.request.svc.RegisterRequestDto;
import kr.damda.dcm.dto.response.svc.RegisterResponseDto;
import kr.damda.dcm.dto.response.svc.component.ResultResponseDto;
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
    private final DeviceUtil deviceUtil;

    public RegisterResponseDto registerDevice(RegisterRequestDto registerRequestDto) {
        log.info("Iot Core Register Start");
        String deviceId = deviceUtil.buildDeviceId(registerRequestDto.getDeviceDto());
        IotClient iotClient = createIotClient();
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();

        if (!thingService.validationThing(deviceId, iotClient)) {
            ResultResponseDto resultResponseDto = new ResultResponseDto();
            resultResponseDto.setCode(400);
            resultResponseDto.setDescription(deviceId + "already exist");
            registerResponseDto.setResultDto(resultResponseDto);
            return registerResponseDto;
        }

        CreateThingResponse createThingResponse = thingService.createThing(deviceId, iotClient);
        CreateKeysAndCertificateResponse createKeysResponse = certificateService.attachKey(
            iotClient, deviceId);

        AttributePayload attributePayload = attrService.getAttrPayload(
            registerRequestDto.getAccountDto());
        attrService.setThingAttr(attributePayload, deviceId, iotClient);
        log.info("-------------------------------------------------");
        log.info("| 사물, 인증서 등록 완료");
        log.info("| 사물 이름: {}", deviceId);
        log.info("-------------------------------------------------");

        //TODO
        return null;
    }

    private IotClient createIotClient() {
        return IotClient.builder()
            .region(Region.AP_NORTHEAST_2)
            .credentialsProvider(DefaultCredentialsProvider.create())
            .build();
    }
}
