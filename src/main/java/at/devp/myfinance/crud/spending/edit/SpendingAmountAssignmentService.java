package at.devp.myfinance.crud.spending.edit;

import at.devp.myfinance.dto.SpendingEditDto;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.services.rule.RuleUpdateService;
import at.devp.myfinance.services.transfer.TransferUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpendingAmountAssignmentService {

  private final RuleUpdateService ruleUpdateService;
  private final TransferUpdateService transferUpdateService;

  @Transactional
  public void setAmount(SpendingEditDto spendingEditDto, Spending spending) {
    spending.setAmount(spendingEditDto.getAmount());
    ruleUpdateService.updateStatus(spending.getRule());
    transferUpdateService.updateStatus(spending.getTransfer());
  }
}
