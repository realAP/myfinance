package at.devp.myfinance.feature.financeoverview;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SpendingOverviewController {

    private final SpendingOverviewService spendingOverviewService;

    @GetMapping("/fe/overview")
    public List<SpendingCategoryBlockDto> getFinanceOverview() {
        return spendingOverviewService.createOverview();
    }
}
