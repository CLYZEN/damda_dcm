package kr.damda.dcm.iotcore.service;

import software.amazon.awssdk.services.iot.IotClient;

public interface PolicyService {

    void createPolicyAndAttachToCertificate(IotClient iotClient, String policyName,
        String policyDocument, String certificateArn);
}
