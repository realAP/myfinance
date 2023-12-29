package at.devp.myfinance.controller.write;

import at.devp.myfinance.dto.SpendingDto;
import at.devp.myfinance.services.createspending.SpendingCreatorSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateSpendingController {

  private final SpendingCreatorSerivce spendingCreatorSerivce;

  @PostMapping("api/v1/write/spending")
  public SpendingDto createSpending(@RequestBody final SpendingDto spendingDto) {
    return spendingCreatorSerivce.createSpending(spendingDto);
  }

}
