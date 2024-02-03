package at.devp.myfinance.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class TransferTest {

  @Test
  void testCalculateHasChange_SameValues() {
    // Initialize a new Transfer entity, with oldAmount and amount being equal
    Transfer transfer = new Transfer();
    transfer.setOldAmount(BigDecimal.valueOf(1000));
    transfer.setAmount(BigDecimal.valueOf(1000));

    // Call the method being tested
    boolean hasChange = transfer.calculateHasChange();

    // Assert that the method returns false, as there was no change in amount
    assertFalse(hasChange, "Expected no change in amount, but change was found");
  }

  @Test
  void testCalculateHasChange_DifferentValues() {
    // Initialize a new Transfer entity, with oldAmount and amount being different
    Transfer transfer = new Transfer();
    transfer.setOldAmount(BigDecimal.valueOf(1000));
    transfer.setAmount(BigDecimal.valueOf(2000));

    // Call the method being tested
    boolean hasChange = transfer.calculateHasChange();

    // Assert that the method returns true, as there was change in amount
    assertTrue(hasChange, "Expected a change in amount, but no change was found");
  }

  @Test
  void testCalculateHasChange_NullOldAmount() {
    // Initialize a new Transfer entity, with oldAmount being null and amount having a value
    Transfer transfer = new Transfer();
    transfer.setOldAmount(null);
    transfer.setAmount(BigDecimal.valueOf(2000));

    // Call the method being tested
    boolean hasChange = transfer.calculateHasChange();

    // Assert that the method returns true, as a change is detected when the oldAmount is null.
    assertTrue(hasChange, "Expected a change in amount, but no change was found");
  }

  @Test
  void testCalculateAmount() {
    // Initialize a new Transfer entity, with a list of spendings
    Transfer transfer = new Transfer();
    Spending spending1 = new Spending();
    spending1.setAmount(BigDecimal.valueOf(1000));
    Spending spending2 = new Spending();
    spending2.setAmount(BigDecimal.valueOf(2000));
    transfer.getSpendings().add(spending1);
    transfer.getSpendings().add(spending2);

    // Call the method being tested
    BigDecimal amount = transfer.calculateAmount();

    // Assert that the method returns the sum of the amounts of the spendings
    assertEquals(BigDecimal.valueOf(3000), amount, "Expected the sum of the amounts of the spendings");
  }

   @Test
   void whenCalculateAmountGivenSpendingWithNullAmountThenReturnSumOfNonNullAmounts() {
     // Initialize a new Transfer entity, with a list of spendings
     Transfer transfer = new Transfer();
     Spending spending1 = new Spending();
     spending1.setAmount(null);
     Spending spending2 = new Spending();
     spending2.setAmount(BigDecimal.valueOf(1000));
     transfer.getSpendings().add(spending1);
     transfer.getSpendings().add(spending2);

     // Call the method being tested
     BigDecimal amount = transfer.calculateAmount();

     // Assert that the method returns the sum of the amounts of the spendings
     assertEquals(BigDecimal.valueOf(1000), amount, "Expected the sum of the amounts of the spendings");
   }

  @Test
  void testUpdateAmountAndChange() {
    // Initialize a new Transfer entity, with a list of spendings
    Transfer transfer = new Transfer();
    Spending spending1 = new Spending();
    spending1.setAmount(BigDecimal.valueOf(1000));
    Spending spending2 = new Spending();
    spending2.setAmount(BigDecimal.valueOf(2000));
    transfer.getSpendings().add(spending1);
    transfer.getSpendings().add(spending2);


    assertThat(transfer.isChange(), is(false));
    assertThat(transfer.getAmount().toString(), is("0.00"));

    // Call the method being tested
    transfer.updateAmountAndChange();

    // Assert that the amount and isChange fields are updated
    assertEquals(BigDecimal.valueOf(3000), transfer.getAmount(), "Expected the sum of the amounts of the spendings");
    assertTrue(transfer.isChange(), "Expected isChange to be true");
  }

}