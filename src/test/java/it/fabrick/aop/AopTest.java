package it.fabrick.aop;

import it.fabrick.Application;
import it.fabrick.entity.cashAccountBalance.response.CashAccountBalancePayload;
import it.fabrick.service.bankingaccount.IBankingAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AopTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBankingAccountService bas;

    @SpyBean
    @Autowired
    private Aop aop;

    @Test
    public void logControllerMethodEnteringTest() throws Exception {
        when(bas.getCashAccountBalance(anyString()))
            .thenReturn(CashAccountBalancePayload
                .builder()
                .balance("80")
                .build());

        mockMvc.perform(
            get("/fabrick/cashAccountBalance"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        verify(aop, times(1)).logControllerMethodEntering(any());
    }

    @Test
    public void logControllerMethodExitingTest() throws Exception {
        when(bas.getCashAccountBalance(anyString()))
            .thenReturn(CashAccountBalancePayload
                .builder()
                .balance("80")
                .build());

        mockMvc.perform(
            get("/fabrick/cashAccountBalance"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        verify(aop, times(1)).logControllerMethodExiting(any());
    }

}
