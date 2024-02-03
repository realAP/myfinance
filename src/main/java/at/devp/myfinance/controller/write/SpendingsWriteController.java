package at.devp.myfinance.controller.write;

import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.dto.SpendingEditDto;
import at.devp.myfinance.services.spending.SpendingCreatorService;
import at.devp.myfinance.services.spending.SpendingDeletionService;
import at.devp.myfinance.services.spending.edit.SpendingEditManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SpendingsWriteController {
    private final SpendingCreatorService spendingCreatorService;
    private final SpendingDeletionService spendingDeletionService;
    private final SpendingEditManagerService spendingEditManagerService;

    @PostMapping("/spendings")
    public String createSpending(@ModelAttribute SpendingCreationDto spendingCreationDto){
        spendingCreatorService.createSpending(spendingCreationDto);
        return "redirect:/overview";
    }

    @GetMapping("/spendings/{id}/delete")
    public String deleteSpending(@PathVariable("id") Long id) {
        spendingDeletionService.deleteById(id);
        return "redirect:/overview";
    }

    @PostMapping("/spendings/edit")
    public String editSpending(@ModelAttribute SpendingEditDto spendingEditDto) {
        spendingEditManagerService.editSpending(spendingEditDto);
        return "redirect:/overview";
    }

}
