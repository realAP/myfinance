package at.devp.myfinance.feature.financeOverview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpendingOverviewController.class)
class SpendingOverviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SpendingOverviewService spendingOverviewService;


    @Test
    @WithMockUser
    void whenOverviewEndpointIsCalledThenReturnIsOk() throws Exception {
        mockMvc.perform(get("/fe/overview")).andExpect(status().isOk());
    }

}