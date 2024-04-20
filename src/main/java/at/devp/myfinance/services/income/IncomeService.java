package at.devp.myfinance.services.income;

import at.devp.myfinance.entity.Earning;
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

    final var sumOfEarnings = earnings.stream().map(Earning::getAmount).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
    incomeDto.setSumOfEarnings(sumOfEarnings);

    return incomeDto;
  }

  private List<EarningDto> convert2EarningDtos(List<Earning> earnings) {
    return earnings.stream()
        .map(earning -> {
          final var earningDto = new EarningDto();
          earningDto.setId(earning.getId());
          earningDto.setDescription(earning.getDescription());
          earningDto.setAmount(earning.getAmount());
          return earningDto;
        }).toList();
  }
}
