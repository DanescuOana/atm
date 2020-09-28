package com.tech.atm.card.dao;

import com.tech.atm.domain.vo.Card;

public interface CardDao {

    Long checkExistCardByCardNumber(Long cardNumber);
    Card checkIfPinCorrect(Card card);
}
