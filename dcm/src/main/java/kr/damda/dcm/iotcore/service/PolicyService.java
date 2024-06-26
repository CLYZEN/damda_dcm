package kr.damda.dcm.iotcore.service;

import software.amazon.awssdk.services.iot.IotClient;

public interface PolicyService {

    String createPolicy(String deviceId, String deviceType, IotClient iotClient);

    void attachPolicyToCertificate(String policyName, String certificateArn, IotClient iotClient);

    void detachPolicyFromCertificate(String policyName, String certificateArn, IotClient iotClient);

    void deletePolicy(String policyName, IotClient iotClient);
}
