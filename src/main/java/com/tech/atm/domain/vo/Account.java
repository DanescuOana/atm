package com.tech.atm.domain.vo;

import java.math.BigDecimal;

public class Account {

    private Long id;
    private String iban;
    private BigDecimal balanceAccount;
    private AccountType accountType;
    private Card attachedCard;
    private User user;
    private Boolean canWithdraw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(BigDecimal balanceAccount) {
        this.balanceAccount = balanceAccount;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Card getAttachedCard() {
        return attachedCard;
    }

    public void setAttachedCard(Card attachedCard) {
        this.attachedCard = attachedCard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getCanWithdraw() {
        return canWithdraw;
    }

    public void setCanWithdraw(Boolean canWithdraw) {
        this.canWithdraw = canWithdraw;
    }
}
