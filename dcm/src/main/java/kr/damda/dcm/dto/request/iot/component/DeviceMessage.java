package kr.damda.dcm.dto.request.iot.component;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeviceMessage {
    private String o;
    private List<DeviceElement> e;
}
