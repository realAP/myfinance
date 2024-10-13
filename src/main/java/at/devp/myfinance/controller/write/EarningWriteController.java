package at.devp.myfinance.controller.write;

import at.devp.myfinance.crud.income.create.IncomeCreationDto;
import at.devp.myfinance.crud.income.delete.IncomeDeletionService;
import at.devp.myfinance.crud.income.create.IncomeCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/earnings")
public class EarningWriteController {
  private final IncomeCreationService incomeCreationService;
  private final IncomeDeletionService incomeDeletionService;

  @PostMapping
  public String createEarning(@ModelAttribute IncomeCreationDto incomeCreationDto) {
    incomeCreationService.createIncome(incomeCreationDto);
    return "redirect:/income";
  }

  @GetMapping("/{id}/delete")
  public String deleteEarning(@PathVariable("id") Long id) {
    incomeDeletionService.deleteById(id);
    return "redirect:/income";
  }

}
