package at.devp.myfinance.controller;

import at.devp.myfinance.dto.RuleCreationDto;
import at.devp.myfinance.dto.RuleOverviewDto;
import at.devp.myfinance.services.rule.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RulesController {

  private final RuleChangeService ruleChangeService;
  private final RuleCreatorService ruleCreatorService;
  private final RuleDeletionService ruleDeletionService;
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

  @PostMapping("/newrule")
  public String newRule(@ModelAttribute RuleCreationDto ruleCreationDto, Model model) {
    ruleCreatorService.createRule(ruleCreationDto);
    return "redirect:/rules";
  }

  @PostMapping("/removerule/{id}")
  public String deleteRule(@PathVariable("id") Long id) {
    ruleDeletionService.deleteRule(id);
    return "redirect:/rules";
  }

  @PostMapping("/confirmchangerule/{id}")
  public String confirmChange(@PathVariable("id") Long id) {
    ruleChangeService.confirmAmountChangeForRule(id);
    return "redirect:/rules";
  }

}
