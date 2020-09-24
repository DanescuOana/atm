package com.tech.atm.card.service;

import com.tech.atm.model.Card;

public interface CardService {

    Long checkExistCardByCardNumber(Long cardNumber);
    Card checkIfPinCorrect(Card card);
}
