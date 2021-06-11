package it.fabrick.service.bankingpayments;

import it.fabrick.entity.moneyTransfer.request.MoneyTransferRequest;
import it.fabrick.entity.moneyTransfer.response.MoneyTransferPayload;
import it.fabrick.entity.moneyTransfer.response.MoneyTransferResponse;
import it.fabrick.utils.HttpUtils;
import it.fabrick.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class BankingPaymentsService implements IBankingPaymentsService {

    @Value("${uri.baseUrl}")
    private String baseUrl;

    @Value("${uri.moneyTransfer}")
    private String moneyTransfer;

    @Override
    public MoneyTransferPayload createMoneyTransfer(
        MoneyTransferRequest moneyTransferRequest, String accountId
    ) {
        return RestTemplateUtils.makeRequest(
            HttpUtils
                .builder()
                .domain(baseUrl)
                .uri(moneyTransfer)
                .pathVariables(new String[]{accountId})
                .build()
                .createStringUrl(),
            HttpMethod.POST,
            new HttpEntity<>(moneyTransferRequest, new HttpHeaders()),
            MoneyTransferResponse.class)
            .getPayload();
    }
}
