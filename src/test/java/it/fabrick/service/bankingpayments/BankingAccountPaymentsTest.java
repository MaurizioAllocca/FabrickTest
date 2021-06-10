package it.fabrick.service.bankingpayments;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.application.Application;
import it.fabrick.entity.moneyTransfer.request.MoneyTransferRequest;
import it.fabrick.entity.moneyTransfer.response.MoneyTransferPayload;
import it.fabrick.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BankingAccountPaymentsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBankingPaymentsService bps;

    @Value("classpath:mocks/money_transfer_request.json")
    private Resource moneyTransferRequestMock;

    @Value("classpath:mocks/money_transfer_payload.json")
    private Resource moneyTransferPayloadMock;

    @Test
    public void createMoneyTransferTest() throws Exception {

        MoneyTransferPayload moneyTransferPayload = new ObjectMapper()
            .readValue(moneyTransferPayloadMock.getInputStream(),
                new TypeReference<>() {
                });

        doReturn(moneyTransferPayload).when(bps).createMoneyTransfer(any(MoneyTransferRequest.class));

        String response = mockMvc.perform(
            post("/fabrick/moneyTransfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(moneyTransferRequestMock.getInputStream().readAllBytes()))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        assertThat(JsonUtils.asPojo(response, MoneyTransferPayload.class), is(moneyTransferPayload));

    }
}

