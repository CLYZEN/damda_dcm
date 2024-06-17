package kr.damda.dcm.controller;

import jakarta.validation.Valid;
import kr.damda.dcm.dto.request.RegisterRequestDto;
import kr.damda.dcm.dto.response.component.AccountResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {

    @PostMapping("api/1.0/device/register")
    public AccountResponseDto registerDevice(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        return new AccountResponseDto();
    }

}
