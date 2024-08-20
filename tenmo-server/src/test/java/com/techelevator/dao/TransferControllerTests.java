package com.techelevator.dao;

import com.techelevator.tenmo.TenmoApplication;
import com.techelevator.tenmo.controller.TransferController;
import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = TransferController.class)
public class TransferControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransferDao transferDao;

    @MockBean
    private AccountDao accountDao;

        @Test
        public void testEndpoint() throws Exception {
            mockMvc.perform(get("/transfer"))
                    .andExpect(status().isOk());
        }
}