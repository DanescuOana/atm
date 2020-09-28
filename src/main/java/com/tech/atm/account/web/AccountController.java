package com.tech.atm.account.web;

import com.tech.atm.account.service.AccountService;
import com.tech.atm.domain.vo.Account;
import com.tech.atm.domain.logic.ChooseOperationWrapper;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private static final Logger LOG =   LoggerFactory.getLogger(AccountController.class);

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public List<Account> loadAccountsForCardNumber(@RequestParam Long cardNumber){
        LOG.info("******* calling REST endpoint /account/accounts");
        return this.accountService.loadDetailsAccountsByCardNumber(cardNumber);
    }

    @PostMapping("/withdraw")
    public Boolean withdrawMoneyFromAccount(@RequestBody Account account, @RequestParam Long amount){
        LOG.info("******* calling REST endpoint /account/withdraw");
        return this.accountService.withdrawMoneyFromAccount(account, amount);
    }

    @PostMapping("/can-withdraw")
    public Boolean accountCanBeWithdaw(@RequestParam String iban){
        LOG.info("******* calling REST endpoint /account/can-withdraw");
        return this.accountService.accountCanBeWithdaw(iban);
    }

    @PostMapping("/operation")
    public Boolean chooseOperation(@RequestBody ChooseOperationWrapper operationWrapper){
        LOG.info("******* calling REST endpoint /account/operation");
        return this.accountService.chooseOperation(operationWrapper.getAccount(), operationWrapper.getAtmOperation(), operationWrapper.getAmount());
    }
}
