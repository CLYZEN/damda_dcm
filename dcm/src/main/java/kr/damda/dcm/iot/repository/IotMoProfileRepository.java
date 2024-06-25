package kr.damda.dcm.iot.repository;

import kr.damda.dcm.iot.entity.IotMoProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IotMoProfileRepository extends JpaRepository<IotMoProfile, Long> {

}
