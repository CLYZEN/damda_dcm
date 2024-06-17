package kr.damda.dcm.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.damda.dcm.dto.response.component.AccountResponseDto;
import kr.damda.dcm.dto.response.component.ResultResponseDto;
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
}
