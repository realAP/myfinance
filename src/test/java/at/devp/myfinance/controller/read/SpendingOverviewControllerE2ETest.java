package at.devp.myfinance.controller.read;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.SpendingRepository;
import jakarta.transaction.Transactional;
import org.instancio.Instancio;
import org.instancio.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.instancio.Select.field;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
class SpendingOverviewControllerE2ETest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private SpendingRepository spendingRepository;

  @BeforeEach
  void cleanDb() {
    spendingRepository.deleteAll();
  }

  @Test
  @Transactional
  @Disabled
  void testFinanceOverviewEndpoint() throws Exception {

    Model<Spending> spendingModel = Instancio.of(Spending.class)
        .ignore(field(Spending::getId))
        .ignore(field(Transfer::getId))
        .ignore(field(Rule::getId)).toModel();

    final var spending1 = Instancio.of(spendingModel).withSeed(123l).create();
    final var spending2 = Instancio.of(spendingModel).withSeed(124l).create();

    entityManager.persist(spending1);
    entityManager.persist(spending2);


    String expectedJsonResponse = """
        [
          {
            "description": "OMTTHYHV",
            "amount": 8754.252439728922,
            "category": "SPORT",
            "ruleDto": {
              "description": "ZNRCBAQK"
            },
            "transferDto": {
              "description": "YEDU"
            }
          },
          {
            "description": "UQIKDJVU",
            "amount": 2399.8272342672662,
            "category": "SPORT",
            "ruleDto": {
              "description": "EKPXIUG"
            },
            "transferDto": {
              "description": "HSSQUBZ"
            }
          }
        ]
                            """;

    mockMvc.perform(get("/api/v1/read/financeoverview").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andExpect(content().json(expectedJsonResponse));
  }
}
