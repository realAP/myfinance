package at.devp.myfinance.controller.read;

import at.devp.myfinance.dto.RuleCreationDto;
import at.devp.myfinance.dto.RuleOverviewDto;
import at.devp.myfinance.services.rule.RuleOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class RulesOverviewPageController {

  private final RuleOverviewService ruleOverviewService;

  @GetMapping("/rules")
  public String getRulesPage(Model model) {
    final var ruleDtos = ruleOverviewService.createRuleOverview();

    model.addAttribute("ruleOverviewDtos", ruleDtos);
    model.addAttribute("ruleOverviewDto", new RuleOverviewDto());
    model.addAttribute("ruleCreationDto", new RuleCreationDto());

    final var sumOfAllRules = ruleOverviewService.calculateSumOfAllRules();
    model.addAttribute("sumOfAllRules", sumOfAllRules);

    return "rules";
  }
}
