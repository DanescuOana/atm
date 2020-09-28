package com.tech.atm.account.dao;

import com.tech.atm.domain.vo.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    List<Account> loadDetailsAccountsByCardNumber(Long cardNumber);
    Integer checkIfUserCanWithdraw(String iban, Long amount);
    Integer updateAmountIntoDatabase(String iban, BigDecimal amount);
    Integer checkIfAcountCanWithdraw(String iban);
}
