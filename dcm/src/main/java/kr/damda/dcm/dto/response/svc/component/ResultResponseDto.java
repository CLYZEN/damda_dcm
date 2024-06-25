package kr.damda.dcm.dto.response.svc.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResultResponseDto {

    private Integer code;

    private String description;

}
