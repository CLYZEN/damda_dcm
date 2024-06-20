package kr.damda.dcm.svc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "svc_user")
public class SvcUser extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOGIN_ID", length = 255)
    private String loginId;

    @Column(name = "PASSWORD", length = 50)
    private String password;

    @Column(name = "NICKNAME", nullable = false, length = 20)
    private String nickname;

    @Column(name = "PHONE", nullable = false, length = 15)
    private String phone;

    @Column(name = "EMAIL", length = 255)
    private String email;

    @Column(name = "WITHDRAWAL_STATUS", nullable = false, length = 1, columnDefinition = "char(1) default 'F'")
    private char withdrawalStatus = 'F';

    @Column(name = "LAST_LOGIN_TIME")
    private LocalDateTime lastLoginTime;

    @Column(name = "BIRTHDAY", length = 8)
    private String birthday;

    @Column(name = "GENDER", length = 10)
    private String gender;

}
