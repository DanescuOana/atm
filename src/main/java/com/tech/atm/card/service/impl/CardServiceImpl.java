package com.tech.atm.card.service.impl;

import com.tech.atm.account.dao.AccountDao;
import com.tech.atm.card.dao.CardDao;
import com.tech.atm.card.service.CardService;
import com.tech.atm.model.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    private static final Logger LOG =   LoggerFactory.getLogger(CardServiceImpl.class);
    private CardDao cardDao;

    @Autowired
    public CardServiceImpl(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    @Override
    public Long checkExistCardByCardNumber(Long cardNumber) {
        Long cardNumberExistence = this.cardDao.checkExistCardByCardNumber(cardNumber);
        if(cardNumber.equals(0)) {
            LOG.warn("******Warning! There is no cardNumber. Reinsert the cardNumber");
            System.out.println("Please re-type the cardNumber carefully!");

        } else if(cardNumber.equals(1)) {
            LOG.warn("******CardNumber was found, proceed with the pin input.");
            System.out.println("Please insert your pin number!");
        } else {
            LOG.error("Something unexpected happened, there is more than one.");
        }


        return cardNumberExistence;
    }

    @Override
    public Card checkIfPinCorrect(Card card) {
        return this.cardDao.checkIfPinCorrect(card);
    }
}
