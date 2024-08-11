package at.devp.myfinance.controller_fe.write;

import at.devp.myfinance.services.space.create.SpaceCreationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpaceWriteController.class)
class SpaceWriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpaceCreationService spaceCreationService;

    @Test
    void whenCreateSpaceEndpointIsCalledThenReturnIsCreated() throws Exception {
        mockMvc.perform(post("/fe/write/spaces")).andExpect(status().isCreated());
    }


}