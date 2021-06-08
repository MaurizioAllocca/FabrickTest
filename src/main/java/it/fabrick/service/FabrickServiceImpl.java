package it.fabrick.service;

import com.fasterxml.jackson.databind.JsonNode;
import it.fabrick.entity.CashAccountBalance;
import it.fabrick.entity.CashAccountBalancePayload;
import it.fabrick.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    private HttpEntity he;

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
//        return rt.exchange(baseUrl + cashAccountBalance, HttpMethod.GET, he, CashAccountBalance.class).getBody();

        return rt
            .exchange(
                HttpUtils.builder()
                    .domain(baseUrl)
                    .uri(cashAccountBalance)
                    .pathVariables(new String[]{accountId})
                    .build()
                    .createStringUrl(),
                HttpMethod.GET,
                he,
                CashAccountBalance.class)
            .getBody()
            .getPayload();
    }

    @Override
    public JsonNode getCashAccountTransactions(String fromAccountingDate, String toAccountingDate) {
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
                        he,
                        JsonNode.class)
                .getBody();
    }
}
