package com.tech.atm.account.dao.impl;

import com.tech.atm.account.dao.AccountDao;
import com.tech.atm.model.Account;
import com.tech.atm.support.MyBatisSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl extends MyBatisSupport implements AccountDao {

    @Override
    public List<Account> loadAccounts() {
        return getSqlSession().selectList("account.loadAccounts");
    }
}
