package kr.damda.dcm.svc.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import kr.damda.dcm.dto.request.svc.component.DeviceRequestDto;
import kr.damda.dcm.util.DeviceUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "svc_device")
public class SvcDevice extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "device")
    private Set<SvcGroupDevice> groupDevices;

    @Column(name = "DEVICE_ID", nullable = false, length = 60)
    private String deviceId;

    @Column(name = "MANUFACTURE", nullable = false, length = 20)
    private String manufacture;

    @Column(name = "MODEL", nullable = false, length = 20)
    private String model;

    @Column(name = "SERIAL", nullable = false, length = 50)
    private String serial;

    @Column(name = "NAME", nullable = false, length = 20)
    private String name;

    @Column(name = "LOCATION", length = 20)
    private String location;

    @Column(name = "WITHDRAWAL_STATUS", nullable = false, length = 1)
    private char withdrawalStatus;

    @Column(name = "LATITUDE", length = 30)
    private String latitude;

    @Column(name = "LONGITUDE", length = 30)
    private String longitude;

    @Column(name = "NATION_CODE", length = 5)
    private String nationCode;

    @Column(name = "ADDR1", length = 20)
    private String addr1;

    @Column(name = "ADDR2", length = 20)
    private String addr2;

    @Column(name = "ADDR3", length = 20)
    private String addr3;

    @Column(name = "ADDR4", length = 20)
    private String addr4;

    @Column(name = "ADDR_ROAD", length = 20)
    private String addrRoad;

    @Column(name = "ADDR_ROAD_NO", length = 10)
    private String addrRoadNo;

    @Column(name = "ADDR_BUILDING", length = 20)
    private String addrBuilding;

    public static SvcDevice createSvcDevice(DeviceRequestDto deviceRequestDto) {

        DeviceUtil deviceUtil = new DeviceUtil();
        SvcDevice svcDevice = new SvcDevice();
        svcDevice.setDeviceId(deviceUtil.buildDeviceId(deviceRequestDto));
        svcDevice.setManufacture(deviceRequestDto.getMaker());
        svcDevice.setModel(deviceRequestDto.getModel());
        svcDevice.setSerial(deviceRequestDto.getSerialNumber());
        svcDevice.setName(deviceRequestDto.getName());
        svcDevice.setLocation(deviceRequestDto.getLocation());
        svcDevice.setWithdrawalStatus('F');
        svcDevice.setLatitude(deviceRequestDto.getLatitude());
        svcDevice.setLongitude(deviceRequestDto.getLongitude());
        svcDevice.setAddr1(deviceRequestDto.getAddressDto().getAddr1());
        svcDevice.setAddr2(deviceRequestDto.getAddressDto().getAddr2());
        svcDevice.setAddr3(deviceRequestDto.getAddressDto().getAddr3());
        svcDevice.setAddr4(deviceRequestDto.getAddressDto().getAddr4());
        svcDevice.setAddrRoad(deviceRequestDto.getAddressDto().getAddrRoad());
        svcDevice.setAddrRoadNo(deviceRequestDto.getAddressDto().getAddrRoadNo());
        svcDevice.setAddrBuilding(deviceRequestDto.getAddressDto().getAddrBuilding());
        svcDevice.setNationCode(deviceRequestDto.getAddressDto().getNation());

        return svcDevice;
    }

}
