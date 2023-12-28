package at.devp.myfinance.services.financeoverview;

import at.devp.myfinance.dto.RuleDto;
import at.devp.myfinance.dto.SpendingDto;
import at.devp.myfinance.dto.TransferDto;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.SpendingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceOverviewService {

  private final SpendingRepository spendingRepository;

  @Transactional
  public List<SpendingDto> createOverview() {

    final var spendings = spendingRepository.findAll();
    return convert2SpendingDtos(spendings);


  }

  private List<SpendingDto> convert2SpendingDtos(final List<Spending> spendings) {
    return spendings.stream()
        .map(this::createSpendingDto)
        .toList();
  }

  private SpendingDto createSpendingDto(Spending spending) {
    final var spendingDto = new SpendingDto();
    spendingDto.setAmount(spending.getAmount());
    spendingDto.setDescription(spending.getDescription());
    spendingDto.setCategory(spending.getCategory());

    spendingDto.setRuleDto(createRuleDto(spending.getRule()));
    spendingDto.setTransferDto(createTransferDto(spending.getTransfer()));

    return spendingDto;
  }

  private RuleDto createRuleDto(final Rule rule) {
    final var ruleDto = new RuleDto();
    ruleDto.setDescription(rule.getDescription());
    return ruleDto;
  }

  private TransferDto createTransferDto(final Transfer transfer) {
    final var transferDto = new TransferDto();
    transferDto.setDescription(transfer.getDescription());
    return transferDto;
  }
}
