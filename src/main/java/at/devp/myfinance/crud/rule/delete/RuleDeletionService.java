package at.devp.myfinance.crud.rule.delete;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RuleDeletionService {
  private final RuleRepository ruleRepository;

  public void removeSpendingAndUpdate(final Rule rule, final Spending spending) {
    rule.getSpendings().remove(spending);
    rule.updateAmountAndChange();
    ruleRepository.save(rule);
  }
  public void deleteRule(final Long id) {
    ruleRepository.deleteById(id);
  }
}
