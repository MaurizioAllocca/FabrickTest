package it.fabrick.service;

import it.fabrick.entity.cashAccountBalance.response.CashAccountBalancePayload;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactionsList;
import it.fabrick.entity.moneyTransfer.request.MoneyTransferRequest;
import it.fabrick.entity.moneyTransfer.response.MoneyTransferPayload;

public interface FabrickService {

    CashAccountBalancePayload getCashAccountBalance();

    CashAccountTransactionsList getCashAccountTransactions(String fromAccountingDate, String toAccountingDate);

    CashAccountTransactionsList storeCashAccountTransactions(CashAccountTransactionsList cashAccountTransactionsList);

    MoneyTransferPayload createMoneyTransfer(MoneyTransferRequest moneyTransferRequest);
}
