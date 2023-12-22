package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SpendingRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private SpendingRepository spendingRepository;

  @Test
  void testFindByRuleId() {
    // Given
    Rule rule = new Rule();
    entityManager.persist(rule);
    Spending spending1 = new Spending();
    spending1.setRule(rule);
    entityManager.persist(spending1);
    Spending spending2 = new Spending();
    spending2.setRule(rule);
    entityManager.persist(spending2);
    entityManager.flush();

    // When
    final var result = spendingRepository.findById(spending1.getId());

    System.out.println(result);
  }

  @Test
  void testFindAll() {
    // Given
    Rule rule = new Rule();
    entityManager.persist(rule);
    Spending spending1 = new Spending();
    spending1.setRule(rule);
    entityManager.persist(spending1);
    Spending spending2 = new Spending();
    spending2.setRule(rule);
    entityManager.persist(spending2);
    entityManager.flush();

    // When
    final List<Spending> result = spendingRepository.findAll();

    // Then
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.containsAll(List.of(spending1, spending2)));
  }
}