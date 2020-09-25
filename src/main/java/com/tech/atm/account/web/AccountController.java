package com.tech.atm.account.web;

import com.tech.atm.account.service.AccountService;
import com.tech.atm.model.Account;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private static final Logger LOG =   LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/load-accounts-by-card-number")
    public List<Account> loadAccountsForCardNumber(@RequestParam Long cardNumber){
        LOG.info("******* calling REST endpoint /account/load-accounts-by-card-number");
        return this.accountService.loadDetailsAccountsByCardNumber(cardNumber);
    }

    @PostMapping("/withdraw-money-from-account")
    public Boolean withdrawMoneyFromAccount(@RequestBody Account account, @RequestParam Long amount){
        LOG.info("******* calling REST endpoint /account/withdraw-money-from-account");
        return this.accountService.withdrawMoneyFromAccount(account, amount);
    }
}
