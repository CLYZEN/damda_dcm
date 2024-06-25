package kr.damda.dcm.dto.request.iot.component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeviceRequest {
    private DeviceMessage message;
    private String sequence;
    private String device_id;
    private String session_id;
}
