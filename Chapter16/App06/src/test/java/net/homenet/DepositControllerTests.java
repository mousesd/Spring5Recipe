package net.homenet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.*;

public class DepositControllerTests {
    private static final String TEST_ACCOUNT_NO = "1234";
    private static final double TEST_AMOUNT = 50;

    private AccountService accountService;
    private DepositController depositController;

    @Before
    public void setUp() throws Exception {
        accountService = Mockito.mock(AccountService.class);
        depositController = new DepositController(accountService);
    }

    @Test
    public void deposit() {
        //# Setup
        Mockito.when(accountService.getBalance(TEST_ACCOUNT_NO)).thenReturn(150.0);
        ModelMap model = new ModelMap();

        //# Execute
        String viewName = depositController.deposit(TEST_ACCOUNT_NO, TEST_AMOUNT, model);

        //# Assert or Verify
        assertEquals(viewName, "success");
        assertEquals(model.get("accountNo"), TEST_ACCOUNT_NO);
        assertEquals(model.get("balance"), 150.0);
    }
}
