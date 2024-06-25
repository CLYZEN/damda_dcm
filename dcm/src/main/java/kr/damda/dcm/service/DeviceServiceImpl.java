package kr.damda.dcm.service;

import kr.damda.dcm.dto.request.svc.RegisterRequestDto;
import kr.damda.dcm.dto.response.svc.RegisterResponseDto;
import kr.damda.dcm.iotcore.dto.IotCoreResponseDto;
import kr.damda.dcm.iotcore.service.AttrService;
import kr.damda.dcm.iotcore.service.CertificateService;
import kr.damda.dcm.iotcore.service.ThingService;
import kr.damda.dcm.svc.service.SvcDeviceServiceImpl;
import kr.damda.dcm.util.DeviceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.AttributePayload;
import software.amazon.awssdk.services.iot.model.CreateKeysAndCertificateResponse;
import software.amazon.awssdk.services.iot.model.CreateThingResponse;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    @Qualifier("svcDeviceServiceImpl")
    private final SvcDeviceServiceImpl svcDeviceService;


    @Override
    public RegisterResponseDto registerDevice(RegisterRequestDto registerRequestDto) {

        RegisterResponseDto svcRegisterResponseDto = svcDeviceService.registerDevice(registerRequestDto);
        return svcRegisterResponseDto;
    }



}
