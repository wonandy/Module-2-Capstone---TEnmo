package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDao {


    Transfer createTransfer(Transfer transfer);

    Transfer getTransferById(int transferId);
}
