package kr.damda.dcm.svc.repository;

import kr.damda.dcm.svc.entity.SvcUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SvcUserRepository extends JpaRepository<SvcUser, Long> {
    SvcUser findByLoginId(String loginId);
}
