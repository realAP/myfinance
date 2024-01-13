package at.devp.myfinance.services.spending;

import at.devp.myfinance.dto.SpendingCreationDto;
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
  private final SpendingRepository spendingRepository;
  private final TransferEditService transferEditService;
  private final TransferRepository transferRepository;
  private final TransferUpdateService transferUpdateService;
  private final RuleEditService ruleEditService;

  @Transactional
  public void editSpending(final SpendingCreationDto spendingCreationDto) {
    final var spending = spendingRepository.findById(spendingCreationDto.getId()).orElseThrow(() -> new IllegalArgumentException("Spending with id " + spendingCreationDto.getId() + " not found"));

    spending.setDescription(spendingCreationDto.getDescription());
    spending.setCategory(spendingCreationDto.getCategory());

    if (spending.getAmount() != spendingCreationDto.getAmount()) {
      spending.setAmount(spendingCreationDto.getAmount());
      ruleUpdateService.updateStatus(spending.getRule());
      transferUpdateService.updateStatus(spending.getTransfer());
    }

    if (checkForRuleChange(spending, spendingCreationDto)) {
      final var selectedRule = ruleRepository.findById(spendingCreationDto.getRuleId()).orElseThrow(() -> new IllegalArgumentException("Rule with id " + spendingCreationDto.getRuleId() + " not found"));
      final var oldRule = spending.getRule();
      spending.setRule(selectedRule);
      ruleEditService.editRuleAndUpdate(oldRule, selectedRule, spending);
    }

    if (checkForTransferChange(spending, spendingCreationDto)) {
      final var oldTransfer = spending.getTransfer();
      final var selectedTransfer = transferRepository.findById(spendingCreationDto.getTransferId()).orElseThrow(() -> new IllegalArgumentException("Transfer with id " + spendingCreationDto.getTransferId() + " not found"));
      spending.setTransfer(selectedTransfer);
      transferEditService.editTransferAndUpdate(oldTransfer, selectedTransfer, spending);
    }

    spendingRepository.save(spending);
  }
  private boolean checkForRuleChange(Spending spending, SpendingCreationDto spendingCreationDto) {
    return !Objects.equals(spending.getRule().getId(), spendingCreationDto.getRuleId());
  }

  private boolean checkForTransferChange(Spending spending, SpendingCreationDto spendingCreationDto) {
    return !Objects.equals(spending.getTransfer().getId(), spendingCreationDto.getTransferId());
  }

  public SpendingCreationDto getSpendingCreationDtoById(final Long id) {
    return spendingRepository.findById(id).map(spending -> {
      SpendingCreationDto dto = new SpendingCreationDto();
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
