package com.techelevator.tenmo.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferPendingDto {
    private int transferId;
    @NotNull(message = "The field `accountFrom` should not be null.")
    private String accountFrom;
    @DecimalMin(value = "1.0", message = "The field `amount` should be greater than 0.")
    @NotNull(message = "The field `amount` should not be null.")
    private BigDecimal amount;

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    // Getters and setters
    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTranferId(int transferId) {
    }
}