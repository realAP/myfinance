package at.devp.myfinance.crud.spending.edit;

import at.devp.myfinance.dto.SpendingEditDto;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.services.rule.RuleEditService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingRuleService {

  private final RuleEditService ruleEditService;
  private final RuleRepository ruleRepository;

  @Transactional
  public void setRule(SpendingEditDto spendingEditDto, Spending spending) {
    final var selectedRule = ruleRepository.findById(spendingEditDto.getRuleId()).orElseThrow(() -> new IllegalArgumentException("Rule with id " + spendingEditDto.getRuleId() + " not found"));
    final var oldRule = spending.getRule();
    spending.setRule(selectedRule);
    ruleEditService.editRuleAndUpdate(oldRule, selectedRule, spending);
  }

}
