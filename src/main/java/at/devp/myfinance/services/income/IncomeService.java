package at.devp.myfinance.services.income;

import at.devp.myfinance.entity.Income;
import at.devp.myfinance.repositories.IncomeRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepository incomeRepository;

    @NonNull
    public IncomeDto createIncomeOverview() {
        final var earnings = incomeRepository.findAll();
        final var earningDtos = convert2EarningDtos(earnings);
        final var incomeDto = new IncomeDto();
        incomeDto.getEarningDtos().addAll(earningDtos);
        incomeDto.setSumOfEarnings(sumOfEarnings());
        return incomeDto;
    }

    @NonNull
    public BigDecimal sumOfEarnings() {
        final var earnings = incomeRepository.findAll();
        return earnings.stream().map(Income::getAmount).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<EarningDto> convert2EarningDtos(List<Income> incomes) {
        return incomes.stream()
                .map(earning -> {
                    final var earningDto = new EarningDto();
                    earningDto.setId(earning.getId());
                    earningDto.setDescription(earning.getDescription());
                    earningDto.setAmount(earning.getAmount());
                    return earningDto;
                }).toList();
    }
}
