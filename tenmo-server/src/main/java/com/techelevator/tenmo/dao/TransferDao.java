package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDetailsDto;
import com.techelevator.tenmo.model.TransferDto;

import java.util.List;

public interface TransferDao {


    Transfer createTransfer(Transfer transfer);
    List<TransferDto> getTransfersByUserId(int userId);

    TransferDetailsDto getTransferDetailsById(int transferId);


    Transfer getTransferById(int transferId);
}
