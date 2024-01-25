package at.devp.myfinance.controller.write;

import at.devp.myfinance.services.rule.RuleChangeService;
import at.devp.myfinance.services.transfer.TransferChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/confirmchange")
public class ConfirmChangeController {
    private final TransferChangeService transferChangeService;
    private final RuleChangeService ruleChangeService;

    @PostMapping("/transfer/{id}")
    public String confirmChangeTransfer(@PathVariable("id") Long id) {
        transferChangeService.confirmAmountChangeForTransfer(id);
        return "redirect:/transfers";
    }

    @PostMapping("/rule/{id}")
    public String confirmChangeRule(@PathVariable("id") Long id) {
        ruleChangeService.confirmAmountChangeForRule(id);
        return "redirect:/rules";
    }
}
