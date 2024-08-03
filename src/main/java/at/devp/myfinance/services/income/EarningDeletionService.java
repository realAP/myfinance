package at.devp.myfinance.services.income;

import at.devp.myfinance.repositories.EarningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EarningDeletionService {
  private final EarningRepository earningRepository;

  public void deleteById(Long id) {
    earningRepository.deleteById(id);
  }
}
