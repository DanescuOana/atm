package com.tech.atm.model;

import java.util.List;

public class Card {
    private Long cardNumber;
    private List<Account> accountsAttachedToCard;
    private Long pin;
    private String cardOwnerName;
    private Account currentAccount;

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<Account> getAccountsAttachedToCard() {
        return accountsAttachedToCard;
    }

    public void setAccountsAttachedToCard(List<Account> accountsAttachedToCard) {
        this.accountsAttachedToCard = accountsAttachedToCard;
    }

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
        this.pin = pin;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }
}
