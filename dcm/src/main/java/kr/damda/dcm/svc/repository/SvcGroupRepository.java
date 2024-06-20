package kr.damda.dcm.svc.repository;

import kr.damda.dcm.svc.entity.SvcGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SvcGroupRepository extends JpaRepository<SvcGroup, Long> {

}
