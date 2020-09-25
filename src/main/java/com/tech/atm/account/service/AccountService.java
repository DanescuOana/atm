package com.tech.atm.account.service;

import com.tech.atm.model.Account;

import java.util.List;

public interface AccountService{

    List<Account> loadDetailsAccountsByCardNumber(Long cardNumber);
    Boolean withdrawMoneyFromAccount(Account account, Long amount);
}
