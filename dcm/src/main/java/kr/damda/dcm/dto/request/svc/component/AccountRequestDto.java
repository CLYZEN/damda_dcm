package kr.damda.dcm.dto.request.svc.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonRootName(value = "account")
public class AccountRequestDto {

    @NotNull
    @JsonProperty(value = "login_id")
    private String loginId;

    @JsonProperty(value = "group_id")
    private String groupId;
}
