package at.devp.myfinance.services.ruleservice;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.dto.RuleCreationDto;
import at.devp.myfinance.dto.RuleDropDownDto;
import at.devp.myfinance.dto.RuleOverviewDto;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.repositories.RuleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RuleService {

  private final RuleRepository ruleRepository;
  private final Converter converter;


  public List<RuleOverviewDto> createRuleOverview() {
    final var rules = ruleRepository.findAll();
    return converter.convert2RuleOverviewDtos(rules);
  }


  public void deleteRule(final Long id) {
    ruleRepository.deleteById(id);
  }

  public List<RuleDropDownDto> createRuleDropDownDto() {
    final var rules = ruleRepository.findAll();
    return converter.convert2RuleDropDownDtos(rules);
  }

  public Double calculateSumOfAllRules() {
    return ruleRepository.findAll().stream().map(Rule::getAmount).filter(Objects::nonNull).mapToDouble(Double::doubleValue).sum();
  }

  public void createRule(final RuleCreationDto ruleCreationDto) {
    final Rule rule = new Rule();
    rule.setTo(ruleCreationDto.getTo());
    rule.setFrom(ruleCreationDto.getFrom());
    rule.setDescription(ruleCreationDto.getDescription());

    ruleRepository.save(rule);
  }

  public void confirmChange(final Long id) {
    final Rule rule = ruleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Rule not found"));
    rule.setAmount(rule.calculateAmount());
    rule.setOldAmount(rule.calculateAmount());
    rule.setChange(false);

    ruleRepository.save(rule);
  }

  @Transactional
  public void updateAllRules() {
    final var rules = ruleRepository.findAll();
    rules.forEach(rule -> {
      rule.setAmount(rule.calculateAmount());
      rule.setChange(rule.calculateHasChange());
    });

    ruleRepository.saveAll(rules);
  }

  public void updateStatus(final Long ruleId) {
    final var rule = ruleRepository.findById(ruleId).orElseThrow(() -> new RuntimeException("Rule not found"));
    rule.updateStatus();
    ruleRepository.save(rule);
  }

}
