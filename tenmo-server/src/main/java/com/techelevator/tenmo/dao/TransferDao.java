package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {


    Integer getTransferStatusIdByDesc(String status);
    Transfer createTransfer(Transfer transfer);
    List<TransferDto> getTransfersByUserId(int userId);

    TransferDetailsDto getTransferDetailsById(int transferId);

    List<TransferPendingDto> getPendingTransfers(int userId);
    Transfer getTransferById(int transferId);

//    TransferDetailsDto sendRequest(int accountFrom, int accountTo, BigDecimal ammount);
}
