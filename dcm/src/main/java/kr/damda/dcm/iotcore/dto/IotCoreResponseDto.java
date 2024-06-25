package kr.damda.dcm.iotcore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class IotCoreResponseDto {

    private Integer code;

    private String message;
}
