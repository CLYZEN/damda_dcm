package kr.damda.dcm.svc.entity;

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
@Table(name = "svc_device_maker")
public class SvcDeviceMaker extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 45)
    private String name;

    @Column(name = "CODE", nullable = false, length = 45)
    private String code;

    @Column(name = "COPANY_UUID", columnDefinition = "char(36) default uuid()")
    private String companyUuid;
}
