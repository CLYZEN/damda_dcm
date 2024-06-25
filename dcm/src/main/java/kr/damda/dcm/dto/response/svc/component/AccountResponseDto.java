package kr.damda.dcm.dto.response.svc.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountResponseDto {

    @JsonProperty(value = "group_id")
    private String groupId;
}
