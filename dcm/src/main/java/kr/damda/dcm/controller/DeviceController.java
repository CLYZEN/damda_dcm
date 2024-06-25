package kr.damda.dcm.controller;

import jakarta.validation.Valid;
import kr.damda.dcm.dto.request.svc.RegisterRequestDto;
import kr.damda.dcm.dto.response.svc.RegisterResponseDto;
import kr.damda.dcm.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DeviceController {

    @Qualifier("deviceServiceImpl")
    private final DeviceService deviceService;

    @PostMapping("/api/1.0/device/register")
    public RegisterResponseDto registerDevice(
        @RequestBody @Valid RegisterRequestDto registerRequestDto) {

        log.info(registerRequestDto.toString());



        return deviceService.registerDevice(registerRequestDto);
    }

}
