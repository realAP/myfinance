package at.devp.myfinance.services.rule;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.RuleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RuleUpdateService {

  private final RuleRepository ruleRepository;

  public void updateStatus(final Long ruleId) {
    final var rule = ruleRepository.findById(ruleId).orElseThrow(() -> new RuntimeException("Rule not found"));
    rule.updateAmountAndChange();
    ruleRepository.save(rule);
  }

  public void updateStatus(final Rule rule) {
    rule.updateAmountAndChange();
    ruleRepository.save(rule);
  }

  public void addSpendingAndUpdate(final Spending spending) {
    final var rule = spending.getRule();
    rule.getSpendings().add(spending);

    final var sumOfSpendings = rule.getSpendings().stream().map(Spending::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    rule.setAmount(sumOfSpendings);
    rule.setChange(sumOfSpendings != rule.getOldAmount());

    ruleRepository.save(rule);
  }


  @Transactional
  public void editRuleAndUpdate(Rule oldRule, Rule selectedRule, final Spending spending) {
    oldRule.getSpendings().remove(spending);
    oldRule.updateAmountAndChange();
    ruleRepository.save(oldRule);

    selectedRule.getSpendings().add(spending);
    selectedRule.updateAmountAndChange();
    ruleRepository.save(selectedRule);
  }

  public void editRuleAndUpdate(final Long spendingId, final Rule selectedRule) {

  }

  @Transactional
  public void updateAll() {
    final var rules = ruleRepository.findAll();
    rules.forEach(rule -> {
      rule.setAmount(rule.calculateAmount());
      rule.setChange(rule.calculateHasChange());
    });

    ruleRepository.saveAll(rules);
  }

}