package at.devp.myfinance.services.rule;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.RuleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RuleEditService {
  private final RuleRepository ruleRepository;

  private final RuleUpdateService ruleUpdateService;
  private final RuleDeletionService ruleDeletionService;

  @Transactional
  public void editRuleAndUpdate(Rule oldRule, Rule selectedRule, final Spending spending) {
    ruleDeletionService.removeSpendingAndUpdate(oldRule, spending);
    ruleUpdateService.addSpendingAndUpdate(selectedRule, spending);
  }

}
