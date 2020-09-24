package com.tech.atm.account.service.impl;

import com.tech.atm.account.dao.AccountDao;
import com.tech.atm.account.service.AccountService;
import com.tech.atm.account.web.AccountController;
import com.tech.atm.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOG =   LoggerFactory.getLogger(AccountServiceImpl.class);
    private AccountDao accountDao;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> loadAccounts() {
        return this.accountDao.loadAccounts();
    }
}
