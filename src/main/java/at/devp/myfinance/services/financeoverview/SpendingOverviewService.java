package at.devp.myfinance.services.financeoverview;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.dto.SpendingOverviewDto;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.SpendingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpendingOverviewService {

  private final SpendingRepository spendingRepository;
  private final Converter converter;

  @Transactional
  public List<SpendingOverviewDto> createOverview() {
    final var spendings = spendingRepository.findAll();
    return converter.convert2SpendingDtos(spendings);
  }

  public BigDecimal calculateSum() {
    return spendingRepository.findAll().stream().map(Spending::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
