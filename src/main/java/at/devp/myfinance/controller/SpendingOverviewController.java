package at.devp.myfinance.controller;

import at.devp.myfinance.dto.RuleDropDownDto;
import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.dto.SpendingOverviewDto;
import at.devp.myfinance.dto.TransferDropDownDto;
import at.devp.myfinance.services.CategoryService;
import at.devp.myfinance.services.financeoverview.SpendingOverviewService;
import at.devp.myfinance.services.rule.RuleDropDownService;
import at.devp.myfinance.services.spending.SpendingCreatorService;
import at.devp.myfinance.services.spending.SpendingDeletionService;
import at.devp.myfinance.services.spending.SpendingEditService;
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
public class SpendingOverviewController {

  private final CategoryService categoryService;
  private final SpendingOverviewService spendingOverviewService;
  private final SpendingCreatorService spendingCreatorService;
  private final SpendingDeletionService spendingDeletionService;
  private final TransferDropDownService transferDropDownService;
  private final SpendingEditService spendingEditService;
  private final RuleDropDownService ruleDropDownService;


  @GetMapping("/overview")
  public String getfinanceOverview(Model model) {
    final var spendingDtos = spendingOverviewService.createOverview();
    model.addAttribute("spendingDtos", spendingDtos);
    final var categoryDtos = categoryService.createCategories();
    model.addAttribute("categoryDtos", categoryDtos);
    model.addAttribute("spendingOverviewDto", new SpendingOverviewDto());
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
    final var spendingCreationDto = spendingEditService.getSpendingCreationDtoById(id);
    model.addAttribute("spendingCreationDto", spendingCreationDto);
    final var categoryDtos = categoryService.createCategories();
    model.addAttribute("categoryDtos", categoryDtos);

    final var ruleDropDownDtos = ruleDropDownService.createRuleDropDownDto();
    model.addAttribute("ruleDropDownDtos", ruleDropDownDtos);
    model.addAttribute("ruleDropDownDto", new RuleDropDownDto());

    final var transferDropDownDtos = transferDropDownService.createDropDownDtos();
    model.addAttribute("transferDropDownDtos", transferDropDownDtos);
    model.addAttribute("transferDropDownDto", new TransferDropDownDto());

    return "editstudent";
  }
}
