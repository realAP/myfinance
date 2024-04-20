package at.devp.myfinance.controller.write;

import at.devp.myfinance.services.income.EarningDeletionService;
import at.devp.myfinance.services.income.create.EarningCreationDto;
import at.devp.myfinance.services.income.create.EarningCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/earnings")
public class EarningWriteController {
  private final EarningCreationService earningCreationService;
  private final EarningDeletionService earningDeletionService;

  @PostMapping
  public String createEarning(@ModelAttribute EarningCreationDto earningCreationDto) {
    earningCreationService.createEarning(earningCreationDto);
    return "redirect:/income";
  }

  @GetMapping("/{id}/delete")
  public String deleteSpending(@PathVariable("id") Long id) {
    earningDeletionService.deleteById(id);
    return "redirect:/income";
  }

}
