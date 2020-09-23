package com.tech.atm.account.web;

import com.tech.atm.account.service.AccountService;
import com.tech.atm.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/load-accounts")
    public List<Account> loadAccounts(){
        return this.accountService.loadAccounts();
    }
}
