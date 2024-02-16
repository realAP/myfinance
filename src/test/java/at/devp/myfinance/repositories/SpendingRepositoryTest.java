package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class SpendingRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private SpendingRepository underTest;

  @Test
  void testFindAll() {
    // Given
    Rule rule = new Rule();
    entityManager.persist(rule);

    Spending spending1 = new Spending();
    spending1.setRuleAndUpdateStatus(rule);
    entityManager.persist(spending1);

    Spending spending2 = new Spending();
    spending2.setRuleAndUpdateStatus(rule);
    entityManager.persist(spending2);

    // When
    final List<Spending> result = underTest.findAll();

    // Then
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.containsAll(List.of(spending1, spending2)));
  }

  @Test
  void whenFindAllSpendingsByRuleIdGivenThreeSpendingsWithSameRuleThenReturnThreeSpendings() {
    final var rule = entityManager.persist(new Rule());

    final var spending1 = new Spending();
    spending1.setRuleAndUpdateStatus(rule);
    entityManager.persist(spending1);

    final var spending2 = new Spending();
    spending2.setRuleAndUpdateStatus(rule);
    entityManager.persist(spending2);

    final var spending3 = new Spending();
    spending3.setRuleAndUpdateStatus(rule);
    entityManager.persist(spending3);

    final var result = underTest.findAllSpendingsByRuleId(rule.getId());

    assertThat(result.size(), is(3));
  }

}