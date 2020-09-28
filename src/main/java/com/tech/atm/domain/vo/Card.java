package com.tech.atm.domain.vo;

import java.util.Objects;

public class Card {
    private Long cardNumber;
    private Long pin;
    private User user;

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
        this.pin = pin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardNumber, card.cardNumber) &&
                Objects.equals(pin, card.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, pin);
    }
}
