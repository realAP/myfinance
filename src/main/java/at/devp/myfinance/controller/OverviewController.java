package at.devp.myfinance.controller;

import at.devp.myfinance.dto.RuleDropDownDto;
import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.dto.SpendingOverviewDto;
import at.devp.myfinance.dto.TransferDropDownDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OverviewController {

  private final CategoryService categoryService;
  private final OverviewService overviewService;
  private final RuleService ruleService;
  private final SpendingCreatorService spendingCreatorService;
  private final SpendingDeletionService spendingDeletionService;
  private final TransferDropDownService transferDropDownService;
  private final SpendingUpdateService spendingUpdateService;


  @GetMapping("/overview")
  public String getfinanceOverview(Model model) {
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

  @PostMapping("/newspending")
  public String addData(@ModelAttribute SpendingCreationDto spendingCreationDto, Model model) {
    model.addAttribute("spendingCreationDto", new SpendingCreationDto());
    spendingCreatorService.createSpending(spendingCreationDto);
    return "redirect:/overview";
  }

  @GetMapping("/delete/{id}")
  public String deleteData(@PathVariable("id") Long id) {
    spendingDeletionService.deleteById(id);
    return "redirect:/overview";
  }

  @GetMapping("spendings/edit/{id}")
  public String editStudentButton(@PathVariable("id") Long id, Model model) {
    final var spendingCreationDto = spendingUpdateService.getSpendingCreationDtoById(id);
    model.addAttribute("spendingCreationDto", spendingCreationDto);
    final var categoryDtos = categoryService.createCategories();
    model.addAttribute("categoryDtos", categoryDtos);

    final var ruleDropDownDtos = ruleService.createRuleDropDownDto();
    model.addAttribute("ruleDropDownDtos", ruleDropDownDtos);
    model.addAttribute("ruleDropDownDto", new RuleDropDownDto());

    final var transferDropDownDtos = transferDropDownService.createDropDownDtos();
    model.addAttribute("transferDropDownDtos", transferDropDownDtos);
    model.addAttribute("transferDropDownDto", new TransferDropDownDto());

    return "editstudent";
  }
}
