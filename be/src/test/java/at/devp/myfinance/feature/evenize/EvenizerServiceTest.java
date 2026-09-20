package at.devp.myfinance.feature.evenize;

import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.feature.financeOverview.SpendingOverviewService;
import at.devp.myfinance.repositories.SpendingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EvenizerServiceTest {

    @Mock
    private SpendingRepository spendingRepository;

    @Mock
    private SpendingOverviewService spendingOverviewService;

    @InjectMocks
    private EvenizerService underTest;


    @Test
    void whenEvenizeGivenSpendingWithAmountOf20AndDifferenceBetweenIncomesAndSpendingsIsNegative5ThenEvenizedAmountIs15() {
        final var spending = new Spending();
        spending.setAmount(new BigDecimal("20.00"));

        when(spendingRepository.findById(1L)).thenReturn(Optional.of(spending));
        when(spendingOverviewService.calculateDifferenceBetweenIncomesAndSpendings()).thenReturn(new BigDecimal("-5.00"));

        final var result = underTest.evenize(1L);

        assertThat(result, is(new BigDecimal("15.00")));
    }

    @Test
    void whenEvenizeGivenSpendingWithAmountOf20AndDifferenceBetweenIncomesAndSpendingsIsPositive5ThenEvenizedAmountIs25() {
        final var spending = new Spending();
        spending.setAmount(new BigDecimal("20.00"));

        when(spendingRepository.findById(1L)).thenReturn(Optional.of(spending));
        when(spendingOverviewService.calculateDifferenceBetweenIncomesAndSpendings()).thenReturn(new BigDecimal("5.00"));

        final var result = underTest.evenize(1L);

        assertThat(result, is(new BigDecimal("25.00")));

    }

}