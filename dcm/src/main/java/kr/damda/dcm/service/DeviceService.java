package kr.damda.dcm.service;

import kr.damda.dcm.dto.request.RegisterRequestDto;
import kr.damda.dcm.dto.request.component.DeviceRequestDto;
import kr.damda.dcm.dto.response.RegisterResponseDto;

public interface DeviceService {

    RegisterResponseDto registerDevice(RegisterRequestDto registerRequestDto);

}
