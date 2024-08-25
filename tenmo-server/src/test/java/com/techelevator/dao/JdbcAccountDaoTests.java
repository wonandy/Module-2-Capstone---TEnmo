package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class JdbcAccountDaoTests extends BaseDaoTests {

    protected static final Account ACCOUNT_1 = new Account(2001, 1001, new BigDecimal(1000.00));
    protected static final Account ACCOUNT_2 = new Account(2002, 1002, new BigDecimal(1000.00));
    protected static final Account ACCOUNT_3 = new Account(2003, 1002, new BigDecimal(1000.00));

    private JdbcAccountDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcAccountDao(jdbcTemplate);
    }

    @Test
    public void getAccountByUserId_returns_correct_account() {
        Account actualAccount = sut.getAccountByUserId(ACCOUNT_1.getUserId());
        Assert.assertEquals(ACCOUNT_1.getAccountId(), actualAccount.getAccountId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAccountByUsername_given_null_throws_exception() {
        sut.getAccountByUsername(null);
    }

    @Test
    public void getAccountById_returns_correct_account() {
        Account actualAccount = sut.getAccountById(ACCOUNT_3.getAccountId());

        Assert.assertEquals(ACCOUNT_3.getAccountId(), actualAccount.getAccountId());
    }

    @Test
    public void updateAccount_returns_updated_values() {
        Account account = sut.getAccountById(ACCOUNT_2.getAccountId());
        account.setBalance(new BigDecimal("900.00"));

        Account updatedAccount = sut.updateAccount(account);

        Assert.assertEquals(new BigDecimal("900.00"), updatedAccount.getBalance());
        Assert.assertEquals(account.getAccountId(), updatedAccount.getAccountId());

    }
}
