package net.homenet.controller;

import net.homenet.configuration.BankConfiguration;
import net.homenet.configuration.BankWebConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.*;

@SuppressWarnings("SqlResolve")
@ContextConfiguration(classes = { BankConfiguration.class, BankWebConfiguration.class })
@WebAppConfiguration
@Sql(scripts = { "classpath:/ddl.sql" })
public class DepositControllerTestNGSpringContextTests extends AbstractTransactionalTestNGSpringContextTests {

    private static final String ACCOUNT_PARAM = "accountNo";
    private static final String AMOUNT_PARAM = "amount";
    private static final String TEST_ACCOUNT_NO = "1234";
    private static final String TEST_AMOUNT = "50.0";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeMethod
    public void setUp() {
        jdbcTemplate.update("INSERT INTO ACCOUNT (ACCOUNT_NO, BALANCE) VALUES (?, ?)", TEST_ACCOUNT_NO, 100);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testDeposit() throws Exception {
        mockMvc.perform(get("/deposit.do")
                .param(ACCOUNT_PARAM, TEST_ACCOUNT_NO)
                .param(AMOUNT_PARAM, TEST_AMOUNT))
            .andDo(print())
            .andExpect(forwardedUrl("/WEB-INF/views/success.jsp"))
            .andExpect(status().is(200));
    }
}