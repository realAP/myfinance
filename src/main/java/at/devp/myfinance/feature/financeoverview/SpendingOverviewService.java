package at.devp.myfinance.feature.financeoverview;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.services.income.IncomeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpendingOverviewService {

    private final SpendingRepository spendingRepository;
    private final IncomeService incomeService;
    private final Converter converter;

    @Transactional
    public List<SpendingCategoryBlockDto> createOverview() {
        final var spendings = spendingRepository.findAll();

        final var spendingsByCategory = spendings.stream().collect(Collectors.groupingBy(Spending::getCategory, Collectors.toList()));
        return spendingsByCategory.entrySet().stream().map(entry -> {
            final var spendingTableDto = new SpendingCategoryBlockDto();
            spendingTableDto.setCategory(entry.getKey().getName());
            spendingTableDto.setSpendingRowDtos(convert2SortedSpendingRowDtos(entry.getValue()));
            spendingTableDto.setSpendingSumPerCategory(entry.getValue().stream().map(Spending::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
            return spendingTableDto;
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
        return incomeService.sumOfEarnings().subtract(calculateSumOfSpendings());
    }
}
