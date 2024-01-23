package at.devp.myfinance.controller.read;

import at.devp.myfinance.dto.RuleDropDownDto;
import at.devp.myfinance.dto.SpendingEditDto;
import at.devp.myfinance.dto.TransferDropDownDto;
import at.devp.myfinance.services.CategoryService;
import at.devp.myfinance.services.ruleservice.RuleService;
import at.devp.myfinance.services.spending.SpendingEditService;
import at.devp.myfinance.services.transfer.TransferDropDownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class EditSpendingController {
    private final SpendingEditService spendingEditService;
    private final CategoryService categoryService;
    private final RuleService ruleService;
    private final TransferDropDownService transferDropDownService;


    @GetMapping("spendings/{id}/edit")
    public String getEditSpendingPage(@PathVariable("id") Long id, Model model) {
        final var spendingEditDto = spendingEditService.getSpendingCreationDtoById(id);
        model.addAttribute("spendingEditDto", spendingEditDto);
        final var categoryDtos = categoryService.createCategories();
        model.addAttribute("categoryDtos", categoryDtos);

        final var ruleDropDownDtos = ruleService.createRuleDropDownDto();
        model.addAttribute("ruleDropDownDtos", ruleDropDownDtos);
        model.addAttribute("ruleDropDownDto", new RuleDropDownDto());

        final var transferDropDownDtos = transferDropDownService.createDropDownDtos();
        model.addAttribute("transferDropDownDtos", transferDropDownDtos);
        model.addAttribute("transferDropDownDto", new TransferDropDownDto());

        return "editspending";
    }

}
