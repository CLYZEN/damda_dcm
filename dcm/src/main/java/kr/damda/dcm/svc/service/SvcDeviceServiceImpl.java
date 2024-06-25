package kr.damda.dcm.svc.service;

import java.util.List;
import kr.damda.dcm.dto.request.svc.RegisterRequestDto;
import kr.damda.dcm.dto.response.svc.RegisterResponseDto;
import kr.damda.dcm.dto.response.svc.component.AccountResponseDto;
import kr.damda.dcm.dto.response.svc.component.ResultResponseDto;
import kr.damda.dcm.exception.NotFoundException;
import kr.damda.dcm.service.DeviceService;
import kr.damda.dcm.svc.entity.SvcDevice;
import kr.damda.dcm.svc.entity.SvcDeviceMaker;
import kr.damda.dcm.svc.entity.SvcDeviceModel;
import kr.damda.dcm.svc.entity.SvcGroup;
import kr.damda.dcm.svc.entity.SvcGroupDevice;
import kr.damda.dcm.svc.entity.SvcGroupUser;
import kr.damda.dcm.svc.entity.SvcUser;
import kr.damda.dcm.svc.repository.SvcDeviceMakerRepository;
import kr.damda.dcm.svc.repository.SvcDeviceModelRepository;
import kr.damda.dcm.svc.repository.SvcDeviceRepository;
import kr.damda.dcm.svc.repository.SvcGroupDeviceRepository;
import kr.damda.dcm.svc.repository.SvcGroupRepository;
import kr.damda.dcm.svc.repository.SvcGroupUserRepository;
import kr.damda.dcm.svc.repository.SvcUserRepository;
import kr.damda.dcm.util.DeviceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional("svcTransactionManager")
public class SvcDeviceServiceImpl implements DeviceService {

    private final SvcGroupDeviceRepository svcGroupDeviceRepository;
    private final SvcDeviceRepository svcDeviceRepository;
    private final SvcDeviceMakerRepository svcDeviceMakerRepository;
    private final SvcDeviceModelRepository svcDeviceModelRepository;
    private final SvcUserRepository svcUserRepository;
    private final SvcGroupUserRepository svcGroupUserRepository;
    private final SvcGroupRepository svcGroupRepository;
    private final DeviceUtil deviceUtil;

    /* SVC Register */
    @Override
    public RegisterResponseDto registerDevice(RegisterRequestDto registerRequestDto) {
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        SvcDeviceMaker svcDeviceMaker = validateSvcDeviceMaker(
            registerRequestDto.getDeviceDto().getMaker());
        List<SvcDeviceModel> svcDeviceModelList = validateSvcDeviceModel(svcDeviceMaker.getId(),
            registerRequestDto.getDeviceDto().getModel());
        validateSvcDevice(deviceUtil.buildDeviceId(registerRequestDto.getDeviceDto()));
        boolean isSvcGroup = validateSvcGroup(registerRequestDto.getAccountDto().getLoginId());

        SvcDevice newSvcDevice = SvcDevice.createSvcDevice(registerRequestDto.getDeviceDto());

        AccountResponseDto accountResponseDto = new AccountResponseDto();
        ResultResponseDto resultResponseDto = new ResultResponseDto();

        svcDeviceRepository.save(newSvcDevice);

        SvcUser svcUser = svcUserRepository.findByLoginId(
            registerRequestDto.getAccountDto().getLoginId());

        // 기존 그룹 존재
        if (isSvcGroup) {

            SvcGroupUser svcGroupUser = svcGroupUserRepository.findByUserId(svcUser.getId());

            Long deviceId = svcDeviceRepository.findByDeviceId(
                deviceUtil.buildDeviceId(registerRequestDto.getDeviceDto())).getId();
            Long groupId = svcGroupUser.getGroupId();

            SvcGroupDevice svcGroupDevice = SvcGroupDevice.createSvcGroupDevice(groupId, deviceId);


            accountResponseDto.setGroupId(groupId.toString());
            resultResponseDto.setCode(0);
            registerResponseDto.setAccountDto(accountResponseDto);
            registerResponseDto.setResultDto(resultResponseDto);

            svcGroupDeviceRepository.save(svcGroupDevice);

        }

        if (!isSvcGroup) {
            SvcGroup svcGroup = new SvcGroup();
            svcGroup.setNickName("우리집");

            SvcGroupDevice svcGroupDevice = new SvcGroupDevice();
            svcGroupDevice.setDeviceId(newSvcDevice.getId());

            SvcGroupUser svcGroupUser = new SvcGroupUser();
            svcGroupUser.setUserId(svcUser.getId());

            svcGroupRepository.save(svcGroup);
            svcGroupDeviceRepository.save(svcGroupDevice);
            svcGroupUserRepository.save(svcGroupUser);

            resultResponseDto.setCode(0);
            accountResponseDto.setGroupId(svcGroup.getId().toString());

            registerResponseDto.setResultDto(resultResponseDto);
            registerResponseDto.setAccountDto(accountResponseDto);

        }
        return registerResponseDto;
    }

    /* Validate Group */

    // svcGroupUser 객체가 not null 이라면 true (현재 가진 그룹이 있음)
    // svcGroupUser 객체가 null 이라면 false (현재 가진 그룹이 없음 그룹 생성 필요)
    private boolean validateSvcGroup(String loginId) {
        SvcUser svcUser = svcUserRepository.findByLoginId(loginId);
        if (svcUser == null) {
            throw new NotFoundException("User not found: " + loginId);
        }
        SvcGroupUser svcGroupUser = svcGroupUserRepository.findByUserId(svcUser.getId());

        return svcGroupUser != null;
    }

    /* Validate Device */
    private void validateSvcDevice(String deviceId) {
        SvcDevice svcDevice = svcDeviceRepository.findByDeviceId(deviceId);

        if (svcDevice != null) {
            throw new IllegalStateException(deviceId + "already exists");
        }


    }

    private SvcDeviceMaker validateSvcDeviceMaker(String maker) {
        SvcDeviceMaker svcDeviceMaker = svcDeviceMakerRepository.findByCode(maker);
        if (svcDeviceMaker == null) {
            throw new NotFoundException("Check out the maker");
        }
        return svcDeviceMaker;
    }

    private List<SvcDeviceModel> validateSvcDeviceModel(Long makerId, String model) {
        List<SvcDeviceModel> svcDeviceModelList = svcDeviceModelRepository.findByDeviceMakerId(
            makerId);

        if (svcDeviceModelList.isEmpty()) {
            throw new NotFoundException("Check out the model");
        }

        boolean modelExists = svcDeviceModelList.stream()
            .anyMatch(deviceModel -> deviceModel.getCode().equals(model));

        if (!modelExists) {
            throw new NotFoundException("Check out the model");
        }

        return svcDeviceModelList;
    }

}
