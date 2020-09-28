package com.tech.atm.card.service;

import com.tech.atm.domain.vo.Card;
import com.tech.atm.support.exception.BusinessRuntimeException;

public interface CardService {

    Long checkExistCardByCardNumber(Long cardNumber) throws BusinessRuntimeException;
    Card checkIfPinCorrect(Card card);
}
