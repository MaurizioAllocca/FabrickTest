package it.fabrick.controller;

import it.fabrick.entity.cashAccountBalance.response.CashAccountBalancePayload;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactionsPayload;
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
        @PathVariable String accountId
    ) {
        return bas.getCashAccountBalance(accountId);
    }

    @GetMapping("/cashAccountTransactions/{accountId}")
    public CashAccountTransactionsPayload getCashAccountTransactions(
        @PathVariable String accountId,
        @RequestParam String fromAccountingDate,
        @RequestParam String toAccountingDate
    ) {
        return bas.storeCashAccountTransactions(
            bas.getCashAccountTransactions(fromAccountingDate, toAccountingDate, accountId));
    }

    @PostMapping("/moneyTransfer/{accountId}")
    public MoneyTransferPayload createMoneyTransfer(
        @PathVariable String accountId,
        @RequestBody MoneyTransferRequest moneyTransferRequest
    ) {
        return bps.createMoneyTransfer(moneyTransferRequest, accountId);
    }
}
