package kr.damda.dcm.svc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import kr.damda.dcm.svc.entity.id.SvcGroupUserId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@IdClass(SvcGroupUserId.class)
@Table(name = "svc_group_user", indexes = {
    @Index(name = "fk_SVC_GROUP_USER_SVC_GROUP1_idx", columnList = "GROUP_ID"),
    @Index(name = "fk_SVC_GROUP_USER_SVC_USER1_idx", columnList = "USER_ID"),
    @Index(name = "GROUP_USER_UNIQUE", columnList = "GROUP_ID, USER_ID", unique = true)
})
public class SvcGroupUser {

    @Id
    @Column(name = "GROUP_ID", nullable = false)
    private Long groupId;

    @Id
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "IS_MASTER", nullable = false, length = 1, columnDefinition = "char(1) default 'T'")
    private char isMaster = 'T';
}
