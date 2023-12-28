package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class SpendingRepositoryUnitTest {

  @Autowired
  private SpendingRepository spendingRepository;


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



    spending.setRule(null); // Change these to valid Rule objects in your actual test
    spending.setTransfer(null); // Change these to valid Transfer objects in your actual test

    // Save item
    spendingRepository.save(spending);

    // Validate if an item is returned when search for amount greater than 150
    assertNotNull(spendingRepository.findAllByAmountIsGreaterThan(150.0));

    // Validate if an item is not returned when search for amount greater than 250
    assertNull(spendingRepository.findAllByAmountIsGreaterThan(250.0));
  }
}