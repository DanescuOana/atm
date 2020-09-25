package com.tech.atm.account.service.impl;

import com.tech.atm.account.dao.AccountDao;
import com.tech.atm.account.service.AccountService;
import com.tech.atm.model.Account;
import com.tech.atm.model.logic.BusinessMessage;
import com.tech.atm.support.exception.BusinessRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public List<Account> loadDetailsAccountsByCardNumber(Long cardNumber) {
        LOG.info("****Loading accounts with cardNumber = "+cardNumber);
        List<Account> accounts = this.accountDao.loadDetailsAccountsByCardNumber(cardNumber);

        accounts.stream().forEach(account -> LOG.info("fetching account: "+account.getIban()));
        return accounts;
    }

    @Override
    public Boolean withdrawMoneyFromAccount(Account account, Long amount) {
        LOG.info("****** account iban = "+account.getIban());
        LOG.info("******** account balance = "+account.getBalanceAccount());
        LOG.info("****** withdraw Long amount=" +amount);

        Boolean processed = false;

        if(this.accountDao.checkIfUserCanWithdraw(account.getIban(), amount).equals(false)) {
            LOG.error("******** withdraw amount is bigger than the balance.");
            throw new BusinessRuntimeException(new BusinessMessage("account.insufficient.fund"));
        } else {
            BigDecimal remainAmount = account.getBalanceAccount().subtract(new BigDecimal(amount));
            processed = this.accountDao.updateAmountIntoDatabase(account.getIban(), remainAmount);
            LOG.error("******** Balance after withdraw = "+remainAmount);
        }

        return processed;
    }
}
