package at.devp.myfinance.controller_fe.write;

import at.devp.myfinance.services.bank.create.BankCreationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankWriteController.class)
class BankWriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankCreationService bankCreationService;

    @Test
    void whenCreateBankEndpointIsCalledThenReturnIsCreated() throws Exception {
        final var content = """
                 {
                    "name": "n26"
                 }
                """;
        mockMvc.perform(post("/fe/write/banks").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
    }


}