package com.tech.atm.domain.logic;

import com.tech.atm.domain.vo.Account;

public class ChooseOperationWrapper {
    private Account account;
    private ATMOperation atmOperation;
    private Long amount;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ATMOperation getAtmOperation() {
        return atmOperation;
    }

    public void setAtmOperation(ATMOperation atmOperation) {
        this.atmOperation = atmOperation;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
