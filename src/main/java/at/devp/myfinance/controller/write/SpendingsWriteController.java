package at.devp.myfinance.controller.write;

import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.dto.SpendingEditDto;
import at.devp.myfinance.services.spending.SpendingCreatorService;
import at.devp.myfinance.services.spending.SpendingDeletionService;
import at.devp.myfinance.services.spending.edit.SpendingEditManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/spendings")
public class SpendingsWriteController {
    private final SpendingCreatorService spendingCreatorService;
    private final SpendingDeletionService spendingDeletionService;
    private final SpendingEditManagerService spendingEditManagerService;

    @PostMapping
    public String createSpending(@ModelAttribute SpendingCreationDto spendingCreationDto){
        spendingCreatorService.createSpending(spendingCreationDto);
        return "redirect:/overview";
    }

    @GetMapping("/{id}/delete")
    public String deleteSpending(@PathVariable("id") Long id) {
        spendingDeletionService.deleteById(id);
        return "redirect:/overview";
    }

    @PostMapping("/edit")
    public String editSpending(@ModelAttribute SpendingEditDto spendingEditDto) {
        spendingEditManagerService.editSpending(spendingEditDto);
        return "redirect:/overview";
    }

}
