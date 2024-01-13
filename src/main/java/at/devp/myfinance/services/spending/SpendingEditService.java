package at.devp.myfinance.services.spending;

import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import at.devp.myfinance.services.ruleservice.RuleUpdateService;
import at.devp.myfinance.services.transfer.TransferEditService;
import at.devp.myfinance.services.transfer.TransferUpdateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SpendingEditService {
  private final SpendingRepository spendingRepository;
  private final RuleRepository ruleRepository;
  private final TransferRepository transferRepository;
  private final RuleUpdateService ruleUpdateService;
  private final TransferUpdateService transferUpdateService;
  private final TransferEditService transferEditService;

  @Transactional
  public void editSpending(final SpendingCreationDto spendingCreationDto) {
    final var spending = spendingRepository.findById(spendingCreationDto.getId()).orElseThrow(() -> new IllegalArgumentException("Spending with id " + spendingCreationDto.getId() + " not found"));

    if (spending.getAmount() != spendingCreationDto.getAmount()) {
      spending.setAmount(spendingCreationDto.getAmount());
      ruleUpdateService.updateStatus(spending.getRule());
      transferUpdateService.updateStatus(spending.getTransfer());
    }

    spending.setDescription(spendingCreationDto.getDescription());
    spending.setCategory(spendingCreationDto.getCategory());


    if (checkForRuleChange(spending, spendingCreationDto)) {
      final var selectedRule = ruleRepository.findById(spendingCreationDto.getRuleId()).orElseThrow(() -> new IllegalArgumentException("Rule with id " + spendingCreationDto.getRuleId() + " not found"));
      final var oldRule = spending.getRule();
      spending.setRule(selectedRule);
      ruleUpdateService.editRuleAndUpdate(oldRule, selectedRule, spending);
    }

    if (checkForTransferChange(spending, spendingCreationDto)) {
      final var oldTransfer = spending.getTransfer();
      final var selectedTransfer = transferRepository.findById(spendingCreationDto.getTransferId()).orElseThrow(() -> new IllegalArgumentException("Transfer with id " + spendingCreationDto.getTransferId() + " not found"));
      spending.setTransfer(selectedTransfer);
      transferEditService.editTransferAndUpdate(oldTransfer, spending);
    }

    spendingRepository.save(spending);
  }

  @Transactional
  public void editSpending2(final SpendingCreationDto spendingCreationDto) {
    final var spending = spendingRepository.findById(spendingCreationDto.getId()).orElseThrow(() -> new IllegalArgumentException("Spending with id " + spendingCreationDto.getId() + " not found"));
    spending.setAmount(spendingCreationDto.getAmount());
    spending.setDescription(spendingCreationDto.getDescription());
    spending.setCategory(spendingCreationDto.getCategory());

    final var selectedRule = ruleRepository.findById(spendingCreationDto.getRuleId()).orElseThrow(() -> new IllegalArgumentException("Rule with id " + spendingCreationDto.getRuleId() + " not found"));
    spending.setRule(selectedRule);

    final var selectedTransfer = transferRepository.findById(spendingCreationDto.getTransferId()).orElseThrow(() -> new IllegalArgumentException("Transfer with id " + spendingCreationDto.getTransferId() + " not found"));
    spending.setTransfer(selectedTransfer);

    spendingRepository.save(spending);
    spendingRepository.flush();

    ruleUpdateService.updateAll();
    transferUpdateService.updateAll();

  }

  private Spending updateSpendingAttributes(SpendingCreationDto spendingCreationDto) {
    final var spending = spendingRepository.findById(spendingCreationDto.getId()).orElseThrow(() -> new IllegalArgumentException("Spending with id " + spendingCreationDto.getId() + " not found"));
    spending.setAmount(spendingCreationDto.getAmount());
    spending.setDescription(spendingCreationDto.getDescription());
    spending.setCategory(spendingCreationDto.getCategory());
    return spending;
  }

  private boolean checkForRuleChange(Spending spending, SpendingCreationDto spendingCreationDto) {
    return !Objects.equals(spending.getRule().getId(), spendingCreationDto.getRuleId());
  }

  private void updateRule(Spending spending, SpendingCreationDto spendingCreationDto) {
    final var newRule = ruleRepository.findById(spendingCreationDto.getRuleId()).orElseThrow(() -> new IllegalArgumentException("Rule with id " + spendingCreationDto.getRuleId() + " not found"));
    spending.setRuleAndUpdateStatus(newRule);
    //oldRule.updateStatus();
    //newRule.updateStatus();
  }

  private boolean checkForTransferChange(Spending spending, SpendingCreationDto spendingCreationDto) {
    return !Objects.equals(spending.getTransfer().getId(), spendingCreationDto.getTransferId());
  }

  private void updateTransfer(Spending spending, SpendingCreationDto spendingCreationDto) {
    final var newTransfer = transferRepository.findById(spendingCreationDto.getTransferId()).orElseThrow(() -> new IllegalArgumentException("Transfer with id " + spendingCreationDto.getTransferId() + " not found"));
    spending.setTransferAndUpdateStatus(newTransfer);
    //final var oldTransfer = spending.getTransfer();
    //oldTransfer.updateStatus();
    //newTransfer.updateStatus();
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
