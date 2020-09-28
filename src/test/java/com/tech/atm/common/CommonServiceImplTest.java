package com.tech.atm.common;

import com.tech.atm.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
public class CommonServiceImplTest {

    protected Long userId;
    protected String ibanCan;
    protected String ibanCannot;
    protected List<Account> accounts;
    protected List<Long> cardNumbers;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUpTest() {
        //insert into user
        String username = "odanescu23";
        String name = "Oana Danescu";

        jdbcTemplate.update(
                "INSERT INTO user (username, name) VALUES (?, ?)",
                username, name
        );

        //insert into card
        Long pin = 1223L;
        Long cardNumberTrue = 100006L;
        jdbcTemplate.update(
                "INSERT INTO card (card_number, pin) VALUES (?, ?)",
                cardNumberTrue, pin
        );

        Long cardNumberFalse = 100007L;
        jdbcTemplate.update(
                "INSERT INTO card (card_number, pin) VALUES (?, ?)",
                cardNumberFalse, pin
        );

        //insert into account
         ibanCan="RO49AAAA1B31007593840028";
         ibanCannot="RO49AAAA1B31007593840029";
        Double balance = 3445.45;
        Long accountTypeIdCanWithdraw = 5L;
        Long accountTypeIdCanNotWithdraw =4L;

        userId = jdbcTemplate.queryForObject("select id from user where username ='"+username+"'", Long.class);

        jdbcTemplate.update(
                "INSERT INTO account (iban, balance_account, account_type_id, card_number, user_id) VALUES (?, ?, ?, ?, ?)",
                ibanCan, balance, accountTypeIdCanWithdraw, cardNumberTrue, userId
        );

        jdbcTemplate.update(
                "INSERT INTO account (iban, balance_account, account_type_id, card_number, user_id) VALUES (?, ?, ?, ?, ?)",
                ibanCannot, balance, accountTypeIdCanNotWithdraw, cardNumberFalse, userId
        );

        cardNumbers = jdbcTemplate.queryForList("select card_number from account where user_id="+userId, Long.class);
        accounts = this.getAccounts();
    }

    public List<Account> getAccounts() {
        String sql = "select account.iban, account.balance_account balanceAccount, account.card_number cardNumber, account_type.possibility_extract_from_atm  canWithdraw from account inner join account_type on account.account_type_id = account_type.id where user_id="+userId;

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Account>(Account.class));
    }
}
