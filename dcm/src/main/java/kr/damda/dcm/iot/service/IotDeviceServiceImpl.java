package kr.damda.dcm.iot.service;

import java.util.Optional;
import kr.damda.dcm.dto.request.svc.RegisterRequestDto;
import kr.damda.dcm.dto.request.svc.component.DeviceRequestDto;
import kr.damda.dcm.dto.response.iot.IotDeviceModelProjection;
import kr.damda.dcm.dto.response.svc.RegisterResponseDto;
import kr.damda.dcm.exception.DeviceAlreadyRegisteredException;
import kr.damda.dcm.iot.entity.IotDevice;
import kr.damda.dcm.iot.entity.IotDeviceProfile;
import kr.damda.dcm.iot.repository.IotDeviceModelRepository;
import kr.damda.dcm.iot.repository.IotDeviceRepository;
import kr.damda.dcm.service.DeviceService;
import kr.damda.dcm.util.DeviceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class IotDeviceServiceImpl implements DeviceService {

    private final IotDeviceRepository iotDeviceRepository;
    private final IotDeviceModelRepository iotDeviceModelRepository;
    private final DeviceUtil deviceUtil;

    @Override
    public RegisterResponseDto registerDevice(RegisterRequestDto registerRequestDto) {
        if (validateIotDevice(registerRequestDto.getDeviceDto())) {
            throw new DeviceAlreadyRegisteredException("이미 등록된 디바이스");
        }

        if (!validateIotDevice(registerRequestDto.getDeviceDto())) {
            /* 기존 가등록 Start */
            IotDeviceModelProjection iotDeviceModelProjection = iotDeviceModelRepository.findByManufacturerAndModelName(
                registerRequestDto.getDeviceDto().getMaker(),
                registerRequestDto.getDeviceDto().getModel());

            IotDevice iotDevice = IotDevice.buildIotDevice(registerRequestDto.getDeviceDto(),
                iotDeviceModelProjection.getId());

            IotDeviceProfile iotDeviceProfile = IotDeviceProfile.buildIotDeviceProfile(registerRequestDto.getDeviceDto());
            /* 기존 가등록 End */


        }
        return null;
    }

    /* Iot Device */
    /* Iot Device 가 있을 경우 true 없을 경우 false */
    private boolean validateIotDevice(DeviceRequestDto deviceRequestDto) {
        String deviceId = deviceUtil.buildDeviceId(deviceRequestDto);
        Optional<IotDevice> iotDevice = iotDeviceRepository.findByDeviceId(deviceId);

        return iotDevice.isPresent();
    }

    private boolean validateResourceUri(String deviceId, String deviceType) {

        return true;
    }

}
