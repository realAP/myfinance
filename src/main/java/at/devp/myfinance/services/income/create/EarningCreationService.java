package at.devp.myfinance.services.income.create;

import at.devp.myfinance.entity.Earning;
import at.devp.myfinance.repositories.EarningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EarningCreationService {
  private final EarningRepository earningRepository;

  public void createEarning(EarningCreationDto earningCreationDto) {
    final var earning = new Earning();
    earning.setAmount(earningCreationDto.getAmount());
    earning.setDescription(earningCreationDto.getDescription());

    earningRepository.save(earning);
  }
}
