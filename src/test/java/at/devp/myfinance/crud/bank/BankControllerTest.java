package at.devp.myfinance.crud.bank;

import at.devp.myfinance.crud.bank.create.BankCreationService;
import at.devp.myfinance.crud.bank.read.BankReadService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BankController.class)
class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BankReadService bankReadService;

    @MockitoBean
    private BankCreationService bankCreationService;

    @Test
    @WithMockUser
    void whenGetBanksIsCalledThenReturnIsOk() throws Exception {
        mockMvc.perform(get("/fe/crud/banks")).andExpect(status().isOk());
    }


    @Test
    @WithMockUser
    @Disabled
    void whenCreateBankEndpointIsCalledThenReturnIsCreated() throws Exception {
        final var content = """
                 {
                    "name": "n26"
                 }
                """;
        mockMvc.perform(post("/fe/crud/banks").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
    }
}