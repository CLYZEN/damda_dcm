package kr.damda.dcm.service;

import kr.damda.dcm.dto.request.RegisterRequestDto;
import kr.damda.dcm.dto.response.RegisterResponseDto;
import kr.damda.dcm.svc.service.SvcDeviceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
