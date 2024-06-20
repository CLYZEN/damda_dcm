package kr.damda.dcm.svc.entity.id;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
public class SvcGroupDeviceId implements Serializable {

    private Long groupId;

    private Long deviceId;

    public SvcGroupDeviceId() {
    }

    public SvcGroupDeviceId(Long groupId, Long deviceId) {
        this.groupId = groupId;
        this.deviceId = deviceId;
    }
}
