package kr.damda.dcm.svc.repository;

import kr.damda.dcm.svc.entity.SvcDeviceMaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SvcDeviceMakerRepository extends JpaRepository<SvcDeviceMaker, Long> {

    SvcDeviceMaker findByCode(String code);
}
