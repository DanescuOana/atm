package com.tech.atm.account.service.impl;

import com.tech.atm.account.service.AccountService;
import com.tech.atm.common.CommonServiceImplTest;
import com.tech.atm.domain.vo.Account;
import com.tech.atm.domain.logic.ATMOperation;
import com.tech.atm.support.exception.BusinessRuntimeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountServiceImplTest extends CommonServiceImplTest {

    @Autowired
    AccountService accountService;

    //loadDetailsAccountsByCardNumber methods Tests
    @Test
    public void loadDetailsAccountsByCardNumberPass() {
        List<Account> accounts=this.accountService.loadDetailsAccountsByCardNumber(cardNumbers.get(0));
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @Test
    public void loadDetailsAccountsByCardNumberFail() {
        assertThrows(BusinessRuntimeException.class, () -> {
            this.accountService.loadDetailsAccountsByCardNumber(223333L);
        });
    }

    //withdrawMoneyFromAccount methods Tests
    @Test
    public void withdrawMoneyFromAccountPass() {
        Long amount = 2L;
        assertEquals(true, this.accountService.withdrawMoneyFromAccount(
                accounts.stream()
                        .filter(account -> account.getCanWithdraw() == true)
                        .findFirst()
                        .get(), amount));
    }

    @Test
    public void withdrawMoneyFromAccountFail() {
        Long amount = 24555555555L;
        assertThrows(BusinessRuntimeException.class, () -> {
            this.accountService.withdrawMoneyFromAccount(
                    (Account) accounts.stream()
                            .filter(account -> account.getCanWithdraw())
                            .findFirst()
                            .get(), amount);
        });
    }

    //chooseOperationComplete WITHDRAW methods Tests
    @Test
    public void chooseOperationCompleteCanWithdrawNullAmount() {
        assertNotEquals(true, this.accountService.chooseOperation(
                (Account) accounts.stream()
                        .filter(account -> account.getCanWithdraw())
                        .findFirst()
                        .get(), ATMOperation.WITHDRAW, null));
    }

    @Test
    public void chooseOperationCompleteCanWithdrawTrue() {
        assertEquals(true, this.accountService.chooseOperation(
                (Account) accounts.stream()
                        .filter(account -> account.getCanWithdraw())
                        .findFirst()
                        .get(), ATMOperation.WITHDRAW, 3L));
    }

    @Test
    public void chooseOperationCompleteCanWithdrawFalse() {
        assertThrows(BusinessRuntimeException.class, () -> {
            this.accountService.chooseOperation(
                    (Account) accounts.stream()
                            .filter(account -> account.getCanWithdraw())
                            .findFirst()
                            .get(), ATMOperation.WITHDRAW, 35464564L);
        });
    }

    @Test
    public void chooseOperationCompleteWithdrawFail() {
        assertThrows(BusinessRuntimeException.class, () -> {
            this.accountService.chooseOperation(
                    (Account) accounts.stream()
                            .filter(account -> !account.getCanWithdraw())
                            .findFirst()
                            .get(), ATMOperation.WITHDRAW, 34L);
        });
    }

    //chooseOperationComplete DEPOSIT methods Tests
    @Test
    public void chooseOperationCompleteDepositNull() {
        assertThrows(BusinessRuntimeException.class, () -> {
            this.accountService.chooseOperation(
                    (Account) accounts.stream()
                            .findFirst()
                            .get(), ATMOperation.DEPOSIT, null);
        });
    }

    @Test
    public void chooseOperationCompleteDepositTrue() {
        assertEquals(true, this.accountService.chooseOperation(
                (Account) accounts.stream()
                        .findFirst()
                        .get(), ATMOperation.DEPOSIT, 2332L));
    }

    //chooseOperationComplete CANCEL methods Tests
    @Test
    public void chooseOperationCompleteCancel() {
        assertEquals(true, this.accountService.chooseOperation(
                (Account) accounts.stream()
                        .findFirst()
                        .get(), ATMOperation.CANCEL, 23L));
    }

    //chooseOperationComplete CANCEL methods Tests
    @Test
    public void chooseOperationCompleteOther() {
        assertThrows(BusinessRuntimeException.class, () -> {
            this.accountService.chooseOperation(
                    (Account) accounts.stream()
                            .findFirst()
                            .get(), ATMOperation.OTHER, 23L);
        });
    }

    //accountCanBeWithdaw methods Tests
    @Test
    public void accountCanBeWithdawTrue() {
        assertEquals(true, this.accountService.accountCanBeWithdaw(ibanCan));
    }

    @Test
    public void accountCanBeWithdawFalse() {
        assertNotEquals(true, this.accountService.accountCanBeWithdaw(ibanCannot));
    }

    @Test
    public void accountCanBeWithdawNull() {
        assertThrows(BusinessRuntimeException.class, () -> {
            this.accountService.accountCanBeWithdaw(null);
        });
    }
}
