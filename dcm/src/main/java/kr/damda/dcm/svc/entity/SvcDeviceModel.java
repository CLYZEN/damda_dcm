package kr.damda.dcm.svc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "svc_device_model", indexes = {
    @Index(name = "fk_SVC_DEVICE_MODEL_SVC_DEVICE_MAKER1_idx", columnList = "DEVICE_MAKER_ID"),
    @Index(name = "fk_SVC_DEVICE_MODEL_SVC_DEVICE_TYPE1_idx", columnList = "DEVICE_TYPE_ID")
})
public class SvcDeviceModel extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 45)
    private String name;

    @Column(name = "CODE", nullable = false, length = 45)
    private String code;

    @Column(name = "DEVICE_MAKER_ID", nullable = false)
    private Integer deviceMakerId;

    @Column(name = "DEVICE_TYPE_ID", nullable = false)
    private Integer deviceTypeId;

    @Column(name = "SERVICE_STATUS", nullable = false, length = 1, columnDefinition = "char(1) default 'F'")
    private char serviceStatus = 'F';
}
