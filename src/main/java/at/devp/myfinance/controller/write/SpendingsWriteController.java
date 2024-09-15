package at.devp.myfinance.controller.write;

import at.devp.myfinance.dto.SpendingEditDto;
import at.devp.myfinance.crud.spending.delete.SpendingDeletionService;
import at.devp.myfinance.crud.spending.edit.SpendingEditManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/spendings")
public class SpendingsWriteController {
    private final SpendingDeletionService spendingDeletionService;
    private final SpendingEditManagerService spendingEditManagerService;



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
