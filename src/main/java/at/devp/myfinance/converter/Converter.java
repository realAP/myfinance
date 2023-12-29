package at.devp.myfinance.converter;

import at.devp.myfinance.dto.RuleDropDownDto;
import at.devp.myfinance.dto.RuleOverviewDto;
import at.devp.myfinance.dto.SpendingOverviewDto;
import at.devp.myfinance.dto.TransferDto;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Converter {
  public List<SpendingOverviewDto> convert2SpendingDtos(final List<Spending> spendings) {
    return spendings.stream()
        .map(this::convert2SpendingOverviewDto)
        .toList();
  }

  public List<RuleOverviewDto> convert2RuleOverviewDtos(final List<Rule> rules) {
    return rules.stream().map(this::convert2RuleOverviewDto).toList();
  }

  public SpendingOverviewDto convert2SpendingOverviewDto(Spending spending) {
    final var spendingDto = new SpendingOverviewDto();
    spendingDto.setId(spending.getId());
    spendingDto.setAmount(spending.getAmount());
    spendingDto.setDescription(spending.getDescription());
    spendingDto.setCategory(spending.getCategory());

    spendingDto.setRuleDescription(spending.getRule().getDescription());
    //spendingDto.setTransferDto(createTransferDto(spending.getTransfer()));

    return spendingDto;
  }

  public RuleOverviewDto convert2RuleOverviewDto(final Rule rule) {
    final var ruleDto = new RuleOverviewDto();
    ruleDto.setDescription(rule.getDescription());
    ruleDto.setAmount(rule.calculateAmount());
    ruleDto.setFrom(rule.getFrom());
    ruleDto.setTo(rule.getTo());
    return ruleDto;
  }

  public TransferDto createTransferDto(final Transfer transfer) {
    final var transferDto = new TransferDto();
    transferDto.setDescription(transfer.getDescription());
    return transferDto;
  }

  public List<RuleDropDownDto> convert2RuleDropDownDtos(List<Rule> rules) {
    return rules.stream()
        .map(this::convert2RuleDropDownDto)
        .toList();
  }

  private RuleDropDownDto convert2RuleDropDownDto(final Rule rule) {
    final var ruleDropDownDto = new RuleDropDownDto();
    ruleDropDownDto.setDescription(rule.getDescription());
    ruleDropDownDto.setId(rule.getId());
    return ruleDropDownDto;
  }
}
