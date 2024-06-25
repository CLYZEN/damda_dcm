package kr.damda.dcm.iotcore.service;

import java.util.HashMap;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.AttributePayload;

public interface AttrService {

    AttributePayload getAttrPayload(Object obj);

    void setThingAttr(AttributePayload attributePayload, String thingName, IotClient iotClient);

    void updateThingAttr(String thingName, HashMap<String, String> attributes, IotClient iotClient);
}
