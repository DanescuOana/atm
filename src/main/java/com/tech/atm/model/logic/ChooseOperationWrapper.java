package com.tech.atm.model.logic;

import com.tech.atm.model.Account;

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
