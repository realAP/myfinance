package at.devp.myfinance.controller.read;

import at.devp.myfinance.dto.RuleDropDownDto;
import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.dto.SpendingOverviewDto;
import at.devp.myfinance.dto.TransferDropDownDto;
import at.devp.myfinance.services.CategoryService;
import at.devp.myfinance.services.spending.SpendingDeletionService;
import at.devp.myfinance.services.financeoverview.OverviewService;
import at.devp.myfinance.services.ruleservice.RuleService;
import at.devp.myfinance.services.spending.SpendingEditService;
import at.devp.myfinance.services.spending.createspending.SpendingCreatorService;
import at.devp.myfinance.services.transfer.TransferDropDownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class OverviewController {

  private final CategoryService categoryService;
  private final OverviewService overviewService;
  private final RuleService ruleService;
  private final SpendingCreatorService spendingCreatorService;
  private final SpendingDeletionService spendingDeletionService;
  private final TransferDropDownService transferDropDownService;
  private final SpendingEditService spendingEditService;


  @GetMapping("/overview")
  public String getfinanceOverviewPage(Model model) {
    final var spendingDtos = overviewService.createOverview();
    model.addAttribute("spendingDtos", spendingDtos);
    final var categoryDtos = categoryService.createCategories();
    model.addAttribute("categoryDtos", categoryDtos);
    model.addAttribute("spendingOverviewDto", new SpendingOverviewDto());
    model.addAttribute("spendingCreationDto", new SpendingCreationDto());

    final var sumOfSpendings = overviewService.calculateSum();
    model.addAttribute("sumOfSpendings", sumOfSpendings);


    final var ruleDropDownDtos = ruleService.createRuleDropDownDto();
    model.addAttribute("ruleDropDownDtos", ruleDropDownDtos);
    model.addAttribute("ruleDropDownDto", new RuleDropDownDto());

    final var transferDropDownDtos = transferDropDownService.createDropDownDtos();
    model.addAttribute("transferDropDownDtos", transferDropDownDtos);
    model.addAttribute("transferDropDownDto", new TransferDropDownDto());

    return "overview";
  }

}
