package at.devp.myfinance.controller;

import at.devp.myfinance.dto.RuleCreationDto;
import at.devp.myfinance.dto.RuleOverviewDto;
import at.devp.myfinance.services.ruleservice.RuleService;
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


  private final RuleService ruleService;

  @GetMapping("/rules")
  public String getRules(Model model) {
    final var ruleDtos = ruleService.createRuleOverview();

    model.addAttribute("ruleOverviewDtos", ruleDtos);
    model.addAttribute("ruleOverviewDto", new RuleOverviewDto());
    model.addAttribute("ruleCreationDto", new RuleCreationDto());

    return "rules";
  }

  @PostMapping("/newrule")
  public String newRule(@ModelAttribute RuleCreationDto ruleCreationDto, Model model) {
    ruleService.createRule(ruleCreationDto);
    return "redirect:/rules";
  }

  @PostMapping("/removerule/{id}")
  public String deleteRule(@PathVariable("id") Long id) {
    ruleService.deleteRule(id);
    return "redirect:/rules";
  }

  @PostMapping("/confirmchangerule/{id}")
  public String confirmChange(@PathVariable("id") Long id) {
    ruleService.confirmChange(id);
    return "redirect:/rules";
  }

}
