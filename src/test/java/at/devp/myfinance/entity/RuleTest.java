package at.devp.myfinance.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
}