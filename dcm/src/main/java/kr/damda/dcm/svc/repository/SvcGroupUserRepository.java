package kr.damda.dcm.svc.repository;

import kr.damda.dcm.svc.entity.SvcGroupUser;
import kr.damda.dcm.svc.entity.id.SvcGroupUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SvcGroupUserRepository extends JpaRepository<SvcGroupUser, SvcGroupUserId> {
    SvcGroupUser findByUserId(Long userId);
}
