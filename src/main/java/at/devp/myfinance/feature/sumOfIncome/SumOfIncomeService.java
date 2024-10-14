package at.devp.myfinance.feature.sumOfIncome;

import at.devp.myfinance.entity.Income;
import at.devp.myfinance.repositories.IncomeRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SumOfIncomeService {
    private final IncomeRepository incomeRepository;

    @NonNull
    public BigDecimal getSum() {
        return incomeRepository
                .findAll()
                .stream()
                .map(Income::getAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
