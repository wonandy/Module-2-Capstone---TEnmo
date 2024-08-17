package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDetailsDto {

    private int tranferId;
    private String transferType;
    private String transferStatus;
    private String accountFrom;
    private String accountTo;
    private BigDecimal amount;

    public TransferDetailsDto() {
    }

    public TransferDetailsDto(int tranferId, String accountFrom, BigDecimal amount) {
        this.tranferId = tranferId;
        this.accountFrom = accountFrom;
        this.amount = amount;
    }

    public TransferDetailsDto(int tranferId, String transferType, String transferStatus, String accountFrom, String accountTo, BigDecimal amount) {
        this.tranferId = tranferId;
        this.transferType = transferType;
        this.transferStatus = transferStatus;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public int getTranferId() {
        return tranferId;
    }

    public void setTranferId(int tranferId) {
        this.tranferId = tranferId;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
