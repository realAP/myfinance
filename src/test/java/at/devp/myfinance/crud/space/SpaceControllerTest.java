package at.devp.myfinance.crud.space;

import at.devp.myfinance.crud.space.create.SpaceCreationService;
import at.devp.myfinance.crud.space.read.SpaceReadService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpaceController.class)
class SpaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpaceCreationService spaceCreationService;

    @MockBean
    private SpaceReadService spaceReadService;

    @Test
    @Disabled
    void whenCreateSpaceEndpointIsCalledThenReturnIsCreated() throws Exception {
        final var content = """
                 {
                    "name": "Einnahmen"
                 }
                """;

        mockMvc.perform(post("/fe/crud/spaces").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void whenGetSpacesIsCalledThenReturnIsOk() throws Exception {
        mockMvc.perform(get("/fe/crud/spaces")).andExpect(status().isOk());
    }

}