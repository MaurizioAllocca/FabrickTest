package it.fabrick.service;

import it.fabrick.entity.*;
import it.fabrick.entity.request.MoneyTransferRequest;
import it.fabrick.entity.response.MoneyTransferPayload;
import it.fabrick.entity.response.MoneyTransferResponse;
import it.fabrick.repository.TransactionRepository;
import it.fabrick.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
public class FabrickServiceImpl implements FabrickService {

    @Autowired
    private RestTemplate rt;

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${uri.baseUrl}")
    private String baseUrl;

    @Value("${uri.cashAccountBalance}")
    private String cashAccountBalance;

    @Value("${uri.cashAccountTransactions}")
    private String cashAccountTransactions;

    @Value("${uri.moneyTransfer}")
    private String moneyTransfer;

    @Value("${accountId}")
    private String accountId;

    @Override
    public CashAccountBalancePayload getCashAccountBalance() {
        return rt
            .exchange(
                HttpUtils.builder()
                    .domain(baseUrl)
                    .uri(cashAccountBalance)
                    .pathVariables(new String[]{accountId})
                    .build()
                    .createStringUrl(),
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                CashAccountBalance.class)
            .getBody()
            .getPayload();
    }

    @Override
    public CashAccountTransactionsList getCashAccountTransactions(String fromAccountingDate, String toAccountingDate) {
        return rt
            .exchange(
                HttpUtils.builder()
                    .domain(baseUrl)
                    .uri(cashAccountTransactions)
                    .pathVariables(new String[]{accountId})
                    .queryParams(new LinkedMultiValueMap<>(Map.of(
                        "fromAccountingDate", Collections.singletonList(fromAccountingDate),
                        "toAccountingDate", Collections.singletonList(toAccountingDate)))
                    )
                    .build()
                    .createStringUrl(),
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    CashAccountTransactions.class)
            .getBody()
            .getPayload();
    }

    @Override
    public CashAccountTransactionsList storeCashAccountTransactions(CashAccountTransactionsList cashAccountTransactionsList) {
        transactionRepository.saveAll(cashAccountTransactionsList.getList());
        transactionRepository.findAll().stream().forEach(y -> System.out.println("ID: " + y.getTransactionId()));
        return cashAccountTransactionsList;
    }

    @Override
    public MoneyTransferPayload createMoneyTransfer(MoneyTransferRequest moneyTransferRequest) {
        return rt
                .exchange(
                        HttpUtils.builder()
                                .domain(baseUrl)
                                .uri(moneyTransfer)
                                .pathVariables(new String[]{accountId})
                                .build()
                                .createStringUrl(),
                        HttpMethod.POST,
                        new HttpEntity<>(moneyTransferRequest, new HttpHeaders()),
                        MoneyTransferResponse.class)
                .getBody()
                .getPayload();
    }
}
