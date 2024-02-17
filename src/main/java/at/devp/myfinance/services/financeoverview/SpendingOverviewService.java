package at.devp.myfinance.services.financeoverview;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.SpendingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpendingOverviewService {

  private final SpendingRepository spendingRepository;
  private final Converter converter;

  @Transactional
  public List<SpendingCategoryBlockDto> createOverview() {
    final var spendings = spendingRepository.findAll();

    final var spendingsByCategory = spendings.stream().collect(Collectors.groupingBy(Spending::getCategory, Collectors.toList()));
    return spendingsByCategory.entrySet().stream().map(entry -> {
      final var spendingTableDto = new SpendingCategoryBlockDto();
      spendingTableDto.setCategory(entry.getKey());
      spendingTableDto.setSpendingRowDtos(convert2SpendingRowDtos(entry.getValue()));
      spendingTableDto.setSpendingSumPerCategory(entry.getValue().stream().map(Spending::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
      return spendingTableDto;
    }).toList();
  }

  private List<SpendingRowDto> convert2SpendingRowDtos(final List<Spending> spendings) {
    return spendings.stream().map(this::convert2SpendingTableDto).toList();
  }

  private SpendingRowDto convert2SpendingTableDto(final Spending spending) {
    final var spendingRowDto = new SpendingRowDto();
    spendingRowDto.setId(spending.getId());
    spendingRowDto.setAmount(spending.getAmount());
    spendingRowDto.setDescription(spending.getDescription());
    spendingRowDto.setCategory(spending.getCategory().toString());
    spendingRowDto.setRuleDescription(spending.getRule().getDescription());
    spendingRowDto.setTransferDescription(spending.getTransfer().getDescription());
    return spendingRowDto;
  }

  public BigDecimal calculateSum() {
    return spendingRepository.findAll().stream().map(Spending::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
