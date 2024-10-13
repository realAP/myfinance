package at.devp.myfinance.feature.evenize;

import at.devp.myfinance.feature.financeOverview.SpendingOverviewService;
import at.devp.myfinance.repositories.SpendingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class EvenizerService {

    private final SpendingRepository spendingRepository;
    private final SpendingOverviewService spendingOverviewService;

    public BigDecimal evenize(final Long spendingId) {
        final var spending = spendingRepository.findById(spendingId).orElseThrow(() -> new IllegalArgumentException("Spending: " + spendingId + " not found"));
        final var amountToEvenize = spending.getAmount();

        final var incomeAndSpendingDifference = spendingOverviewService.calculateDifferenceBetweenIncomesAndSpendings();
        return amountToEvenize.add(incomeAndSpendingDifference);
    }

}
