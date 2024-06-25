package kr.damda.dcm.iot.repository;

import java.util.Optional;
import kr.damda.dcm.iot.entity.IotDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IotDeviceRepository extends JpaRepository<IotDevice, Long> {

    Optional<IotDevice> findByDeviceId(String deviceId);
}
