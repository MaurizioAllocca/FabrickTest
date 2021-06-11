package it.fabrick.service.bankingaccount;

import it.fabrick.entity.cashAccountBalance.response.CashAccountBalancePayload;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactionsPayload;

public interface IBankingAccountService {

    CashAccountBalancePayload getCashAccountBalance(String accountId);

    CashAccountTransactionsPayload getCashAccountTransactions(
        String fromAccountingDate, String toAccountingDate, String accountId);

    CashAccountTransactionsPayload storeCashAccountTransactions(
        CashAccountTransactionsPayload cashAccountTransactionsList);
}
