package kr.damda.dcm.iotcore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.AttachPolicyRequest;
import software.amazon.awssdk.services.iot.model.CreatePolicyRequest;
import software.amazon.awssdk.services.iot.model.CreatePolicyResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class PolicyServiceImpl implements PolicyService {

    @Override
    public void createPolicyAndAttachToCertificate(IotClient iotClient, String policyName,
        String policyDocument, String certificateArn) {
        // 정책 생성
        CreatePolicyRequest createPolicyRequest = CreatePolicyRequest.builder()
            .policyName(policyName)
            .policyDocument(policyDocument)
            .build();

        CreatePolicyResponse createPolicyResponse = iotClient.createPolicy(createPolicyRequest);
        System.out.println("Policy ARN: " + createPolicyResponse.policyArn());

        // 인증서에 정책 연결
        AttachPolicyRequest attachPolicyRequest = AttachPolicyRequest.builder()
            .policyName(policyName)
            .target(certificateArn)
            .build();

        iotClient.attachPolicy(attachPolicyRequest);
        System.out.println("Policy attached to the certificate.");
    }
}
