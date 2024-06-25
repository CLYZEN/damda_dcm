package kr.damda.dcm.iot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import kr.damda.dcm.dto.request.svc.component.DeviceRequestDto;
import kr.damda.dcm.util.DeviceUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "iot_device_profile")
public class IotDeviceProfile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SUBS_NO", nullable = false, length = 255)
    private String subsNo;

    @Column(name = "DEVICE_ID", nullable = false, length = 50)
    private String deviceId;

    @Column(name = "DISPLAY_NAME", length = 50)
    private String displayName;

    @Column(name = "DISPLAY_IMG", length = 50)
    private String displayImg;

    @Column(name = "SERVICE_NO", length = 255)
    private String serviceNo;

    @Column(name = "SYSTEM_ID", length = 255)
    private String systemId;

    @Column(name = "CAUTION_RULE_YN")
    private Integer cautionRuleYn = 0;

    @Column(name = "SCHEDULES_RULE_YN")
    private Integer schedulesRuleYn = 0;

    @Column(name = "SUNUPDOWN_RULE_YN")
    private Integer sunupdownRuleYn = 0;

    @Column(name = "TRESPASS_ACTION_ID")
    private Integer trespassActionId = 0;

    @Column(name = "EXTRA", length = 128)
    private String extra;

    public static IotDeviceProfile buildIotDeviceProfile(DeviceRequestDto deviceRequestDto) {
        DeviceUtil deviceUtil = new DeviceUtil();
        String deviceId = deviceUtil.buildDeviceId(deviceRequestDto);
        IotDeviceProfile iotDeviceProfile = new IotDeviceProfile();
        iotDeviceProfile.setSubsNo("50abf12a0000001");
        iotDeviceProfile.setDeviceId(deviceId);
        iotDeviceProfile.setDisplayImg(deviceId);
        iotDeviceProfile.setDisplayName(deviceId);

        return iotDeviceProfile;
    }

}
