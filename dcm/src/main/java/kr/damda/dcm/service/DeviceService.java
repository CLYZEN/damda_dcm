package kr.damda.dcm.service;

import kr.damda.dcm.dto.request.svc.RegisterRequestDto;
import kr.damda.dcm.dto.response.svc.RegisterResponseDto;

public interface DeviceService {

    RegisterResponseDto registerDevice(RegisterRequestDto registerRequestDto);

}
