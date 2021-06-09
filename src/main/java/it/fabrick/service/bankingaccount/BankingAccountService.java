package it.fabrick.service.bankingaccount;

import it.fabrick.entity.cashAccountBalance.response.CashAccountBalance;
import it.fabrick.entity.cashAccountBalance.response.CashAccountBalancePayload;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactions;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactionsList;
import it.fabrick.repository.TransactionRepository;
import it.fabrick.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BankingAccountService implements IBankingAccountService {

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
        return cashAccountTransactionsList;
    }

}
