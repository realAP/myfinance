package at.devp.myfinance.crud.spending.edit;

import at.devp.myfinance.dto.SpendingEditDto;
import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.services.rule.RuleDeletionService;
import at.devp.myfinance.services.rule.RuleUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingRuleAssignmentService {

    private final RuleRepository ruleRepository;
    private final RuleUpdateService ruleUpdateService;
    private final RuleDeletionService ruleDeletionService;

    @Transactional
    public void setRule(SpendingEditDto spendingEditDto, Spending spending) {
        final var selectedRule = ruleRepository.findById(spendingEditDto.getRuleId()).orElseThrow(() -> new IllegalArgumentException("Rule with id " + spendingEditDto.getRuleId() + " not found"));
        final var oldRule = spending.getRule();
        spending.setRule(selectedRule);
        editRuleAndUpdate(oldRule, selectedRule, spending);
    }

    private void editRuleAndUpdate(Rule oldRule, Rule selectedRule, final Spending spending) {
        ruleDeletionService.removeSpendingAndUpdate(oldRule, spending);
        ruleUpdateService.addSpendingAndUpdate(selectedRule, spending);
    }

}
