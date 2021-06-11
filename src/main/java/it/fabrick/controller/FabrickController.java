package it.fabrick.controller;

import it.fabrick.entity.cashAccountBalance.response.CashAccountBalancePayload;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactionsList;
import it.fabrick.entity.moneyTransfer.request.MoneyTransferRequest;
import it.fabrick.entity.moneyTransfer.response.MoneyTransferPayload;
import it.fabrick.service.bankingaccount.IBankingAccountService;
import it.fabrick.service.bankingpayments.IBankingPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fabrick")
public class FabrickController {

    @Autowired
    private IBankingAccountService bas;
    @Autowired
    private IBankingPaymentsService bps;

    @GetMapping("/cashAccountBalance/{accountId}")
    public CashAccountBalancePayload getCashAccountBalance(
        @PathVariable String accountId) {
        return bas.getCashAccountBalance(accountId);
    }

    @GetMapping("/cashAccountTransactions/{accountId}")
    public CashAccountTransactionsList getCashAccountTransactions(
        @RequestParam String fromAccountingDate,
        @RequestParam String toAccountingDate,
        @PathVariable String accountId
    ) {
        return bas.storeCashAccountTransactions(
            bas.getCashAccountTransactions(fromAccountingDate, toAccountingDate, accountId));
    }

    @PostMapping("/moneyTransfer/{accountId}")
    public MoneyTransferPayload createMoneyTransfer(
        @RequestBody MoneyTransferRequest moneyTransferRequest,
        @PathVariable String accountId) {
        return bps.createMoneyTransfer(moneyTransferRequest, accountId);
    }
}
