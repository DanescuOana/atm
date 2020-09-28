package com.tech.atm.account.service.impl;

import com.tech.atm.account.dao.AccountDao;
import com.tech.atm.account.service.AccountService;
import com.tech.atm.domain.vo.Account;
import com.tech.atm.domain.logic.ATMOperation;
import com.tech.atm.domain.logic.BusinessMessage;
import com.tech.atm.support.exception.BusinessRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOG =   LoggerFactory.getLogger(AccountServiceImpl.class);
    private AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> loadDetailsAccountsByCardNumber(Long cardNumber) {
        LOG.info("****Loading accounts with cardNumber = "+cardNumber);
        List<Account> accounts = this.accountDao.loadDetailsAccountsByCardNumber(cardNumber);

        if(accounts.stream().noneMatch(account -> account.getAttachedCard().getCardNumber().equals(cardNumber))) {
            LOG.info("****No accounts has been found for this cardNumber");
            throw new BusinessRuntimeException(new BusinessMessage("account.no.card"));
        } else {
            accounts.stream().forEach(account -> LOG.info("fetching account: "+account.getIban()));
        }

        return accounts;
    }

    @Override
    public Boolean withdrawMoneyFromAccount(Account account, Long amount) {
        LOG.info("****** account iban = "+account.getIban());
        LOG.info("******** account balance = "+account.getBalanceAccount());
        LOG.info("****** withdraw Long amount=" +amount);

        Boolean processed = false;

        if(this.accountDao.checkIfUserCanWithdraw(account.getIban(), amount).equals(0)) {
            LOG.error("******** withdraw amount is bigger than the balance.");
            throw new BusinessRuntimeException(new BusinessMessage("account.insufficient.fund"));
        } else {
            BigDecimal remainAmount = account.getBalanceAccount().subtract(new BigDecimal(amount));
            this.accountDao.updateAmountIntoDatabase(account.getIban(), remainAmount);
            processed = true;
            LOG.error("******** Balance after withdraw = "+remainAmount);
        }

        return processed;
    }

    @Transactional
    @Override
    public Boolean chooseOperation(Account account, ATMOperation operation, Long amount) {
        LOG.info("****** choose operation or account");
        if(ATMOperation.WITHDRAW.equals(operation)) {
            LOG.info("****** WITHDRAW operation");
            LOG.info("***** check if account have the possibility to withdraw.");
            if(this.accountCanBeWithdaw(account.getIban())) {
                LOG.info("******* proceed with printing the next operations");
                LOG.info("******* proceed with withdrawing acccount and print the results.");
                return amount != null ?  this.withdrawMoneyFromAccount(account, amount) :  false;
            } else {
                LOG.error("*********This iban="+account.getIban()+ " does not have posibility to withdraw.");
                throw new BusinessRuntimeException(new BusinessMessage("account.type.no.withdraw"));
            }
        } else if(ATMOperation.DEPOSIT.equals(operation)) {
            LOG.info("****** DEPOSIT operation");
            LOG.info("******* iban="+account.getIban());
            LOG.info("********* check amount and then deposit.");

            if(amount != null) {
                BigDecimal totalAmount = account.getBalanceAccount().add(new BigDecimal(amount));
                this.accountDao.updateAmountIntoDatabase(account.getIban(), totalAmount);
                LOG.info("************amount have been deposited into account iban="+account.getIban());
                return true;
            } else {
                throw new BusinessRuntimeException(new BusinessMessage("account.operation.failed"));
            }
        } else if(ATMOperation.CANCEL.equals(operation)) {
            LOG.info("****** CANCEL operation");
            LOG.info("******* return card to owner.");
            return true;

        } else {
            LOG.error("****** UNKNOWN operation.");
            throw new BusinessRuntimeException(new BusinessMessage("account.operation.unknown"));
        }
    }

    @Override
    public Boolean accountCanBeWithdaw(String iban) {
        Boolean canWithdraw;
        LOG.info("****** Check if account have the possibility to withdraw iban = "+iban);

        if(iban == null) {
            throw new BusinessRuntimeException(new BusinessMessage("unexpected.error"));
        }
        canWithdraw = this.accountDao.checkIfAcountCanWithdraw(iban) == 0 ? false: true;
        LOG.info("******iban = "+iban+ " can withdraw: "+canWithdraw);
        return canWithdraw;
    }
}
