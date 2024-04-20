package at.devp.myfinance.controller.write;

import at.devp.myfinance.services.income.create.EarningCreationService;
import at.devp.myfinance.services.income.create.EarningCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/earnings")
public class EarningWriteController {
    private final EarningCreationService earningCreationService;
    //private final SpendingDeletionService spendingDeletionService;
    //private final SpendingEditManagerService spendingEditManagerService;

    @PostMapping
    public String createEarning(@ModelAttribute EarningCreationDto earningCreationDto){
      earningCreationService.createEarning(earningCreationDto);
        return "redirect:/income";
    }

    //@GetMapping("/{id}/delete")
    //public String deleteSpending(@PathVariable("id") Long id) {
    //    spendingDeletionService.deleteById(id);
    //    return "redirect:/overview";
    //}

    //@PostMapping("/edit")
    //public String editSpending(@ModelAttribute SpendingEditDto spendingEditDto) {
    //    spendingEditManagerService.editSpending(spendingEditDto);
    //    return "redirect:/overview";
    //}

}
