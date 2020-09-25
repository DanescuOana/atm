package com.tech.atm.account.dao;

import com.tech.atm.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    List<Account> loadDetailsAccountsByCardNumber(Long cardNumber);
    Boolean checkIfUserCanWithdraw(String iban, Long amount);
    Boolean updateAmountIntoDatabase(String iban, BigDecimal amount);
}
