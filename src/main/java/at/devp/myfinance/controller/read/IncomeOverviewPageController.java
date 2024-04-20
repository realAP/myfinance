package at.devp.myfinance.controller.read;

import at.devp.myfinance.services.income.EarningDto;
import at.devp.myfinance.services.income.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IncomeOverviewPageController {

  private final IncomeService incomeService;


  @GetMapping("/income")
  public String getIncomeOverview(Model model) {
    final var incomeDto = incomeService.createIncomeOverview();
    model.addAttribute("incomeDto", incomeDto);
    model.addAttribute("earningDto", new EarningDto());

    return "income";
  }
}
