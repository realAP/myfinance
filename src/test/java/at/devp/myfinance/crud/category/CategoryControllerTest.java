package at.devp.myfinance.crud.category;

import at.devp.myfinance.crud.category.create.CategoryCreationService;
import at.devp.myfinance.crud.category.read.CategoryReadService;
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

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryCreationService categoryCreationService;
    @MockBean
    private CategoryReadService categoryReadService;

    @Test
    @Disabled
    void whenCreateCategoryEndpointIsCalledThenReturnIsCreated() throws Exception {
        final var content = """
                 {
                    "name": "Sport"
                 }
                """;
        mockMvc.perform(post("/fe/crud/categories").contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void whenGetCategoriesIsCalledThenReturnIsOk() throws Exception {
        mockMvc.perform(get("/fe/crud/categories")).andExpect(status().isOk());
    }

}