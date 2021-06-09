package it.fabrick.service;

import it.fabrick.entity.CashAccountBalancePayload;
import it.fabrick.entity.CashAccountTransactionsList;
import it.fabrick.entity.CashAccountTransactionsPayload;

public interface FabrickService {

    CashAccountBalancePayload getCashAccountBalance();

    CashAccountTransactionsList getCashAccountTransactions(String fromAccountingDate, String toAccountingDate);

    CashAccountTransactionsList storeCashAccountTransactions(CashAccountTransactionsList cashAccountTransactionsList);
}
