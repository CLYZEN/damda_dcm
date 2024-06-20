package kr.damda.dcm.svc.repository;

import kr.damda.dcm.svc.entity.SvcGroupDevice;
import kr.damda.dcm.svc.entity.id.SvcGroupDeviceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SvcGroupDeviceRepository extends JpaRepository<SvcGroupDevice, SvcGroupDeviceId> {

    @Query("SELECT g.groupId FROM SvcGroupDevice g JOIN g.device d WHERE d.deviceId = :deviceId")
    Integer findGroupIdsByDeviceId(@Param("deviceId") String deviceId);

}
