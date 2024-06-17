package kr.damda.dcm.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.damda.dcm.dto.request.component.AccountRequestDto;
import kr.damda.dcm.dto.request.component.DeviceRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterRequestDto {

    @JsonProperty(value = "account")
    private AccountRequestDto accountDto;

    @JsonProperty(value = "device")
    private DeviceRequestDto deviceDto;

}
