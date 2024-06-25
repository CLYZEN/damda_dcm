package kr.damda.dcm.iotcore.dto;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import software.amazon.awssdk.services.iot.model.ThingAttribute;

@Getter
@Setter
@ToString
public class ThingInfoDto {

    private String thingName;

    private String thingArn;

    private String thingTypeName;

    private Map<String,String> thingAttr;

    public ThingInfoDto(ThingAttribute thing, Map<String, String> attributes) {
        this.thingArn = thing.thingArn();
        this.thingName = thing.thingName();
        this.thingTypeName = thing.thingTypeName();
        this.thingAttr = attributes;
    }
}
