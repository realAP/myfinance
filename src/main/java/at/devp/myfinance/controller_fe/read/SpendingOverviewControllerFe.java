package at.devp.myfinance.controller_fe.read;

import at.devp.myfinance.services.CategoryService;
import at.devp.myfinance.services.financeoverview.SpendingCategoryBlockDto;
import at.devp.myfinance.services.financeoverview.SpendingOverviewService;
import at.devp.myfinance.services.rule.RuleDropDownService;
import at.devp.myfinance.services.transfer.TransferDropDownService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpendingOverviewControllerFe {

  //private final CategoryService categoryService;
  //private final RuleDropDownService ruleDropDownService;
  private final SpendingOverviewService spendingOverviewService;
  //private final TransferDropDownService transferDropDownService;


  @GetMapping("/fe/overview")
  @CrossOrigin(origins = "http://localhost:4200")
  public List<SpendingCategoryBlockDto> getFinanceOverview() {

      // TODO: clean this when extending the creation controller
/*
    model.addAttribute("spendingCategoryBlockDtos", spendingCategoryBlockDtos);
    model.addAttribute("spendingCategoryBlockDto", new SpendingCategoryBlockDto());
    model.addAttribute("spendingRowDto", new SpendingRowDto());
*/
    //final var categoryDtos = categoryService.createCategories();
/*
    model.addAttribute("categoryDtos", categoryDtos);
    model.addAttribute("spendingCreationDto", new SpendingCreationDto());
*/

    //final var sumOfSpendings = spendingOverviewService.calculateSum();
    //model.addAttribute("sumOfSpendings", sumOfSpendings);


    //final var ruleDropDownDtos = ruleDropDownService.createRuleDropDownDto();
/*
    model.addAttribute("ruleDropDownDtos", ruleDropDownDtos);
    model.addAttribute("ruleDropDownDto", new RuleDropDownDto());
*/

    //final var transferDropDownDtos = transferDropDownService.createDropDownDtos();
/*
    model.addAttribute("transferDropDownDtos", transferDropDownDtos);
    model.addAttribute("transferDropDownDto", new TransferDropDownDto());
*/

    return spendingOverviewService.createOverview();
  }
}
