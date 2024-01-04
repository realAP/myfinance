package at.devp.myfinance.controller;

import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.services.CategoryService;
import at.devp.myfinance.services.spending.SpendingEditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class EditSpendingController {

  private final CategoryService categoryService;
  private final SpendingEditService spendingEditService;


  @PostMapping("/spending/edit")
  public String updateSpending(@ModelAttribute SpendingCreationDto spendingCreationDto, Model model) {

    spendingEditService.editSpending(spendingCreationDto);
    final var categoryDtos = categoryService.createCategories();
    model.addAttribute("categoryDtos", categoryDtos);
    return "redirect:/overview";
  }

}
