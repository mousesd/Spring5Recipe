package net.homenet;

import org.springframework.stereotype.Service;
import org.springframework.web.client.support.RestGatewaySupport;

@Service
public class OpenIBanValidationClientImpl extends RestGatewaySupport implements IBanValidationClient {

    private static final String URL_TEMPLATE = "https://openiban.com/validate/{IBAN_NUMBER}?getBIC=true&validateBankCode=true";

    @Override
    public IbanValidationResult validate(String iban) {
        return getRestTemplate().getForObject(URL_TEMPLATE, IbanValidationResult.class, iban);
    }
}
