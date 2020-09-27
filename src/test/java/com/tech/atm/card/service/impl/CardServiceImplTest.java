package com.tech.atm.card.service.impl;

import com.tech.atm.card.service.CardService;
import com.tech.atm.common.CommonServiceImplTest;
import com.tech.atm.model.Card;
import com.tech.atm.support.exception.BusinessRuntimeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class CardServiceImplTest extends CommonServiceImplTest {

    @Autowired
    CardService cardService;

    @Test
    public void checkExistCardByCardNumberOne()  {
        Long cardNumber = 100006L;
        Long cardNumberExistence = this.cardService.checkExistCardByCardNumber(cardNumber);

        assertEquals(cardNumberExistence, 1L);
    }

    @Test
    public void checkExistCardByCardNumberZero()  {
        Long cardNumber = 100089L;

        assertThrows(BusinessRuntimeException.class, () -> {
            this.cardService.checkExistCardByCardNumber(cardNumber);
        });
    }


    @Test
    public void checkIfPinCorrectNull() {
        Card card = new Card();
        card.setCardNumber(cardNumbers.stream().count() > 0 ? cardNumbers.get(0) : null);
        card.setPin(null);

        assertThrows(BusinessRuntimeException.class, () -> {
            this.cardService.checkIfPinCorrect(card);
        });
    }

    @Test
    public void checkIfPinCorrectTrue() {
        Card card = new Card();
        card.setCardNumber(cardNumbers.stream().count() > 0 ? cardNumbers.get(0) : null);
        card.setPin(1223L);
        Card cardFromDb = this.cardService.checkIfPinCorrect(card);

        assertNotNull(cardFromDb);
        assertEquals(cardFromDb, card);
    }

    @Test
    public void checkIfPinCorrectFalse() {
        Card card = new Card();
        card.setCardNumber(cardNumbers.stream().count() > 0 ? cardNumbers.get(0) : null);
        card.setPin(1235L);

        assertThrows(BusinessRuntimeException.class, () -> {
            this.cardService.checkIfPinCorrect(card);
        });

    }
}
