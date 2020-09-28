package com.tech.atm.domain.vo;

public class AccountType {

    private Long id;
    private String accountType;
    private Boolean possibilityToExtractFromATM;
    private Currency currency;

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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}

