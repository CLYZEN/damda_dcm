package kr.damda.dcm.dto.request.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonRootName(value = "device")
public class DeviceRequestDto {

    @NotNull
    private String type;

    @NotNull
    @Size(max = 20, message = "Maker cannot be longer than 20 characters")
    private String maker;

    @NotNull
    @Size(max = 20, message = "Model cannot be longer than 20 characters")
    private String model;

    @NotNull
    @Size(max = 50, message = "SerialNumber cannot be longer than 20 characters")
    @JsonProperty(value = "serial_number")
    private String serialNumber;

    @NotNull
    @Size(max = 20, message = "Location cannot be longer than 20 characters")
    private String location;

    @NotNull
    @Size(max = 20, message = "Name cannot be longer than 20 characters")
    private String name;

    private String longitude;

    private String latitude;

    @JsonProperty(value = "address")
    private AddressRequestDto addressDto;
}
