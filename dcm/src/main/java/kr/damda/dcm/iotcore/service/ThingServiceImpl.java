package kr.damda.dcm.iotcore.service;

import java.util.List;
import java.util.stream.Collectors;
import kr.damda.dcm.iotcore.dto.ThingInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.CreateThingRequest;
import software.amazon.awssdk.services.iot.model.CreateThingResponse;
import software.amazon.awssdk.services.iot.model.DeleteThingRequest;
import software.amazon.awssdk.services.iot.model.DescribeThingRequest;
import software.amazon.awssdk.services.iot.model.DescribeThingResponse;
import software.amazon.awssdk.services.iot.model.IotException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ThingServiceImpl implements ThingService {

    @Override
    public boolean validationThing(String thingName, IotClient iotClient) {
        log.info("Validation Check Start");
        try {
            DescribeThingResponse describeThingResponse = iotClient.describeThing(
                DescribeThingRequest.builder().thingName(thingName).build());
            log.info("Find Thing" + describeThingResponse.thingName());
            return describeThingResponse.thingId().isEmpty();
        } catch (IotException e) {
            return true;
        }
    }

    @Override
    public CreateThingResponse createThing(String thingName,IotClient iotClient) {

        log.info("Thing Create Start");
        CreateThingResponse createThingResponse =
            iotClient
                .createThing(
                    CreateThingRequest.builder()
                        .thingName(thingName)
                        //.thingTypeName(deviceInfoDTO.getMaker())
                        .build());

        return createThingResponse;
    }

    @Override
    public void deleteThing(IotClient iotClient, String thingName) {
        log.info("thing delete start");
        // Thing 삭제 요청 생성 및 전송
        DeleteThingRequest deleteThingRequest = DeleteThingRequest.builder()
            .thingName(thingName)
            .build();
        iotClient.deleteThing(deleteThingRequest);
        log.info("thing delete ok");
    }

    @Override
    public DescribeThingResponse getThingForName(String thingName, IotClient iotClient) {

        DescribeThingRequest request = DescribeThingRequest.builder()
            .thingName(thingName)
            .build();

        return iotClient.describeThing(request);
    }

    @Override
    public List<ThingInfoDto> filterThingsByUserId(List<ThingInfoDto> things, String userId) {
        return things.stream()
            .filter(thing -> userId.equals(thing.getThingAttr().get("userId")))
            .collect(Collectors.toList());
    }

}
