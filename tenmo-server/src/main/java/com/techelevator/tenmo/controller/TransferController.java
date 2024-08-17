package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.BalanceInsufficientException;
import com.techelevator.tenmo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/transfer/")
@PreAuthorize("isAuthenticated()")
public class TransferController {
    private static final Logger log = LoggerFactory.getLogger(TransferController.class);
    private TransferDao transferDao;
    private UserDao userDao;
    private AccountDao accountDao;

    @Autowired
    public TransferController(TransferDao transferDao, UserDao userDao, AccountDao accountDao) {
        this.transferDao = transferDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @GetMapping
    public List<TransferDto> getTransfers(Principal principal){
        User user = userDao.getUserByUsername(principal.getName());
        return transferDao.getTransfersByUserId(user.getId());
    }
    @GetMapping(path = "/{id}")
    public TransferDetailsDto getTransferDetailsById(@PathVariable int id){
        return transferDao.getTransferDetailsById(id);
    }


    @PostMapping("/send")
    public ResponseEntity<String> sendTeBucks(Principal principal, @RequestBody TransferRequestDto transferRequestDto) {
        log.info(principal.getName() + " Is attempting to send TeBucks to: " + transferRequestDto.getUserTo());
        //Get accounts of from and to users
        Account accountTo = accountDao.getAccountByUserId(transferRequestDto.getUserTo());
        Account accountFrom = accountDao.getAccountByUsername(principal.getName());
        BigDecimal amount = transferRequestDto.getAmount();

        //withdraw from account from and deposit on account to if you are able to withdraw succesfully
        try {
            accountFrom.withdraw(amount);
        } catch (BalanceInsufficientException e) {
            log.error("Balance Insufficient for withdraw", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        try {
            accountTo.deposit(amount);
        } catch (IllegalArgumentException e) {
            log.error("negative value was passed to deposit", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        //After succesful money deposit and withdrawl updated both accounts in the database
        accountFrom = accountDao.updateAccount(accountFrom);
        accountTo = accountDao.updateAccount(accountTo);

        //Create new transfer of type Send and status Approved
        Transfer transfer = new Transfer(2,2,accountFrom.getAccountId(),accountTo.getAccountId(),amount);

        transferDao.createTransfer(transfer);

        log.info(principal.getName() + " Succesfully sent $" + transferRequestDto.getAmount() +" to user: " + transferRequestDto.getUserTo());

        return ResponseEntity.status(201).body("Transfer approved");
    }
}
