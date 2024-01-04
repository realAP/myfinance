package at.devp.myfinance.services;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.services.spending.SpendingDeletionService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureTestEntityManager
@ActiveProfiles("test")
class SpendingDeletionServiceTest {
  @Autowired
  private SpendingDeletionService underTest;
  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  @Transactional
  void deleteById() {
    final var rule1 = testEntityManager.persist(new Rule());
    final var transfer1 = testEntityManager.persist(new Transfer());

    final var spending = new Spending();
    spending.setAmount(10.0);
    spending.setRuleAndUpdateStatus(rule1);
    spending.setTransferAndUpdateStatus(transfer1);

    testEntityManager.persist(spending);
    testEntityManager.flush();
    testEntityManager.clear();

    assertThat(testEntityManager.find(Spending.class, spending.getId()), notNullValue());
    assertThat(testEntityManager.find(Spending.class, spending.getId()).getAmount(), is(10.0));

    assertThat(testEntityManager.find(Rule.class, rule1.getId()).getSpendings().size(), is(1));
    assertThat(testEntityManager.find(Rule.class, rule1.getId()).getAmount(), is(10.0));

    assertThat(testEntityManager.find(Transfer.class, transfer1.getId()).getSpendings().size(), is(1));
    assertThat(testEntityManager.find(Transfer.class, transfer1.getId()).getAmount(), is(10.0));

    underTest.deleteById(spending.getId());
    testEntityManager.flush();
    testEntityManager.clear();

    assertThat(testEntityManager.find(Spending.class, spending.getId()), nullValue());

    assertThat(testEntityManager.find(Rule.class, rule1.getId()).getSpendings().size(), is(0));
    assertThat(testEntityManager.find(Rule.class, rule1.getId()).getAmount(), is(0.0));

    assertThat(testEntityManager.find(Transfer.class, transfer1.getId()).getSpendings().size(), is(0));
    assertThat(testEntityManager.find(Transfer.class, transfer1.getId()).getAmount(), is(0.0));
  }
}