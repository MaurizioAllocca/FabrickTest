package it.fabrick.service;

import com.fasterxml.jackson.databind.JsonNode;
import it.fabrick.entity.CashAccountBalance;
import it.fabrick.entity.CashAccountBalancePayload;

public interface FabrickService {

    CashAccountBalancePayload getCashAccountBalance();

    JsonNode getCashAccountTransactions(String fromAccountingDate, String toAccountingDate);
}
