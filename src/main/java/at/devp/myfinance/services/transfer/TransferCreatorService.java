package at.devp.myfinance.services.transfer;

import at.devp.myfinance.dto.TransferCreationDto;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferCreatorService {
  private final TransferRepository transferRepository;

  public void createTransfer(final TransferCreationDto transferCreationDto) {
    final var transfer = new Transfer();
    transfer.setTo(transferCreationDto.getTo());
    transfer.setFrom(transferCreationDto.getFrom());
    transfer.setDescription(transferCreationDto.getDescription());
    transfer.setDateOfExecution(transferCreationDto.getDateOfExecution());

    transferRepository.save(transfer);
  }
}
