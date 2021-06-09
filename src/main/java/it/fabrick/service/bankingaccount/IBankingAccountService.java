package it.fabrick.service.bankingaccount;

import it.fabrick.entity.cashAccountBalance.response.CashAccountBalancePayload;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactionsList;

public interface IBankingAccountService {

    CashAccountBalancePayload getCashAccountBalance();

    CashAccountTransactionsList getCashAccountTransactions(String fromAccountingDate, String toAccountingDate);

    CashAccountTransactionsList storeCashAccountTransactions(CashAccountTransactionsList cashAccountTransactionsList);
}
