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

    @GetMapping("/cashAccountBalance")
    public CashAccountBalancePayload getCashAccountBalance() {
        return bas.getCashAccountBalance();
    }

    @GetMapping("/cashAccountTransactions")
    public CashAccountTransactionsList getCashAccountTransactions(
            @RequestParam(required = false) String fromAccountingDate,
            @RequestParam(required = false) String toAccountingDate
    ) {
        return bas.storeCashAccountTransactions(
                bas.getCashAccountTransactions(fromAccountingDate, toAccountingDate));
    }

    @PostMapping("/moneyTransfer")
    public MoneyTransferPayload createMoneyTransfer(
            @RequestBody MoneyTransferRequest moneyTransferRequest) {
        return bps.createMoneyTransfer(moneyTransferRequest);
    }
}
