package at.devp.myfinance.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class RuleTest {

    @Test
    void testCalculateHasChange_SameValues() {
        // Initialize a new Rule entity, with oldAmount and amount being equal
        Rule rule = new Rule();
        rule.setOldAmount(BigDecimal.valueOf(1000));
        rule.setAmount(BigDecimal.valueOf(1000));

        // Call the method being tested
        boolean hasChange = rule.calculateHasChange();

        // Assert that the method returns false, as there was no change in amount
        assertFalse(hasChange, "Expected no change in amount, but change was found");
    }

    @Test
    void testCalculateHasChange_DifferentValues() {
        // Initialize a new Rule entity, with oldAmount and amount being different
        Rule rule = new Rule();
        rule.setOldAmount(BigDecimal.valueOf(1000));
        rule.setAmount(BigDecimal.valueOf(2000));

        // Call the method being tested
        boolean hasChange = rule.calculateHasChange();

        // Assert that the method returns true, as there was change in amount
        assertTrue(hasChange, "Expected a change in amount, but no change was found");
    }

    @Test
    void testCalculateHasChange_NullOldAmount() {
        // Initialize a new Rule entity, with oldAmount being null and amount having a value
        Rule rule = new Rule();
        rule.setOldAmount(null);
        rule.setAmount(BigDecimal.valueOf(2000));

        // Call the method being tested
        boolean hasChange = rule.calculateHasChange();

        // Assert that the method returns true, as a change is detected when the oldAmount is null.
        assertTrue(hasChange, "Expected a change in amount, but no change was found");
    }

    @Test
    void testCalculateAmount() {
        // Initialize a new Rule entity, with a list of spendings
        Rule rule = new Rule();
        Spending spending1 = new Spending();
        spending1.setAmount(BigDecimal.valueOf(1000));
        Spending spending2 = new Spending();
        spending2.setAmount(BigDecimal.valueOf(2000));
        rule.getSpendings().add(spending1);
        rule.getSpendings().add(spending2);

        // Call the method being tested
        BigDecimal amount = rule.calculateAmount();

        // Assert that the method returns the sum of the amounts of the spendings
        assertEquals(BigDecimal.valueOf(3000), amount, "Expected the sum of the amounts of the spendings");
    }

    @Test
    void whenCalculateAmountGivenSpendingWithNullAmount_thenReturnSumOfNonNullAmounts() {
        // Initialize a new Rule entity, with a list of spendings
        Rule rule = new Rule();
        Spending spending1 = new Spending();
        spending1.setAmount(BigDecimal.valueOf(1000));
        Spending spending2 = new Spending();
        spending2.setAmount(null);
        rule.getSpendings().add(spending1);
        rule.getSpendings().add(spending2);

        // Call the method being tested
        BigDecimal amount = rule.calculateAmount();

        // Assert that the method returns the sum of the amounts of the spendings
        assertEquals(BigDecimal.valueOf(1000), amount, "Expected the sum of the amounts of the spendings");
    }

    @Test
    void testUpdateAmountAndChange() {
        // Initialize a new Rule entity, with a list of spendings
        Rule rule = new Rule();
        Spending spending1 = new Spending();
        spending1.setAmount(BigDecimal.valueOf(1000));
        Spending spending2 = new Spending();
        spending2.setAmount(BigDecimal.valueOf(2000));
        rule.getSpendings().add(spending1);
        rule.getSpendings().add(spending2);

        assertThat(rule.isChange(), is(false));
        assertThat(rule.getAmount().toString(), is("0.00"));

        // Call the method being tested
        rule.updateAmountAndChange();

        // Assert that the amount and isChange fields are updated
        assertEquals(BigDecimal.valueOf(3000), rule.getAmount(), "Expected the sum of the amounts of the spendings");
        assertTrue(rule.isChange(), "Expected a change in amount, but no change was found");
    }
}