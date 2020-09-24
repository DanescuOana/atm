package com.tech.atm.card.dao.impl;

import com.tech.atm.card.dao.CardDao;
import com.tech.atm.model.Card;
import com.tech.atm.support.mybatis.MyBatisSupport;
import org.springframework.stereotype.Repository;

@Repository
public class CardDaoImpl extends MyBatisSupport implements CardDao {

    @Override
    public Long checkExistCardByCardNumber(Long cardNumber) {
        return getSqlSession().selectOne("card.checkExistCardByCardNumber", cardNumber);
    }

    @Override
    public Card checkIfPinCorrect(Card card) {
        return getSqlSession().selectOne("card.checkIfPinCorrect", card);
    }
}
