package net.homenet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { BankConfiguration.class })
public class OpenIBanValidationClientImplTest {

    @Autowired
    private OpenIBanValidationClientImpl client;

    private MockRestServiceServer mockRestServiceServer;

    @Before
    public void setUp() {
        mockRestServiceServer = MockRestServiceServer.createServer(client);
    }

    @Test
    public void validIban() {
        mockRestServiceServer
            .expect(requestTo("https://openiban.com/validate/NL87TRIO0396451440?getBIC=true&validateBankCode=true"))
            .andRespond(withSuccess(new ClassPathResource("NL87TRIO0396451440-result.json"), MediaType.APPLICATION_JSON));

        IbanValidationResult result = client.validate("NL87TRIO0396451440");
        assertTrue(result.isValid());
    }

    @Test
    public void invalidIban() {
        mockRestServiceServer
            .expect(requestTo("https://openiban.com/validate/NL28XXXX389242218?getBIC=true&validateBankCode=true"))
            .andRespond(withSuccess(new ClassPathResource("NL28XXXX389242218-result.json"), MediaType.APPLICATION_JSON));

        IbanValidationResult result = client.validate("NL28XXXX389242218");
        assertFalse(result.isValid());
    }
}
