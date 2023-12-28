package at.devp.myfinance.controller.read;

import at.devp.myfinance.dto.SpendingDto;
import at.devp.myfinance.services.financeoverview.FinanceOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FinanceOverviewController {

  private final FinanceOverviewService financeOverviewService;


  @GetMapping("/api/v1/financeoverview")
  public List<SpendingDto> getfinanceOverview() {
    return financeOverviewService.createOverview();
  }

}
