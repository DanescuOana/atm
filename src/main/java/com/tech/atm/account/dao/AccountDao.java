package com.tech.atm.account.dao;

import com.tech.atm.model.Account;

import java.util.List;

public interface AccountDao {

    List<Account> loadAccounts();
}
