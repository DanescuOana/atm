package com.tech.atm.account.service.impl;

import com.tech.atm.account.dao.AccountDao;
import com.tech.atm.account.service.AccountService;
import com.tech.atm.model.Account;
import com.tech.atm.model.logic.ATMOperation;
import com.tech.atm.model.logic.BusinessMessage;
import com.tech.atm.support.exception.BusinessRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

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
            Integer process = this.accountDao.updateAmountIntoDatabase(account.getIban(), remainAmount);
            processed = Boolean.valueOf(String.valueOf(process));
            LOG.error("******** Balance after withdraw = "+remainAmount);
        }

        return processed;
    }

    @Transactional
    @Override
    public void chooseOperation(Account account, ATMOperation operation) {
        LOG.info("****** choose operation or account");
        Long amount = 0L;
        if(ATMOperation.WITHDRAW.equals(operation)) {
            LOG.info("****** WITHDRAW operation");
            LOG.info("***** check if account have the possibility to withdraw.");
            if(this.accountCanBeWithdaw(account.getIban())) {
                LOG.info("******* proceed with printing the next operations");
                amount = this.processAmountInserted();
                LOG.info("******* proceed with withdrawing acccount and print the results.");
                this.withdrawMoneyFromAccount(account, amount);
            } else {
                LOG.error("*********This iban="+account.getIban()+ " does not have posibility to withdraw.");
                throw new BusinessRuntimeException(new BusinessMessage("account.type.no.withdraw"));
            }
        } else if(ATMOperation.DEPOSIT.equals(operation)) {
            LOG.info("****** DEPOSIT operation");
            LOG.info("******* iban="+account.getIban());
            amount = this.processAmountInserted();
            LOG.info("********* check amount and then deposit.");
            BigDecimal totalAmount = account.getBalanceAccount().add(new BigDecimal(amount));
            Integer process = this.accountDao.updateAmountIntoDatabase(account.getIban(), totalAmount);
            LOG.info("************amount have been deposited into account iban="+account.getIban());

        } else if(ATMOperation.CANCEL.equals(operation)) {
            LOG.info("****** CANCEL operation");
            LOG.info("******* return card to owner.");
            LOG.info("********Bye bye "+account.getUser().getName());

        } else {
            LOG.error("****** UNKNOWN operation.");
            throw new BusinessRuntimeException(new BusinessMessage("account.operation.unknown"));
        }
    }

    @Override
    public Boolean accountCanBeWithdaw(String iban) {
        Boolean canWithdraw = true;
        LOG.info("****** Check if account have the possibility to withdraw iban = "+iban);
        canWithdraw = Boolean.valueOf(String.valueOf(this.accountDao.checkIfAcountCanWithdraw(iban)));
        LOG.info("******iban = "+iban+ " can withdraw: "+canWithdraw);
        return canWithdraw;
    }

    @Override
    public Long processAmountInserted() {
        LOG.info("*************insert amount:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLong();
    }
}
