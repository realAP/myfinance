package at.devp.myfinance.services.space.read;

import at.devp.myfinance.controller_fe.read.SpaceReadController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpaceReadController.class)
class SpaceReadServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpaceReadService spaceReadService;

    @Test
    void whenGetSpacesIsCalledThenReturnIsOk() throws Exception {
        mockMvc.perform(get("fe/read/spaces")).andExpect(status().isOk());
    }


}