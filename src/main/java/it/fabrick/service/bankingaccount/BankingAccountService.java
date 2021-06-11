package it.fabrick.service.bankingaccount;

import it.fabrick.entity.cashAccountBalance.response.CashAccountBalance;
import it.fabrick.entity.cashAccountBalance.response.CashAccountBalancePayload;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactions;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactionsPayload;
import it.fabrick.repository.TransactionRepository;
import it.fabrick.utils.HttpUtils;
import it.fabrick.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class BankingAccountService implements IBankingAccountService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${uri.baseUrl}")
    private String baseUrl;

    @Value("${uri.cashAccountBalance}")
    private String cashAccountBalance;

    @Value("${uri.cashAccountTransactions}")
    private String cashAccountTransactions;

    public CashAccountBalancePayload getCashAccountBalance(
        String accountId
    ) {
        return RestTemplateUtils.makeRequest(
            HttpUtils
                .builder()
                .domain(baseUrl)
                .uri(cashAccountBalance)
                .pathVariables(new String[]{accountId})
                .build()
                .createStringUrl(),
            HttpMethod.GET,
            new HttpEntity<>(new HttpHeaders()),
            CashAccountBalance.class)
            .getPayload();
    }

    @Override
    public CashAccountTransactionsPayload getCashAccountTransactions(
        String fromAccountingDate, String toAccountingDate, String accountId
    ) {
        return RestTemplateUtils.makeRequest(
            HttpUtils
                .builder()
                .domain(baseUrl)
                .uri(cashAccountTransactions)
                .pathVariables(new String[]{accountId})
                .queryParams(new LinkedMultiValueMap<>(Map.of(
                    "fromAccountingDate", Collections.singletonList(fromAccountingDate),
                    "toAccountingDate", Collections.singletonList(toAccountingDate))))
                .build()
                .createStringUrl(),
            HttpMethod.GET,
            new HttpEntity<>(new HttpHeaders()),
            CashAccountTransactions.class)
            .getPayload();
    }

    @Override
    public CashAccountTransactionsPayload storeCashAccountTransactions(
        CashAccountTransactionsPayload cashAccountTransactionsPayload
    ) {
        transactionRepository.saveAll(
            Optional.ofNullable(
                Optional.ofNullable(cashAccountTransactionsPayload)
                    .orElseGet(CashAccountTransactionsPayload::new).getList())
                .orElseGet(Collections::emptyList));
        return cashAccountTransactionsPayload;
    }

}
