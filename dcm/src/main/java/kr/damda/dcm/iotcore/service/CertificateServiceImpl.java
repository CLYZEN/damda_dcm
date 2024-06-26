package kr.damda.dcm.iotcore.service;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.AttachThingPrincipalRequest;
import software.amazon.awssdk.services.iot.model.CertificateStatus;
import software.amazon.awssdk.services.iot.model.CreateKeysAndCertificateRequest;
import software.amazon.awssdk.services.iot.model.CreateKeysAndCertificateResponse;
import software.amazon.awssdk.services.iot.model.DeleteCertificateRequest;
import software.amazon.awssdk.services.iot.model.DetachThingPrincipalRequest;
import software.amazon.awssdk.services.iot.model.ListCertificatesRequest;
import software.amazon.awssdk.services.iot.model.ListCertificatesResponse;
import software.amazon.awssdk.services.iot.model.ListThingPrincipalsRequest;
import software.amazon.awssdk.services.iot.model.ListThingPrincipalsResponse;
import software.amazon.awssdk.services.iot.model.UpdateCertificateRequest;

@Service
@Slf4j
public class CertificateServiceImpl implements CertificateService {
    @Override
    public CreateKeysAndCertificateResponse attachKey(IotClient iotClient, String thingName) {

        log.info("Attach Key Start");
        CreateKeysAndCertificateResponse createKeysResponse = iotClient.createKeysAndCertificate(
            CreateKeysAndCertificateRequest.builder().setAsActive(true).build());

        AttachThingPrincipalRequest attachThingPrincipalRequest = AttachThingPrincipalRequest.builder()
            .thingName(thingName)
            .principal(createKeysResponse.certificateArn())
            .build();
        iotClient.attachThingPrincipal(attachThingPrincipalRequest);
        log.info("Attach Key Finish");

        return createKeysResponse;
    }

    @Override
    public ArrayList<String> deleteKey(IotClient iotClient, String thingName) {
        log.info("Certificate Key Delete Start");
        ArrayList<String> certificateIdList = new ArrayList<>();

        log.info("Principal Delete Start");
        // Thing에 연결된 인증서 확인
        ListThingPrincipalsRequest listThingPrincipalsRequest = ListThingPrincipalsRequest.builder()
            .thingName(thingName)
            .build();
        ListThingPrincipalsResponse listThingPrincipalsResponse = iotClient.listThingPrincipals(listThingPrincipalsRequest);

        // 모든 연결된 인증서 제거
        for (String principal : listThingPrincipalsResponse.principals()) {
            String certificateId = principal.substring(principal.lastIndexOf("/") + 1);
            DetachThingPrincipalRequest detachThingPrincipalRequest = DetachThingPrincipalRequest.builder()
                .thingName(thingName)
                .principal(principal)
                .build();
            iotClient.detachThingPrincipal(detachThingPrincipalRequest);
        }
        log.info("Principal Delete Finish");

        log.info("Certificate Delete Start");
        // 모든 연결된 인증서 삭제
        ListCertificatesRequest listCertificatesRequest = ListCertificatesRequest.builder()
            .build();
        ListCertificatesResponse listCertificatesResponse = iotClient.listCertificates(listCertificatesRequest);
        for (String principal : listThingPrincipalsResponse.principals()) {
            String certificateId = principal.substring(principal.lastIndexOf("/") + 1);
            UpdateCertificateRequest updateCertificateRequest = UpdateCertificateRequest.builder()
                .certificateId(certificateId)
                .newStatus("INACTIVE")
                .build();
            certificateIdList.add(certificateId);
            iotClient.updateCertificate(updateCertificateRequest);

            // 인증서 삭제 작업 수행
            DeleteCertificateRequest deleteCertificateRequest = DeleteCertificateRequest.builder()
                .certificateId(certificateId)
                .build();
            iotClient.deleteCertificate(deleteCertificateRequest);
        }
        log.info("Certificate Delete Finish");

        log.info("Certificate Key Delete Finish");
        return certificateIdList;
    }

    public void detachCertificate(IotClient iotClient, String deviceId, String certificateArn) {
        try {
            DetachThingPrincipalRequest detachRequest = DetachThingPrincipalRequest.builder()
                .thingName(deviceId)
                .principal(certificateArn)
                .build();
            iotClient.detachThingPrincipal(detachRequest);
        } catch (Exception e) {
            log.error("Failed to detach certificate: {}", e.getMessage());
        }
    }

    public void deleteCertificate(IotClient iotClient, String certificateId) {
        try {
            UpdateCertificateRequest updateRequest = UpdateCertificateRequest.builder()
                .certificateId(certificateId)
                .newStatus(CertificateStatus.INACTIVE)
                .build();
            iotClient.updateCertificate(updateRequest);

            DeleteCertificateRequest deleteRequest = DeleteCertificateRequest.builder()
                .certificateId(certificateId)
                .build();
            iotClient.deleteCertificate(deleteRequest);
        } catch (Exception e) {
            log.error("Failed to delete certificate: {}", e.getMessage());
        }
    }
}
