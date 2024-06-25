package kr.damda.dcm.iotcore.service;

import java.util.List;
import kr.damda.dcm.dto.request.svc.component.DeviceRequestDto;
import kr.damda.dcm.iotcore.dto.ThingInfoDto;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.CreateThingResponse;
import software.amazon.awssdk.services.iot.model.DescribeThingResponse;

public interface ThingService {
    boolean validationThing(String thingName, IotClient IotClient);

    CreateThingResponse createThing(String thingName,IotClient iotClient);

    void deleteThing(IotClient iotClient, String thingName);

    DescribeThingResponse getThingForName(String thingName, IotClient iotClient);

    List<ThingInfoDto> filterThingsByUserId(List<ThingInfoDto> things, String userId);
}
