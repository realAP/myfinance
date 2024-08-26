package at.devp.myfinance.controller.read;

import at.devp.myfinance.dto.RuleDropDownDto;
import at.devp.myfinance.dto.TransferDropDownDto;
import at.devp.myfinance.services.rule.RuleDropDownService;
import at.devp.myfinance.services.spending.edit.SpendingEditManagerService;
import at.devp.myfinance.services.transfer.TransferDropDownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class SpendingEditViewPageController {
  private final SpendingEditManagerService spendingEditManagerService;
  private final RuleDropDownService ruleDropDownService;
  private final TransferDropDownService transferDropDownService;


  @GetMapping("/spendings/{id}/edit")
  public String getEditSpendingPage(@PathVariable("id") Long id, Model model) {
    final var spendingEditDto = spendingEditManagerService.getSpendingEditDtoById(id);
    model.addAttribute("spendingEditDto", spendingEditDto);

    final var ruleDropDownDtos = ruleDropDownService.createRuleDropDownDto();
    model.addAttribute("ruleDropDownDtos", ruleDropDownDtos);
    model.addAttribute("ruleDropDownDto", new RuleDropDownDto());

    final var transferDropDownDtos = transferDropDownService.createDropDownDtos();
    model.addAttribute("transferDropDownDtos", transferDropDownDtos);
    model.addAttribute("transferDropDownDto", new TransferDropDownDto());

    return "editspending";
  }
}
