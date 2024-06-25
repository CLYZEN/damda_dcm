package kr.damda.dcm.iot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import kr.damda.dcm.dto.request.svc.component.DeviceRequestDto;
import kr.damda.dcm.util.DeviceUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "iot_device", indexes = @Index(name = "IOT_DEVICE_INDEX", columnList = "device_id"))
@DynamicInsert
@DynamicUpdate
public class IotDevice extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "device_id")
    private String deviceId;

    @Column(nullable = false, name = "subs_no")
    private String subsNo;

    @Column(name = "parent_id")
    private String parentId;

    @Column(nullable = false, name = "conn_status")
    @ColumnDefault("0")
    private Integer connStatus;

    @Column(nullable = false, name = "device_model_id")
    private Long deviceModelId;

    @Column(nullable = false, name = "manufacturer")
    private String manufacturer;

    @Column(nullable = false, name = "model_name")
    private String modelName;

    @Column(nullable = false, name = "data_format")
    @ColumnDefault("")
    private String dataFormat;

    private String location;

    @Column(name = "lora_api_key")
    private String loraApiKey;

    @Column(name = "manufacturer_id")
    private String manufactureId;

    private LocalDateTime registerTime;

    public static IotDevice buildIotDevice(DeviceRequestDto deviceRequestDto,Long deviceModelId) {
        DeviceUtil deviceUtil = new DeviceUtil();
        IotDevice iotDevice = new IotDevice();
        iotDevice.setDeviceId(deviceUtil.buildDeviceId(deviceRequestDto));
        iotDevice.setConnStatus(0);
        iotDevice.setDeviceModelId(deviceModelId);
        iotDevice.setLocation(deviceRequestDto.getLocation());
        iotDevice.setSubsNo("50abf12a0000001");
        iotDevice.setManufacturer(deviceRequestDto.getMaker());
        iotDevice.setModelName(deviceRequestDto.getModel());
        iotDevice.setDataFormat("json");

        return iotDevice;
    }
}
