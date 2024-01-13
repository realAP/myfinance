package at.devp.myfinance.services.rule;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RuleUpdateService {

  private final RuleRepository ruleRepository;

  public void updateStatus(final Rule rule) {
    rule.updateAmountAndChange();
    ruleRepository.save(rule);
  }

  public void addSpendingAndUpdate(final Rule selectedRule, final Spending spending) {
    selectedRule.getSpendings().add(spending);

    final var sumOfSpendings = selectedRule.getSpendings().stream().map(Spending::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    selectedRule.setAmount(sumOfSpendings);
    selectedRule.setChange(sumOfSpendings != selectedRule.getOldAmount());

    ruleRepository.save(selectedRule);
  }
}