package it.fabrick.controller;

import com.fasterxml.jackson.databind.JsonNode;
import it.fabrick.entity.CashAccountBalance;
import it.fabrick.entity.CashAccountBalancePayload;
import it.fabrick.repository.AccountRepository;
import it.fabrick.service.FabrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fabrick")
public class Controller {

    @Autowired
    private FabrickService fs;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/cashAccountBalance")
    public CashAccountBalancePayload getCashAccountBalance() {
        CashAccountBalancePayload cashAccountBalancePayload = fs.getCashAccountBalance();
//        accountRepository.save(account.getPayload());
//        accountRepository.findByIban("IT40L0326822311052923800661")
//                .forEach(account2 -> System.out.println(account2.getAccount()));
        return cashAccountBalancePayload;
    }

    @GetMapping("/cashAccountTransactions")
    public JsonNode getCashAccountTransactions(
            @RequestParam(required = false) String fromAccountingDate,
            @RequestParam(required = false) String toAccountingDate
    ) {
        return fs.getCashAccountTransactions(fromAccountingDate, toAccountingDate);
    }
}
