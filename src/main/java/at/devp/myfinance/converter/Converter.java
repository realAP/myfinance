package at.devp.myfinance.converter;

import at.devp.myfinance.dto.RuleDto;
import at.devp.myfinance.dto.SpendingDto;
import at.devp.myfinance.dto.TransferDto;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Converter {
  public List<SpendingDto> convert2SpendingDtos(final List<Spending> spendings) {
    return spendings.stream()
        .map(this::convert2SpendingDto)
        .toList();
  }

  public SpendingDto convert2SpendingDto(Spending spending) {
    final var spendingDto = new SpendingDto();
    spendingDto.setId(spending.getId());
    spendingDto.setAmount(spending.getAmount());
    spendingDto.setDescription(spending.getDescription());
    spendingDto.setCategory(spending.getCategory());

    spendingDto.setRuleDto(createRuleDto(spending.getRule()));
    spendingDto.setTransferDto(createTransferDto(spending.getTransfer()));

    return spendingDto;
  }

  public RuleDto createRuleDto(final Rule rule) {
    final var ruleDto = new RuleDto();
    ruleDto.setDescription(rule.getDescription());
    return ruleDto;
  }

  public TransferDto createTransferDto(final Transfer transfer) {
    final var transferDto = new TransferDto();
    transferDto.setDescription(transfer.getDescription());
    return transferDto;
  }
}
