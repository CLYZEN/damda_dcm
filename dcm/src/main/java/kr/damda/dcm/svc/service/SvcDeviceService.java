package kr.damda.dcm.svc.service;

import kr.damda.dcm.dto.request.RegisterRequestDto;
import kr.damda.dcm.dto.response.RegisterResponseDto;

public interface SvcDeviceService {
    RegisterResponseDto registerDevice(RegisterRequestDto registerRequestDto);

}
