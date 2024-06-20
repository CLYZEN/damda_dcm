package kr.damda.dcm.svc.entity.id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class SvcGroupUserId {
    private Long groupId;
    private Long userId;

    public SvcGroupUserId() {}

    public SvcGroupUserId(Long groupId, Long userId) {
        this.groupId = groupId;
        this.userId = userId;
    }
}
