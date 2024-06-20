package kr.damda.dcm.svc.repository;

import kr.damda.dcm.svc.entity.SvcDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SvcDeviceRepository extends JpaRepository<SvcDevice, Long> {

    SvcDevice findByDeviceId(String deviceId);

}
