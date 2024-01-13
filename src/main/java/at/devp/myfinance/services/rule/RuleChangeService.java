package at.devp.myfinance.services.rule;

import at.devp.myfinance.repositories.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RuleChangeService {
  private final RuleRepository ruleRepository;

  public void confirmAmountChangeForRule(final Long ruleId) {
    final var rule = ruleRepository.findById(ruleId).orElseThrow(() -> new IllegalArgumentException("Rule not found"));
    rule.setAmount(rule.calculateAmount());
    rule.setOldAmount(rule.calculateAmount());
    rule.setChange(false);

    ruleRepository.save(rule);
  }
}
