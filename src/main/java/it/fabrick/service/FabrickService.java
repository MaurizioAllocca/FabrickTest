package it.fabrick.service;

import it.fabrick.entity.CashAccountBalancePayload;
import it.fabrick.entity.CashAccountTransactionsList;
import it.fabrick.entity.request.MoneyTransferRequest;
import it.fabrick.entity.response.MoneyTransferPayload;
import it.fabrick.entity.response.MoneyTransferResponse;

public interface FabrickService {

    CashAccountBalancePayload getCashAccountBalance();

    CashAccountTransactionsList getCashAccountTransactions(String fromAccountingDate, String toAccountingDate);

    CashAccountTransactionsList storeCashAccountTransactions(CashAccountTransactionsList cashAccountTransactionsList);

    MoneyTransferPayload createMoneyTransfer(MoneyTransferRequest moneyTransferRequest);
}
