package kr.damda.dcm.iotcore.service;

import java.util.ArrayList;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.CreateKeysAndCertificateResponse;

public interface CertificateService {

    CreateKeysAndCertificateResponse attachKey(IotClient iotClient, String thingName);

    ArrayList<String> deleteKey(IotClient iotClient, String thingName);
}
