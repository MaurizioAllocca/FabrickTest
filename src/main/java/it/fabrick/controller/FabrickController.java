package it.fabrick.controller;

import it.fabrick.entity.cashAccountBalance.response.CashAccountBalancePayload;
import it.fabrick.entity.cashAccountTransactions.response.CashAccountTransactionsList;
import it.fabrick.entity.moneyTransfer.request.MoneyTransferRequest;
import it.fabrick.entity.moneyTransfer.response.MoneyTransferPayload;
import it.fabrick.service.FabrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fabrick")
public class FabrickController {

    @Autowired
    private FabrickService fs;

    @GetMapping("/cashAccountBalance")
    public CashAccountBalancePayload getCashAccountBalance() {
        return fs.getCashAccountBalance();
    }

    @GetMapping("/cashAccountTransactions")
    public CashAccountTransactionsList getCashAccountTransactions(
            @RequestParam(required = false) String fromAccountingDate,
            @RequestParam(required = false) String toAccountingDate
    ) {
        return fs.storeCashAccountTransactions(
            fs.getCashAccountTransactions(fromAccountingDate, toAccountingDate));
    }

    @PostMapping("/moneyTransfer")
    public MoneyTransferPayload createMoneyTransfer(
            @RequestBody MoneyTransferRequest moneyTransferRequest) {
        return fs.createMoneyTransfer(moneyTransferRequest);
    }
}
