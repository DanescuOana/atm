package com.tech.atm.account.web;

import com.tech.atm.account.service.AccountService;
import com.tech.atm.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private static final Logger LOG =   LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/load-accounts")
    public List<Account> loadAccounts(){
        LOG.info("******* calling REST endpoint /account/load-accounts Method for test purposes");
        return this.accountService.loadAccounts();
    }
}
