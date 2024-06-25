package kr.damda.dcm.dto.request.svc.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonRootName(value = "address")
public class AddressRequestDto {

    private String nation;

    private String addr1;

    private String addr2;

    private String addr3;

    private String addr4;

    @JsonProperty(value = "addr_road")
    private String addrRoad;

    @JsonProperty(value = "addr_road_no")
    private String addrRoadNo;

    @JsonProperty(value = "addr_building")
    private String addrBuilding;
}
