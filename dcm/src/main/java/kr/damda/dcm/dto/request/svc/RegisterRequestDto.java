package kr.damda.dcm.dto.request.svc;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.damda.dcm.dto.request.svc.component.AccountRequestDto;
import kr.damda.dcm.dto.request.svc.component.DeviceRequestDto;
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
