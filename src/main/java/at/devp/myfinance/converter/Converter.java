package at.devp.myfinance.converter;

import at.devp.myfinance.dto.*;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.feature.financeoverview.SpendingOverviewDto;
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

  public List<TransferOverviewDto> convert2TransferOverviewDtos(final List<Transfer> rules) {
    return rules.stream().map(this::convert2TransferOverviewDto).toList();
  }

  public SpendingOverviewDto convert2SpendingOverviewDto(Spending spending) {
    final var spendingDto = new SpendingOverviewDto();
    spendingDto.setId(spending.getId());
    spendingDto.setAmount(spending.getAmount());
    spendingDto.setDescription(spending.getDescription());
    spendingDto.setCategoryDescription(spending.getCategory().getName());

    spendingDto.setRuleDescription(spending.getRule().getDescription());
    spendingDto.setTransferDescription(spending.getTransfer().getDescription());

    return spendingDto;
  }

  public RuleOverviewDto convert2RuleOverviewDto(final Rule rule) {
    final var ruleDto = new RuleOverviewDto();
    ruleDto.setId(rule.getId());
    ruleDto.setDescription(rule.getDescription());
    ruleDto.setAmount(rule.getAmount());
    ruleDto.setOldAmount(rule.getOldAmount());
    ruleDto.setFrom(rule.getFrom());
    ruleDto.setTo(rule.getTo());
    ruleDto.setChange(rule.isChange());
    return ruleDto;
  }

  public TransferOverviewDto convert2TransferOverviewDto(final Transfer transfer) {
    final var transferOverviewDto = new TransferOverviewDto();
    transferOverviewDto.setId(transfer.getId());
    transferOverviewDto.setDescription(transfer.getDescription());
    transferOverviewDto.setAmount(transfer.getAmount());
    transferOverviewDto.setOldAmount(transfer.getOldAmount());
    transferOverviewDto.setFrom(transfer.getFrom());
    transferOverviewDto.setTo(transfer.getTo());
    transferOverviewDto.setChange(transfer.isChange());
    return transferOverviewDto;
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

  public List<TransferDropDownDto> convert2TransferDropDownDtos(List<Transfer> transfers) {
    return transfers.stream()
        .map(this::convert2TransferDropDownDto)
        .toList();
  }

  private TransferDropDownDto convert2TransferDropDownDto(final Transfer transfer) {
    final var ruleDropDownDto = new TransferDropDownDto();
    ruleDropDownDto.setDescription(transfer.getDescription());
    ruleDropDownDto.setId(transfer.getId());
    return ruleDropDownDto;
  }
}
