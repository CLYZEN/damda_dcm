package kr.damda.dcm.dto.response.svc;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.damda.dcm.dto.response.svc.component.AccountResponseDto;
import kr.damda.dcm.dto.response.svc.component.ResultResponseDto;
import kr.damda.dcm.iotcore.dto.CertificatedDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class RegisterResponseDto {

    @JsonProperty(value = "result")
    private ResultResponseDto resultDto;

    @JsonProperty(value = "account")
    private AccountResponseDto accountDto;

    @JsonProperty(value = "certificated")
    private CertificatedDto certificatedDto;
}
