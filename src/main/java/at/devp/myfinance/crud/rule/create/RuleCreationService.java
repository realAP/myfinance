package at.devp.myfinance.crud.rule.create;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RuleCreationService {
  private final RuleRepository ruleRepository;
  private final SpaceRepository spaceRepository;

  public void createRule(final RuleCreationDto ruleCreationDto) {
    final Rule rule = new Rule();

    final var fromSpace = spaceRepository.findById(ruleCreationDto.getFromSpaceId()).orElseThrow(() -> new IllegalArgumentException("from space not found"));
    rule.setFromSpace(fromSpace);
    final var toSpace = spaceRepository.findById(ruleCreationDto.getToSpaceId()).orElseThrow(() -> new IllegalArgumentException("to space not found"));
    rule.setToSpace(toSpace);
    rule.setTo(ruleCreationDto.getTo());
    rule.setFrom(ruleCreationDto.getFrom());
    rule.setDescription(ruleCreationDto.getDescription());
    rule.setDateOfExecution(ruleCreationDto.getDateOfExecution());

    ruleRepository.save(rule);
  }
}
