package kr.damda.dcm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "iot_mo_profile")
@Getter
@Setter
@ToString
public class IotMoProfile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DEVICE_MODEL_ID", nullable = false)
    private Long deviceModelId;

    @Column(name = "PROFILE_VER", nullable = false, length = 100)
    private String profileVer;

    @Column(name = "DISPLAY_NAME", length = 30)
    private String displayName;

    @Column(name = "RESOURCE_NAME", length = 30)
    private String resourceName;

    @Column(name = "OBJECT_ID", nullable = false)
    private Integer objectId;

    @Column(name = "RESOURCE_ID", nullable = false)
    private Integer resourceId;

    @Column(name = "DATA_TYPE", nullable = false, length = 1)
    private char dataType;

    @Column(name = "OPERATION", nullable = false, length = 3)
    private String operation;

    @Column(name = "DEFAULT_VALUE", length = 512)
    private String defaultValue;

    @Column(name = "UNIT", length = 7)
    private String unit;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Column(name = "STATUS", nullable = false)
    private Byte status = 1;

    @Column(name = "MANDOTORY_YN", nullable = false, length = 1)
    @ColumnDefault("0")
    private char mandotoryYn;

    @Column(name = "HISTORICAL_YN", nullable = false, length = 1)
    @ColumnDefault("0")
    private char historicalYn;

    @Column(name = "STATE_CONDITION", nullable = false, length = 10)
    private String stateCondition = "CHANGE";

    @Column(name = "MAX_COUNT", nullable = false)
    private Short maxCount = 1;

    @Column(name = "EXTENSION", length = 10000)
    private String extension;
}
