package at.devp.myfinance.crud.income;

import at.devp.myfinance.crud.income.create.IncomeCreationDto;
import at.devp.myfinance.crud.income.create.IncomeCreationService;
import at.devp.myfinance.crud.income.delete.IncomeDeletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/earnings")
public class IncomeController {
  private final IncomeCreationService incomeCreationService;
  private final IncomeDeletionService incomeDeletionService;

  @PostMapping
  public String createIncome(@RequestParam IncomeCreationDto incomeCreationDto) {
    incomeCreationService.createIncome(incomeCreationDto);
    return "redirect:/income";
  }

  @GetMapping("/{id}/delete")
  public String deleteIncome(@PathVariable("id") Long id) {
    incomeDeletionService.deleteById(id);
    return "redirect:/income";
  }

}
