package at.devp.myfinance.feature.sumOfIncome;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fe/features/sumofincome")
public class SumOfIncomeController {

    private final SumOfIncomeService sumOfIncomeService;

    @GetMapping()
    public BigDecimal getSumOfIncome() {
        return sumOfIncomeService.getSum();
    }

}
