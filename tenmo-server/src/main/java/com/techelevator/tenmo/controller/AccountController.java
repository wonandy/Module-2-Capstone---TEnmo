package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@RequestMapping("/account/")
public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;

    @Autowired
    public AccountController(AccountDao accountDao){
        this.accountDao = accountDao;
    }


    @GetMapping("balance")
    public BigDecimal getBalance(Principal principal){
        BigDecimal balance = accountDao.getAccountByUsername(principal.getName()).getBalance();
        return balance;
    }


}
