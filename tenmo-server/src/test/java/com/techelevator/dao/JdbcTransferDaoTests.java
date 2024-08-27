package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dto.TransferDetailsDto;
import com.techelevator.tenmo.dto.TransferPendingDto;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

public class JdbcTransferDaoTests extends BaseDaoTests {

    private JdbcTransferDao sut;

    //status        type
    //1 Pending    Request
    //2 Approved    Send
    //3 Rejected
    protected static final int PENDING_ID = 1, REQUEST_ID = 1;
    protected static final int APPROVED_ID = 2, SEND_ID = 2;
    protected static final int REJECTED_ID = 3;
    protected static final Transfer TRANSFER_SEND = new Transfer(3001, SEND_ID, APPROVED_ID, 2002, 2003, new BigDecimal(100.00));
    protected static final Transfer TRANSFER_REQUEST = new Transfer(3002, REQUEST_ID, PENDING_ID, 2003, 2002, new BigDecimal(100.00));

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTransferDao(jdbcTemplate);
    }

    @Test
    public void createTransfer_returns_new_transfer() {
        Transfer dbTransfer = sut.createTransfer(TRANSFER_SEND);
        Assert.assertNotNull(dbTransfer);
        Assert.assertEquals(TRANSFER_SEND.getTransferId(), dbTransfer.getTransferId());
        Assert.assertEquals(APPROVED_ID, dbTransfer.getTransferStatusId());
    }

    @Test
    public void getStatusIdByDesc_returns_correct_desc_id() {
        int pending = sut.getTransferStatusIdByDesc("Pending");
        int approved = sut.getTransferStatusIdByDesc("Approved");
        int rejected = sut.getTransferStatusIdByDesc("ReJeCted");


        Assert.assertEquals(PENDING_ID, pending);
        Assert.assertEquals(APPROVED_ID, approved);
        Assert.assertEquals(REJECTED_ID, rejected);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getStatusIdByDesc_throws_exception_with_invalid_status() {
        sut.getTransferStatusIdByDesc("Invalid");

    }

    @Test
    public void getPendingTransfers_returns_list_of_transfers_where_account_is_accountTo() {

        List<TransferPendingDto> transfersFor2002 = sut.getPendingTransfers
                (2002);
        List<TransferPendingDto> transfersFor2001 = sut.getPendingTransfers(2001);


        Assert.assertEquals(1, transfersFor2002.size());
        Assert.assertEquals(0, transfersFor2001.size());
    }

    @Test
    public void creatingPendingTransfer_updates_accounts_pending() {
        sut.createTransfer(TRANSFER_REQUEST);
        List<TransferPendingDto> transfersFor2002 = sut.getPendingTransfers
                (2002);

        Assert.assertEquals(2, transfersFor2002.size());
    }

    @Test
    public void getTransferDetailsById_returns_correct_transfer_and_user_details() {
        TransferDetailsDto detailsFor3010 = sut.getTransferDetailsById(3010);

        Assert.assertEquals(3010, detailsFor3010.getTransferId());
        Assert.assertEquals("Request", detailsFor3010.getTransferType());
        Assert.assertEquals("Pending", detailsFor3010.getTransferStatus());
        Assert.assertEquals("user1", detailsFor3010.getAccountFrom());
        Assert.assertEquals("user2", detailsFor3010.getAccountTo());
        Assert.assertEquals(new BigDecimal("10.00"), detailsFor3010.getAmount());

    }

    @Test
    public void updateTransfer_returns_transfer_with_updated_values() {
        Transfer transfer = sut.getTransferById(3010);
        int approvedId = sut.getTransferStatusIdByDesc("Approved");
        transfer.setTransferStatusId(approvedId);
        Transfer updatedTransfer = sut.updateTransfer(transfer);

        Assert.assertEquals(APPROVED_ID, updatedTransfer.getTransferStatusId());
    }


}
