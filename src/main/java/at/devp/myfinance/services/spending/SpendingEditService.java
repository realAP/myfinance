package at.devp.myfinance.services.spending;

import at.devp.myfinance.dto.SpendingEditDto;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import at.devp.myfinance.services.rule.RuleEditService;
import at.devp.myfinance.services.rule.RuleUpdateService;
import at.devp.myfinance.services.transfer.TransferEditService;
import at.devp.myfinance.services.transfer.TransferUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SpendingEditService {
  private final RuleRepository ruleRepository;
  private final RuleUpdateService ruleUpdateService;
  private final RuleEditService ruleEditService;
  private final SpendingRepository spendingRepository;
  private final TransferEditService transferEditService;
  private final TransferUpdateService transferUpdateService;
  private final TransferRepository transferRepository;

  @Transactional
  public void editSpending(final SpendingEditDto spendingEditDto) {
    final var spending = spendingRepository.findById(spendingEditDto.getId()).orElseThrow(() -> new IllegalArgumentException("Spending with id " + spendingEditDto.getId() + " not found"));

    spending.setDescription(spendingEditDto.getDescription());
    spending.setCategory(spendingEditDto.getCategory());

    if (spending.getAmount() != spendingEditDto.getAmount()) {
      spending.setAmount(spendingEditDto.getAmount());
      ruleUpdateService.updateStatus(spending.getRule());
      transferUpdateService.updateStatus(spending.getTransfer());
    }

    if (checkForRuleChange(spending, spendingEditDto)) {
      final var selectedRule = ruleRepository.findById(spendingEditDto.getRuleId()).orElseThrow(() -> new IllegalArgumentException("Rule with id " + spendingEditDto.getRuleId() + " not found"));
      final var oldRule = spending.getRule();
      spending.setRule(selectedRule);
      ruleEditService.editRuleAndUpdate(oldRule, selectedRule, spending);
    }

    if (checkForTransferChange(spending, spendingEditDto)) {
      final var oldTransfer = spending.getTransfer();
      final var selectedTransfer = transferRepository.findById(spendingEditDto.getTransferId()).orElseThrow(() -> new IllegalArgumentException("Transfer with id " + spendingEditDto.getTransferId() + " not found"));
      spending.setTransfer(selectedTransfer);
      transferEditService.editTransferAndUpdate(oldTransfer, selectedTransfer, spending);
    }

    spendingRepository.save(spending);
  }

  private boolean checkForRuleChange(final Spending spending, final SpendingEditDto spendingEditDto) {
    return !Objects.equals(spending.getRule().getId(), spendingEditDto.getRuleId());
  }

  private boolean checkForTransferChange(final Spending spending, final SpendingEditDto spendingEditDto) {
    return !Objects.equals(spending.getTransfer().getId(), spendingEditDto.getTransferId());
  }

  public SpendingEditDto getSpendingEditDtoById(final Long id) {
    return spendingRepository.findById(id).map(spending -> {
      final var dto = new SpendingEditDto();
      dto.setId(spending.getId());
      dto.setAmount(spending.getAmount());
      dto.setDescription(spending.getDescription());
      dto.setCategory(spending.getCategory());
      dto.setRuleId(spendingRepository.findRuleIdBySpendingId(spending.getId()));
      dto.setTransferId(spendingRepository.findTransferIdBySpendingId(spending.getId()));
      return dto;
    }).orElseThrow(() -> new IllegalArgumentException("Spending with id " + id + " not found"));
  }

}
