package kr.damda.dcm.iotcore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.AttachPolicyRequest;
import software.amazon.awssdk.services.iot.model.CreatePolicyRequest;
import software.amazon.awssdk.services.iot.model.CreatePolicyResponse;
import software.amazon.awssdk.services.iot.model.DeletePolicyRequest;
import software.amazon.awssdk.services.iot.model.DetachPolicyRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class PolicyServiceImpl implements PolicyService {

    @Value("{aws.iot.dev-arn}")
    private String awsIotArn;

    @Override
    public String createPolicy(String deviceId, String deviceType,IotClient iotClient) {
        String policyDocument = getPolicyDocument(deviceId, deviceType);

        CreatePolicyRequest createPolicyRequest = CreatePolicyRequest.builder()
            .policyName(deviceId + "_POLICY")
            .policyDocument(policyDocument)
            .build();

        CreatePolicyResponse createPolicyResponse = iotClient.createPolicy(createPolicyRequest);
        return createPolicyResponse.policyName();
    }

    @Override
    public void attachPolicyToCertificate(String policyName, String certificateArn,IotClient iotClient) {
        AttachPolicyRequest attachPolicyRequest = AttachPolicyRequest.builder()
            .policyName(policyName)
            .target(certificateArn)
            .build();

        iotClient.attachPolicy(attachPolicyRequest);
    }

    private String getPolicyDocument(String deviceId, String deviceType) {

        return "{\n" +
            "  \"Version\": \"2012-10-17\",\n" +
            "  \"Statement\": [\n" +
            "    {\n" +
            "      \"Effect\": \"Allow\",\n" +
            "      \"Action\": \"iot:Connect\",\n" +
            "      \"Resource\": \"" + awsIotArn + ":client/" + deviceId + "\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"Effect\": \"Allow\",\n" +
            "      \"Action\": \"iot:Publish\",\n" +
            "      \"Resource\": [\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/" + deviceId + "/iot-server/register/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/" + deviceId + "/iot-server/unregister/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/" + deviceId + "/iot-server/timesync/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/" + deviceId + "/iot-server/read/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/" + deviceId + "/iot-server/write/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/" + deviceId + "/iot-server/execute/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/" + deviceId + "/iot-server/delete/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/" + deviceId + "/iot-server/notify/json\",\n" +
            "        \"" + awsIotArn + ":topic/$aws/things/" + deviceType + "/shadow/get\",\n" +
            "        \"" + awsIotArn + ":topic/$aws/things/" + deviceType + "/shadow/update\"\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"Effect\": \"Allow\",\n" +
            "      \"Action\": \"iot:Subscribe\",\n" +
            "      \"Resource\": [\n" +
            "        \"" + awsIotArn + ":topicfilter/" + deviceType + "/sync/iot-server/" + deviceId + "/register/json\",\n" +
            "        \"" + awsIotArn + ":topicfilter/" + deviceType + "/sync/iot-server/" + deviceId + "/unregister/json\",\n" +
            "        \"" + awsIotArn + ":topicfilter/" + deviceType + "/sync/iot-server/" + deviceId + "/timesync/json\",\n" +
            "        \"" + awsIotArn + ":topicfilter/" + deviceType + "/sync/iot-server/" + deviceId + "/read/json\",\n" +
            "        \"" + awsIotArn + ":topicfilter/" + deviceType + "/sync/iot-server/" + deviceId + "/write/json\",\n" +
            "        \"" + awsIotArn + ":topicfilter/" + deviceType + "/sync/iot-server/" + deviceId + "/execute/json\",\n" +
            "        \"" + awsIotArn + ":topicfilter/" + deviceType + "/sync/iot-server/" + deviceId + "/delete/json\",\n" +
            "        \"" + awsIotArn + ":topicfilter/" + deviceType + "/sync/iot-server/" + deviceId + "/notify/json\",\n" +
            "        \"" + awsIotArn + ":topicfilter/" + deviceType + "/sync/iot-server/" + deviceId + "/response/json\",\n" +
            "        \"" + awsIotArn + ":topicfilter/$aws/things/" + deviceId + "/shadow/get/accepted\",\n" +
            "        \"" + awsIotArn + ":topicfilter/$aws/things/" + deviceId + "/shadow/get/rejected\",\n" +
            "        \"" + awsIotArn + ":topicfilter/$aws/things/" + deviceId + "/shadow/update/accepted\",\n" +
            "        \"" + awsIotArn + ":topicfilter/$aws/things/" + deviceId + "/shadow/update/rejected\",\n" +
            "        \"" + awsIotArn + ":topicfilter/$aws/things/" + deviceId + "/shadow/update/delta\"\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"Effect\": \"Allow\",\n" +
            "      \"Action\": \"iot:Receive\",\n" +
            "      \"Resource\": [\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/iot-server/" + deviceId + "/register/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/iot-server/" + deviceId + "/unregister/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/iot-server/" + deviceId + "/timesync/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/iot-server/" + deviceId + "/read/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/iot-server/" + deviceId + "/write/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/iot-server/" + deviceId + "/execute/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/iot-server/" + deviceId + "/delete/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/iot-server/" + deviceId + "/notify/json\",\n" +
            "        \"" + awsIotArn + ":topic/" + deviceType + "/sync/iot-server/" + deviceId + "/response/json\",\n" +
            "        \"" + awsIotArn + ":topic/$aws/things/" + deviceId + "/shadow/get/accepted\",\n" +
            "        \"" + awsIotArn + ":topic/$aws/things/" + deviceId + "/shadow/get/rejected\",\n" +
            "        \"" + awsIotArn + ":topic/$aws/things/" + deviceId + "/shadow/update/accepted\",\n" +
            "        \"" + awsIotArn + ":topic/$aws/things/" + deviceId + "/shadow/update/rejected\",\n" +
            "        \"" + awsIotArn + ":topic/$aws/things/" + deviceId + "/shadow/update/delta\"\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    }

    public void detachPolicyFromCertificate(String policyName, String certificateArn, IotClient iotClient) {
        try {
            DetachPolicyRequest detachRequest = DetachPolicyRequest.builder()
                .policyName(policyName)
                .target(certificateArn)
                .build();
            iotClient.detachPolicy(detachRequest);
        } catch (Exception e) {
            log.error("Failed to detach policy: {}", e.getMessage());
        }
    }

    public void deletePolicy(String policyName, IotClient iotClient) {
        try {
            DeletePolicyRequest deleteRequest = DeletePolicyRequest.builder()
                .policyName(policyName)
                .build();
            iotClient.deletePolicy(deleteRequest);
        } catch (Exception e) {
            log.error("Failed to delete policy: {}", e.getMessage());
        }
    }
}
