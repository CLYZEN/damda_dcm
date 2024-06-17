package kr.damda.dcm.iot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "iot_device_model")
public class IotDeviceModel extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MANUFACTURER", nullable = false, length = 30)
    private String manufacturer;

    @Column(name = "MODEL_NAME", nullable = false, length = 50)
    private String modelName;

    @Column(name = "MANUFACTURER_GROUP", length = 8)
    private String manufacturerGroup;

    @Column(name = "DEVICE_TYPE", length = 20)
    private String deviceType;

    @Column(name = "SERVICE_NAME", nullable = false, length = 20)
    private String serviceName;

    @Column(name = "ICON_URL", length = 255)
    private String iconUrl;

    @Column(name = "PROTOCOL_VER", nullable = false, length = 10)
    private String protocolVer = "6.0";

    @Column(name = "PROFILE_VER", nullable = false, length = 100)
    private String profileVer;

    @Column(name = "FW_AUTO_UPDATE", nullable = false)
    private Integer fwAutoUpdate = 0;

    @Column(name = "SW_AUTO_UPDATE", nullable = false)
    private Integer swAutoUpdate = 0;

    @Column(name = "USE_VIRTUAL_ID")
    private Integer useVirtualId = 0;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Column(name = "API_VER", length = 100)
    private String apiVer = "v3";

    @Column(name = "APPLY_YN")
    private Integer applyYn = 1;
}
