package at.devp.myfinance.services.ruleservice;

import at.devp.myfinance.repositories.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RuleStatusService {

  private final RuleRepository ruleRepository;

  public void updateStatus(final Long ruleId) {
    final var rule = ruleRepository.findById(ruleId).orElseThrow(() -> new RuntimeException("Rule not found"));
    rule.updateStatus();
    ruleRepository.save(rule);
  }

}
