package it.fabrick.service.bankingaccount;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.application.Application;
import it.fabrick.entity.cashAccountBalance.response.CashAccountBalancePayload;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactionsList;
import it.fabrick.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BankingAccountServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBankingAccountService bas;

    @Value("classpath:mocks/cash_account_transactions.json")
    private Resource cashAccountTransactionsMock;

    @Test
    public void getCashAccountBalanceTest() throws Exception {

        when(bas.getCashAccountBalance())
            .thenReturn(CashAccountBalancePayload
                .builder()
                .balance("80")
                .build());

        assertThat(mockMvc.perform(
            get("/fabrick/cashAccountBalance"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString(), is("{\"balance\":\"80\"}"));

    }

    @Test
    public void getCashAccountTransactionsTest() throws Exception {

        CashAccountTransactionsList cashAccountTransactionsList = new ObjectMapper()
            .readValue(cashAccountTransactionsMock.getInputStream(),
                new TypeReference<>() {
                });

        when(bas.getCashAccountTransactions(anyString(), anyString()))
            .thenReturn(cashAccountTransactionsList);

        when(bas.storeCashAccountTransactions(any(CashAccountTransactionsList.class)))
            .thenReturn(cashAccountTransactionsList);

        String response = mockMvc.perform(
            get("/fabrick/cashAccountTransactions")
                .param("fromAccountingDate", "2021-06-09")
                .param("toAccountingDate", "2021-06-10"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        assertThat(JsonUtils.asPojo(response, CashAccountTransactionsList.class), is(cashAccountTransactionsList));

    }
}

