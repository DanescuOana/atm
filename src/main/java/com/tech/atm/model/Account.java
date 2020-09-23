package com.tech.atm.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Account {

    private Long iban;
    private BigDecimal balanceAccount;
    private DecimalFormat moneyDecimalFormat;

    public Long getIban() {
        return iban;
    }

    public void setIban(Long iban) {
        this.iban = iban;
    }

    public BigDecimal getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(BigDecimal balanceAccount) {
        this.balanceAccount = balanceAccount;
    }

    public DecimalFormat getMoneyDecimalFormat() {
        return moneyDecimalFormat;
    }

    public void setMoneyDecimalFormat(DecimalFormat moneyDecimalFormat) {
        this.moneyDecimalFormat = moneyDecimalFormat;
    }
}
