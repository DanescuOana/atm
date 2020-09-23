package com.tech.atm.model;

import java.text.DecimalFormat;

public class AccountType {

    private Long id;
    private String accountType;
    private Boolean possibilityToExtractFromATM;
    private DecimalFormat moneyDecimalFormat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Boolean getPossibilityToExtractFromATM() {
        return possibilityToExtractFromATM;
    }

    public void setPossibilityToExtractFromATM(Boolean possibilityToExtractFromATM) {
        this.possibilityToExtractFromATM = possibilityToExtractFromATM;
    }

    public DecimalFormat getMoneyDecimalFormat() {
        return moneyDecimalFormat;
    }

    public void setMoneyDecimalFormat(DecimalFormat moneyDecimalFormat) {
        this.moneyDecimalFormat = moneyDecimalFormat;
    }
}
