package com.tech.atm.card.web;

import com.tech.atm.card.service.CardService;
import com.tech.atm.model.Card;
import com.tech.atm.support.exception.BusinessException;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/card")
public class CardController {
    private CardService cardService;
    private static final Logger LOG =   LoggerFactory.getLogger(CardController.class);

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/check-if-card-exist")
    public Long checkExistCardByCardNumber(@RequestParam Long cardNumber) throws BusinessException {
        LOG.info("******* calling REST endpoint /card/check-if-card-exist");
        return this.cardService.checkExistCardByCardNumber(cardNumber);
    }

    @PostMapping("/check-if-pin is-correct")
    public Card checkIfPinCorrect(@RequestBody Card card){
        LOG.info("******* calling REST endpoint /card/check-if-pin");
        return this.cardService.checkIfPinCorrect(card);
    }
}
