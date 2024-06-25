package kr.damda.dcm.iot.repository;

import kr.damda.dcm.dto.response.iot.IotDeviceModelProjection;
import kr.damda.dcm.iot.entity.IotDeviceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IotDeviceModelRepository extends JpaRepository<IotDeviceModel, Long> {

    IotDeviceModelProjection findByManufacturerAndModelName(String manufacturer, String modelName);
}
