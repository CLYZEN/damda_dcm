package kr.damda.dcm.iotcore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import software.amazon.awssdk.services.iot.model.CreateKeysAndCertificateResponse;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CertificatedDto {

    private String certificate_pem;

    private String key_private_pem;

    private String key_public_pem;

    public static CertificatedDto setCertificateResponseAttr(
        CreateKeysAndCertificateResponse createKeysResponse) {
        CertificatedDto certificatedDto = new CertificatedDto();
        certificatedDto.setCertificate_pem(createKeysResponse.certificatePem());
        certificatedDto.setKey_private_pem(createKeysResponse.keyPair().privateKey());
        certificatedDto.setKey_public_pem(createKeysResponse.keyPair().publicKey());
        return certificatedDto;
    }

}
