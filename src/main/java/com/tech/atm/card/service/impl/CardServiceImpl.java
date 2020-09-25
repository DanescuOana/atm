package com.tech.atm.card.service.impl;

import com.tech.atm.card.dao.CardDao;
import com.tech.atm.card.service.CardService;
import com.tech.atm.model.Card;
import com.tech.atm.model.logic.BusinessMessage;
import com.tech.atm.support.exception.BusinessRuntimeException;
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
    public Long checkExistCardByCardNumber(Long cardNumber) throws BusinessRuntimeException {
        Long cardNumberExistence = this.cardDao.checkExistCardByCardNumber(cardNumber);
        if(cardNumberExistence == 0L) {
            LOG.error("******There is no cardNumber. Reinsert the cardNumber");
            throw new BusinessRuntimeException(new BusinessMessage("card.invalid.cardNumber"));
        } else if(cardNumberExistence == 1L) {
            LOG.info("******CardNumber was found, proceed with the pin input.");
            return cardNumberExistence;
        } else {
            LOG.error("****Something unexpected happened, there is more than one card number into dataabase.");
            throw new BusinessRuntimeException(new BusinessMessage("unexpected.error"));
        }
    }

    @Override
    public Card checkIfPinCorrect(Card card) {
        Card cardFromDb = this.cardDao.checkIfPinCorrect(card);
        if(cardFromDb.equals(null)) {
           LOG.error("**********this card is not found into database. Pin incorect.");
           throw new BusinessRuntimeException(new BusinessMessage("card.invalid.pin"));
        } else {
            LOG.info("******** pin correct! Proceed with printing the menu details.");
            return cardFromDb;
        }
    }
}
