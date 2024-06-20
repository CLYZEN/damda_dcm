package kr.damda.dcm.svc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.damda.dcm.svc.entity.id.SvcGroupDeviceId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@IdClass(SvcGroupDeviceId.class)
@Table(name = "svc_group_device", indexes = {
    @Index(name = "fk_SVC_GROUP_has_SVC_DEVICE_SVC_DEVICE1_idx", columnList = "DEVICE_ID"),
    @Index(name = "fk_SVC_GROUP_has_SVC_DEVICE_SVC_GROUP1_idx", columnList = "GROUP_ID")
})
public class SvcGroupDevice extends BaseTimeEntity {

    @Id
    @Column(name = "GROUP_ID", nullable = false)
    private Long groupId;

    @Id
    @Column(name = "DEVICE_ID", nullable = false)
    private Long deviceId;

    @ManyToOne
    @JoinColumn(name = "DEVICE_ID", insertable = false, updatable = false)
    private SvcDevice device;

    @Column(name = "CURRENT_GROUP", nullable = false, length = 1, columnDefinition = "char(1) default 'T'")
    private char currentGroup = 'T';

    public static SvcGroupDevice createSvcGroupDevice(Long groupId, Long deviceId) {
        SvcGroupDevice svcGroupDevice = new SvcGroupDevice();
        svcGroupDevice.setGroupId(groupId);
        svcGroupDevice.setDeviceId(deviceId);

        return svcGroupDevice;
    }
}