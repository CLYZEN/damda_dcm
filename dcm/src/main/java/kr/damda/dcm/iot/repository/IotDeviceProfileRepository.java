package kr.damda.dcm.iot.repository;

import kr.damda.dcm.iot.entity.IotDeviceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IotDeviceProfileRepository extends JpaRepository<IotDeviceProfile, Long> {

}
