package at.devp.myfinance.controller_fe.read;

import at.devp.myfinance.feature.financeOverview.SpendingOverviewController;
import at.devp.myfinance.feature.financeOverview.SpendingOverviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpendingOverviewController.class)
class SpendingOverviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpendingOverviewService spendingOverviewService;


    @Test
    @WithMockUser
    void whenOverviewEndpointIsCalledThenReturnIsOk() throws Exception {
        mockMvc.perform(get("/fe/overview")).andExpect(status().isOk());
    }

}