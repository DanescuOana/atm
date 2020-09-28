package com.tech.atm.card.web;

import com.tech.atm.card.service.CardService;
import com.tech.atm.domain.vo.Card;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/card")
public class CardController {
    private CardService cardService;
    private static final Logger LOG =   LoggerFactory.getLogger(CardController.class);

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/existance")
    public Long checkExistCardByCardNumber(@RequestParam Long cardNumber){
        LOG.info("******* calling REST endpoint /card/existance");
        return this.cardService.checkExistCardByCardNumber(cardNumber);
    }

    @PostMapping("/authorize")
    public Card checkIfPinCorrect(@RequestBody Card card){
        LOG.info("******* calling REST endpoint /card/authorize");
        return this.cardService.checkIfPinCorrect(card);
    }
}
