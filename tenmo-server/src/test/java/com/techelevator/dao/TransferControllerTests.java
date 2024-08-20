package com.techelevator.dao;

import com.techelevator.tenmo.TenmoApplication;
import com.techelevator.tenmo.controller.TransferController;
import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dto.TransferDetailsDto;
import com.techelevator.tenmo.dto.TransferPendingDto;
import com.techelevator.tenmo.model.Transfer;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes= TransferController.class)
//@SpringBootTest(classes = TenmoApplication.class)
//@WebMvcTest(controllers = TransferController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ExtendWith(MockitoExtension.class)
@WebMvcTest(TransferController.class)
public class TransferControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransferDao transferDao;

    @MockBean
    private AccountDao accountDao;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testEndpoint() throws Exception {
        TransferDetailsDto transfer = new TransferDetailsDto(3001, "Send", "Approved", "user1","user2",new BigDecimal(10.00));
        when(transferDao.getTransferDetailsById(3001)).thenReturn(transfer);
        mockMvc.perform(get("/transfer/3001"))
                .andExpect(status().isOk());
    }
}