package at.devp.myfinance.services.rule;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.dto.RuleOverviewDto;
import at.devp.myfinance.repositories.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleOverviewService {
  private final RuleRepository ruleRepository;
  private final Converter converter;


  public List<RuleOverviewDto> createRuleOverview() {
    final var rules = ruleRepository.findAll();
    return converter.convert2RuleOverviewDtos(rules);
  }
}
