package it.fabrick.controller;

import it.fabrick.entity.CashAccountBalancePayload;
import it.fabrick.entity.CashAccountTransactionsList;
import it.fabrick.entity.request.MoneyTransferRequest;
import it.fabrick.entity.response.MoneyTransferPayload;
import it.fabrick.entity.response.MoneyTransferResponse;
import it.fabrick.repository.TransactionRepository;
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
