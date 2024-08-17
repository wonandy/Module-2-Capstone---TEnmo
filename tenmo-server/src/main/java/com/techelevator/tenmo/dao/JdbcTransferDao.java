package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDetailsDto;
import com.techelevator.tenmo.model.TransferDto;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcTransferDao implements TransferDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        Transfer newTransfer = null;
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id,account_from, account_to, amount) VALUES (?, ?, ?, ?, ?) " +
                "RETURNING transfer_id";

        try {
            int newTransferId = jdbcTemplate.queryForObject(sql, int.class, 2, 2, transfer.getAccountFromId(), transfer.getAccountToId(), transfer.getAmount());
            newTransfer = getTransferById(newTransferId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return newTransfer;

    }

    @Override
    public Transfer getTransferById(int transferId) {
        Transfer transfer = null;
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfer " +
                "WHERE transfer_id = ?";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, transferId);
            if (rowSet.next()) {
                transfer = mapRowToTransfer(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transfer;
    }

    @Override
    public List<TransferDto> getTransfersByUserId(int userId) {
        List<TransferDto> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, afu.username as account_from, atu.username as account_to, amount " +
                "FROM transfer " +
                "JOIN account af ON transfer.account_from = af.account_id " +
                "JOIN tenmo_user afu ON af.user_id = afu.user_id " +
                "JOIN account at ON transfer.account_to = at.account_id " +
                "JOIN tenmo_user atu ON at.user_id = atu.user_id " +
                "WHERE af.user_id = ? OR atu.user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
            while (results.next()) {
                TransferDto transfer = mapRowToTransferDto(results);
                transfers.add(transfer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transfers;
    }
    @Override
    public TransferDetailsDto getTransferDetailsById(int transferId) {
        TransferDetailsDto transferDetail = null;
        String sql = "SELECT transfer_id, transfer_type_desc, transfer_status_desc, afu.username as account_from, atu.username as account_to, amount " +
                "FROM transfer " +
                "JOIN transfer_status USING(transfer_status_id) " +
                "JOIN transfer_type USING(transfer_type_id) " +
                "JOIN account af ON transfer.account_from = af.account_id " +
                "JOIN tenmo_user afu ON af.user_id = afu.user_id " +
                "JOIN account at ON transfer.account_to = at.account_id " +
                "JOIN tenmo_user atu ON at.user_id = atu.user_id " +
                "WHERE transfer_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
            if (results.next()) {
                transferDetail = mapRowToTransferDetails(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transferDetail;
    }

    private Transfer mapRowToTransfer(SqlRowSet rs){
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferTypeId(rs.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setAccountFromId(rs.getInt("account_from"));
        transfer.setAccountFromId(rs.getInt("account_from"));
        transfer.setAccountToId(rs.getInt("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
    }

    private TransferDto mapRowToTransferDto(SqlRowSet rs) {
        TransferDto transfer = new TransferDto();

        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setAccountFrom(rs.getString("account_from"));
        transfer.setAccountTo(rs.getString("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));

        return transfer;
    }
    private TransferDetailsDto mapRowToTransferDetails(SqlRowSet rs) {
        TransferDetailsDto transferDetail = new TransferDetailsDto();
        transferDetail.setTranferId(rs.getInt("transfer_id"));
        transferDetail.setTransferType(rs.getString("transfer_type_desc"));
        transferDetail.setTransferStatus(rs.getString("transfer_status_desc"));
        transferDetail.setAccountFrom(rs.getString("account_from"));
        transferDetail.setAccountTo(rs.getString("account_to"));
        transferDetail.setAmount(rs.getBigDecimal("amount"));
        return transferDetail;
    }
}
