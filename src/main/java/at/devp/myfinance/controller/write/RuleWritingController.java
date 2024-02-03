package at.devp.myfinance.controller.write;

import at.devp.myfinance.dto.RuleCreationDto;
import at.devp.myfinance.services.rule.RuleChangeService;
import at.devp.myfinance.services.rule.RuleCreatorService;
import at.devp.myfinance.services.rule.RuleDeletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rules")
public class RuleWritingController {
  private final RuleChangeService ruleChangeService;
  private final RuleCreatorService ruleCreatorService;
  private final RuleDeletionService ruleDeletionService;

  @PostMapping
  public String createRule(@ModelAttribute RuleCreationDto ruleCreationDto) {
    ruleCreatorService.createRule(ruleCreationDto);
    return "redirect:/rules";
  }

  @PostMapping("/{id}/delete")
  public String deleteRule(@PathVariable("id") Long id) {
    ruleDeletionService.deleteRule(id);
    return "redirect:/rules";
  }

  @PostMapping("/{id}/confirmchange")
  public String confirmChange(@PathVariable("id") Long id) {
    ruleChangeService.confirmAmountChangeForRule(id);
    return "redirect:/rules";
  }

}
