package at.devp.myfinance.services;

import at.devp.myfinance.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferDeletionService {

  private final TransferRepository transferRepository;

  @Transactional
  public void deleteById(final Long transferId) {
    transferRepository.deleteById(transferId);
  }
}
