package com.tech.atm.account.dao.impl;

import com.tech.atm.account.dao.AccountDao;
import com.tech.atm.model.Account;
import com.tech.atm.support.mybatis.MyBatisSupport;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountDaoImpl extends MyBatisSupport implements AccountDao {

    @Override
    public List<Account> loadDetailsAccountsByCardNumber(Long cardNumber) {
        return getSqlSession().selectList("account.loadDetailsAccountsByCardNumber", cardNumber);
    }

    @Override
    public Integer checkIfUserCanWithdraw(String iban, Long amount) {
        Map<String, Object> params = new HashMap<>();
        params.put("iban", iban);
        params.put("amount", amount);
        return getSqlSession().selectOne("account.checkIfUserCanWithdraw", params);
    }

    @Override
    public Integer updateAmountIntoDatabase(String iban, BigDecimal amount) {
        Map<String, Object> params = new HashMap<>();
        params.put("iban", iban);
        params.put("amount", amount);
        return getSqlSession().selectOne("account.updateAmountIntoDatabase", params);
    }

    @Override
    public Integer checkIfAcountCanWithdraw(String iban) {
        return getSqlSession().selectOne("account.checkIfAcountCanWithdraw", iban);
    }
}
