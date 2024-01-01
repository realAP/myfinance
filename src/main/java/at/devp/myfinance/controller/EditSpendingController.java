package at.devp.myfinance.controller;

import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.services.CategoryService;
import at.devp.myfinance.services.SpendingDeletionService;
import at.devp.myfinance.services.financeoverview.OverviewService;
import at.devp.myfinance.services.ruleservice.RuleService;
import at.devp.myfinance.services.spending.SpendingUpdateService;
import at.devp.myfinance.services.spending.createspending.SpendingCreatorService;
import at.devp.myfinance.services.transfer.TransferDropDownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class EditSpendingController {

  private final CategoryService categoryService;
  private final OverviewService overviewService;
  private final RuleService ruleService;
  private final SpendingCreatorService spendingCreatorService;
  private final SpendingDeletionService spendingDeletionService;
  private final TransferDropDownService transferDropDownService;
  private final SpendingUpdateService spendingUpdateService;


  @PostMapping("/spending/edit")
  public String updateSpending(@ModelAttribute SpendingCreationDto spendingCreationDto, Model model) {

    spendingUpdateService.updateSpending(spendingCreationDto);
    final var categoryDtos = categoryService.createCategories();
    model.addAttribute("categoryDtos", categoryDtos);
    return "redirect:/overview";
  }

}
