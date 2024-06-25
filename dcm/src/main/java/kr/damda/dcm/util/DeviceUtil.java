package kr.damda.dcm.util;

import kr.damda.dcm.dto.request.svc.component.DeviceRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DeviceUtil {

    public String buildDeviceId(DeviceRequestDto deviceRequestDto) {
        return deviceRequestDto.getMaker() + "-" + deviceRequestDto.getModel() + "-"
            + deviceRequestDto.getSerialNumber();
    }
}
