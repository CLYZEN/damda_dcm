package kr.damda.dcm.svc.repository;

import java.util.List;
import kr.damda.dcm.svc.entity.SvcDeviceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SvcDeviceModelRepository extends JpaRepository<SvcDeviceModel, Long> {

    List<SvcDeviceModel> findByDeviceMakerId(Long id);
}
