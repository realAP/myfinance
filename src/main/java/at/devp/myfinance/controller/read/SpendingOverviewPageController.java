package at.devp.myfinance.controller.read;

import at.devp.myfinance.dto.RuleDropDownDto;
import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.dto.TransferDropDownDto;
import at.devp.myfinance.feature.financeoverview.SpendingOverviewService;
import at.devp.myfinance.feature.financeoverview.SpendingRowDto;
import at.devp.myfinance.feature.financeoverview.SpendingCategoryBlockDto;
import at.devp.myfinance.services.rule.RuleDropDownService;
import at.devp.myfinance.services.transfer.TransferDropDownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SpendingOverviewPageController {

  private final RuleDropDownService ruleDropDownService;
  private final SpendingOverviewService spendingOverviewService;
  private final TransferDropDownService transferDropDownService;


  @GetMapping("/overview")
  public String getfinanceOverview(Model model) {
    final var spendingCategoryBlockDtos = spendingOverviewService.createOverview();
    model.addAttribute("spendingCategoryBlockDtos", spendingCategoryBlockDtos);
    model.addAttribute("spendingCategoryBlockDto", new SpendingCategoryBlockDto());
    model.addAttribute("spendingRowDto", new SpendingRowDto());
    model.addAttribute("spendingCreationDto", new SpendingCreationDto());

    final var sumOfSpendings = spendingOverviewService.calculateSum();
    model.addAttribute("sumOfSpendings", sumOfSpendings);


    final var ruleDropDownDtos = ruleDropDownService.createRuleDropDownDto();
    model.addAttribute("ruleDropDownDtos", ruleDropDownDtos);
    model.addAttribute("ruleDropDownDto", new RuleDropDownDto());

    final var transferDropDownDtos = transferDropDownService.createDropDownDtos();
    model.addAttribute("transferDropDownDtos", transferDropDownDtos);
    model.addAttribute("transferDropDownDto", new TransferDropDownDto());

    return "overview";
  }
}
