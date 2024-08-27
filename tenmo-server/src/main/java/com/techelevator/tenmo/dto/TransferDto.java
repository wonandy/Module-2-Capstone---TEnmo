package com.techelevator.tenmo.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferDto {
    private int transferId;
    @NotNull(message = "The field `accountFrom` should not be null.")
    private String accountFrom;
    @NotNull(message = "The field `accountTo` should not be null.")
    private String accountTo;
    @DecimalMin(value = "1.0", message = "The field `amount` should be greater than 0.")
    @NotNull(message = "The field `amount` should not be null.")
    private BigDecimal amount;

    public TransferDto(){

    }
    public TransferDto(int transferId, String accountFrom, String accountTo, BigDecimal amount) {
        this.transferId = transferId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int tranferId) {
        this.transferId = tranferId;
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
