package at.devp.myfinance.controller_fe.read;

import at.devp.myfinance.services.bank.read.BankReadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankReadController.class)
class BankReadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankReadService bankReadService;

    @Test
    void whenGetBanksIsCalledThenReturnIsOk() throws Exception {
        mockMvc.perform(get("/fe/read/banks")).andExpect(status().isOk());
    }
}