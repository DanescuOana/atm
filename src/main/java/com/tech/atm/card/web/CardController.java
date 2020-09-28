package com.tech.atm.card.web;

import com.tech.atm.account.service.AccountService;
import com.tech.atm.card.service.CardService;
import com.tech.atm.domain.vo.Account;
import com.tech.atm.domain.vo.Card;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/card")
public class CardController {
    private CardService cardService;
    private AccountService accountService;
    private static final Logger LOG =   LoggerFactory.getLogger(CardController.class);

    public CardController(CardService cardService, AccountService accountService) {
        this.cardService = cardService;
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public List<Account> loadAccountsForCardNumber(@RequestParam Long cardNumber){
        LOG.info("******* calling REST endpoint /card/accounts");
        return this.accountService.loadDetailsAccountsByCardNumber(cardNumber);
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
