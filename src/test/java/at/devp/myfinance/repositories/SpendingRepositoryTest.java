package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SpendingRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private SpendingRepository underTest;

  @Test
  void testFindByRuleId() {
    // Given
    Rule rule = new Rule();
    entityManager.persist(rule);
    Spending spending1 = new Spending();
    spending1.setRuleAndUpdateStatus(rule);
    entityManager.persist(spending1);
    Spending spending2 = new Spending();
    spending2.setRuleAndUpdateStatus(rule);
    entityManager.persist(spending2);
    entityManager.flush();

    // When
    final var result = underTest.findById(spending1.getId());

    System.out.println(result);
  }

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
    entityManager.flush();

    // When
    final List<Spending> result = underTest.findAll();

    // Then
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.containsAll(List.of(spending1, spending2)));
  }

  @Test
  void testfindBySpendingAmount() {


  }

  @Test
  void testFindAllByAmountIsGreaterThan() {
    // Prepare some valid data
    final var spending = new Spending();
    spending.setDescription("Test spending");
    spending.setAmount(200.00);

    final var transfer = new Transfer();
    transfer.setDescription("transfer");


    final var rule = new Rule();
    rule.setDescription("rule");


    spending.setRuleAndUpdateStatus(null); // Change these to valid Rule objects in your actual test
    spending.setTransferAndUpdateStatus(null); // Change these to valid Transfer objects in your actual test

    // Save item
    underTest.save(spending);

    // Validate if an item is returned when search for amount greater than 150
    assertNotNull(underTest.findAllByAmountIsGreaterThan(150.0));

    // Validate if an item is not returned when search for amount greater than 250
    assertNull(underTest.findAllByAmountIsGreaterThan(250.0));
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