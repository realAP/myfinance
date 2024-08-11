package at.devp.myfinance.controller_fe.read;

import at.devp.myfinance.services.financeoverview.SpendingCategoryBlockDto;
import at.devp.myfinance.services.financeoverview.SpendingOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SpendingOverviewControllerFe {

    private final SpendingOverviewService spendingOverviewService;

    @GetMapping("/fe/overview")
    public List<SpendingCategoryBlockDto> getFinanceOverview() {
        return spendingOverviewService.createOverview();
    }
}
