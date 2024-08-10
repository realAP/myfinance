package at.devp.myfinance.services.rule;

import at.devp.myfinance.dto.RuleCreationDto;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.repositories.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RuleCreatorService {
  private final RuleRepository ruleRepository;

  public void createRule(final RuleCreationDto ruleCreationDto) {
    final Rule rule = new Rule();
    rule.setTo(ruleCreationDto.getTo());
    rule.setFrom(ruleCreationDto.getFrom());
    rule.setDescription(ruleCreationDto.getDescription());
    rule.setDateOfExecution(ruleCreationDto.getDateOfExecution());

    ruleRepository.save(rule);
  }
}
