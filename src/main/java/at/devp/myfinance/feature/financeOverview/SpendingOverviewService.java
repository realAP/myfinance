package at.devp.myfinance.feature.financeOverview;

import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.feature.sumOfIncome.SumOfIncomeService;
import at.devp.myfinance.repositories.SpendingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpendingOverviewService {

    private final SpendingRepository spendingRepository;
    private final SumOfIncomeService sumOfIncomeService;

    @Transactional
    public List<SpendingCategoryBlockDto> createOverview() {
        final var spendings = spendingRepository.findAll();
        final var spendingsByCategory = spendings.stream().collect(Collectors.groupingBy(Spending::getCategory, Collectors.toList()));
        final var sumOfIncome = sumOfIncomeService.getSum();

        return spendingsByCategory.entrySet().stream().map(entry -> {
            final var spendingCategoryBlockDto = new SpendingCategoryBlockDto();
            spendingCategoryBlockDto.setCategory(entry.getKey().getName());
            spendingCategoryBlockDto.setSpendingRowDtos(convert2SortedSpendingRowDtos(entry.getValue()));
            spendingCategoryBlockDto.setSpendingSumPerCategory(entry.getValue().stream().map(Spending::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
            spendingCategoryBlockDto.setPercentageToIncome(calculatePercentageToIncome(spendingCategoryBlockDto.getSpendingSumPerCategory(), sumOfIncome));
            return spendingCategoryBlockDto;
        }).sorted(Comparator.comparing(SpendingCategoryBlockDto::getCategory)).toList();
    }

    public BigDecimal calculateSumOfSpendings() {
        final var spendings = spendingRepository.findAll();
        return spendings.stream().map(Spending::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<SpendingRowDto> convert2SortedSpendingRowDtos(final List<Spending> spendings) {
        return spendings
                .stream()
                .map(this::convert2SpendingTableDto)
                .sorted(Comparator.comparing(SpendingRowDto::getAmount))
                .toList();
    }

    private SpendingRowDto convert2SpendingTableDto(final Spending spending) {
        final var spendingRowDto = new SpendingRowDto();
        spendingRowDto.setId(spending.getId());
        spendingRowDto.setAmount(spending.getAmount());
        spendingRowDto.setDescription(spending.getDescription());
        spendingRowDto.setCategory(spending.getCategory().toString());
        spendingRowDto.setRuleDescription(spending.getRule().getDescription());
        spendingRowDto.setTransferDescription(spending.getTransfer().getDescription());
        spendingRowDto.setRuleId(spending.getRule().getId());
        spendingRowDto.setTransferId(spending.getTransfer().getId());
        spendingRowDto.setCategoryId(spending.getCategory().getId());
        return spendingRowDto;
    }

    public BigDecimal calculateDifferenceBetweenIncomesAndSpendings() {
        return sumOfIncomeService.getSum().subtract(calculateSumOfSpendings());

    }

    private BigDecimal calculatePercentageToIncome(final BigDecimal categorySum, final BigDecimal incomeSum) {
        return categorySum.divide(incomeSum, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
    }
}
