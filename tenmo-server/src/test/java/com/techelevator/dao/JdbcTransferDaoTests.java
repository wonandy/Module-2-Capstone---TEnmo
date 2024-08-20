package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class JdbcTransferDaoTests extends BaseDaoTests{

    private JdbcTransferDao sut;

    //status        type
    //1 Pending    Request
    //2 Approved    Send
    //3 Rejected
    protected static final Transfer TRANSFER_SEND = new Transfer(3001, 2, 2, 2002, 2003, new BigDecimal(100.00));
    protected static final Transfer TRANSFER_REQUEST = new Transfer(3002, 1, 1, 2002, 2003, new BigDecimal(100.00));
    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTransferDao(jdbcTemplate);
    }

    @Test
    public void createTransfer_returns_new_transfer(){
        Transfer dbTransfer = sut.createTransfer(TRANSFER_SEND);
        Assert.assertNotNull(dbTransfer);
        Assert.assertEquals(TRANSFER_SEND.getTransferId(), dbTransfer.getTransferId());
        Assert.assertEquals(2, dbTransfer.getTransferStatusId());
    }

    @Test
    public void  getStatusIdByDesc_returns_correct_desc_id(){
        int pending = sut.getTransferStatusIdByDesc("Pending");
        int approved = sut.getTransferStatusIdByDesc("Approved");
        int rejected = sut.getTransferStatusIdByDesc("ReJeCted");


        Assert.assertEquals(1, pending);
        Assert.assertEquals(2, approved);
        Assert.assertEquals(3, rejected);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getStatusIdByDesc_throws_exception_with_invalid_status(){
        sut.getTransferStatusIdByDesc("Invalid");

    }

}
