package at.devp.myfinance.controller;

import at.devp.myfinance.dto.SpendingDto;
import at.devp.myfinance.services.CategoryService;
import at.devp.myfinance.services.createspending.SpendingCreatorSerivce;
import at.devp.myfinance.services.financeoverview.FinanceOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class FinanceOverviewController {

  private final FinanceOverviewService financeOverviewService;
  private final SpendingCreatorSerivce spendingCreatorSerivce;
  private final CategoryService categoryService;


  @GetMapping("/overview")
  public String getfinanceOverview(Model model) {
    final var spendingDtos = financeOverviewService.createOverview();
    model.addAttribute("spendingDtos", spendingDtos);
    final var categoryDtos = categoryService.createCategories();
    model.addAttribute("categoryDtos", categoryDtos);
    model.addAttribute("spendingDto", new SpendingDto());

    return "overview";
  }

  @PostMapping("/newspending")
  public String addData(@ModelAttribute SpendingDto spendingDto) {
    spendingCreatorSerivce.createSpending(spendingDto);
    return "redirect:/overview";
  }

  @PostMapping("/delete/{id}")
  public String deleteData(@PathVariable("id") Long id) {
    // Logik zum Löschen des Objekts anhand der ID
    spendingCreatorSerivce.deleteSpending(id);
    return "redirect:/overview";
  }

}
