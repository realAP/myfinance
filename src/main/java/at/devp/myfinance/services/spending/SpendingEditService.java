package at.devp.myfinance.services.spending;

import at.devp.myfinance.dto.SpendingCreationDto;
import at.devp.myfinance.dto.SpendingEditDto;
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
  public void editSpending(final SpendingEditDto spendingEditDto) {
    final var spending = spendingRepository.findById(spendingEditDto.getId()).orElseThrow(() -> new IllegalArgumentException("Spending with id " + spendingEditDto.getId() + " not found"));

    if (spending.getAmount() != spendingEditDto.getAmount()) {
      spending.setAmount(spendingEditDto.getAmount());
      ruleUpdateService.updateStatus(spending.getRule());
      transferUpdateService.updateStatus(spending.getTransfer());
    }

    spending.setDescription(spendingEditDto.getDescription());
    spending.setCategory(spendingEditDto.getCategory());


    if (checkForRuleChange(spending, spendingEditDto)) {
      final var selectedRule = ruleRepository.findById(spendingEditDto.getRuleId()).orElseThrow(() -> new IllegalArgumentException("Rule with id " + spendingEditDto.getRuleId() + " not found"));
      final var oldRule = spending.getRule();
      spending.setRule(selectedRule);
      ruleUpdateService.editRuleAndUpdate(oldRule, selectedRule, spending);
    }

    if (checkForTransferChange(spending, spendingEditDto)) {
      final var oldTransfer = spending.getTransfer();
      final var selectedTransfer = transferRepository.findById(spendingEditDto.getTransferId()).orElseThrow(() -> new IllegalArgumentException("Transfer with id " + spendingEditDto.getTransferId() + " not found"));
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

  private boolean checkForRuleChange(final Spending spending, final SpendingEditDto spendingEditDto) {
    return !Objects.equals(spending.getRule().getId(), spendingEditDto.getRuleId());
  }

  private boolean checkForTransferChange(final Spending spending, final SpendingEditDto spendingEditDto) {
    return !Objects.equals(spending.getTransfer().getId(), spendingEditDto.getTransferId());
  }

  private void updateTransfer(Spending spending, SpendingCreationDto spendingCreationDto) {
    final var newTransfer = transferRepository.findById(spendingCreationDto.getTransferId()).orElseThrow(() -> new IllegalArgumentException("Transfer with id " + spendingCreationDto.getTransferId() + " not found"));
    spending.setTransferAndUpdateStatus(newTransfer);
    //final var oldTransfer = spending.getTransfer();
    //oldTransfer.updateStatus();
    //newTransfer.updateStatus();
  }

  public SpendingEditDto getSpendingCreationDtoById(final Long id) {

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
