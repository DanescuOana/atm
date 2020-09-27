package com.tech.atm.account.service;

import com.tech.atm.model.Account;
import com.tech.atm.model.logic.ATMOperation;
import com.tech.atm.support.exception.BusinessException;

import java.util.List;

public interface AccountService{

    List<Account> loadDetailsAccountsByCardNumber(Long cardNumber);
    Boolean withdrawMoneyFromAccount(Account account, Long amount);
    void chooseOperation(Account account, ATMOperation operation);
    Boolean accountCanBeWithdaw(String iban);
    Long processAmountInserted();
}
