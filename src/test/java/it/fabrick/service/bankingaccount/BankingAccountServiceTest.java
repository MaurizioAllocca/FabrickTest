package it.fabrick.service.bankingaccount;

import it.fabrick.application.Application;
import it.fabrick.entity.cashAccountBalance.response.CashAccountBalance;
import it.fabrick.entity.cashAccountBalance.response.CashAccountBalancePayload;
import it.fabrick.utils.HttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalToObject;
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
    }

