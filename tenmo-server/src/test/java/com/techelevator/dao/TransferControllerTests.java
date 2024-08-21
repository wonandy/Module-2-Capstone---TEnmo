//package com.techelevator.dao;
//
//import com.techelevator.tenmo.TenmoApplication;
//import com.techelevator.tenmo.controller.TransferController;
//import com.techelevator.tenmo.dao.AccountDao;
//import com.techelevator.tenmo.dao.TransferDao;
//import com.techelevator.tenmo.dto.LoginResponseDto;
//import com.techelevator.tenmo.dto.TransferDetailsDto;
//import com.techelevator.tenmo.dto.TransferPendingDto;
//import com.techelevator.tenmo.model.LoginDto;
//import com.techelevator.tenmo.model.Transfer;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.*;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.shortThat;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import javax.annotation.PostConstruct;
//import java.math.BigDecimal;
//import java.util.List;
//
//@WebMvcTest(TransferController.class)
//public class TransferControllerTests {
//
//    private String jwtToken;
//    @Autowired
//    private TestRestTemplate template;
//
//
//    @Test
//    @WithMockUser(username = "user", roles = {"USER"})
//    public void testEndpointWithBasicAuth() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(jwtToken);
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<TransferDetailsDto> response = template.exchange("http://localhost:8080/transfer/3001", HttpMethod.GET, entity, TransferDetailsDto.class);
//        System.out.println(response.getStatusCode());
//    }
//}
