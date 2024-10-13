package at.devp.myfinance.feature.financeoverview;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpendingOverviewController {

    private final SpendingOverviewService spendingOverviewService;

    @GetMapping("/fe/overview")
    public List<SpendingCategoryBlockDto> getFinanceOverview() {
        return spendingOverviewService.createOverview();
    }

    @GetMapping("/fe/overview/sum")
    public BigDecimal calculateTotalSpending() {
        return spendingOverviewService.calculateSumOfSpendings();
    }

    @GetMapping("fe/overview/diff")
    public BigDecimal calculateDifference() {
        return spendingOverviewService.calculateDifferenceBetweenIncomesAndSpendings();
    }
}
