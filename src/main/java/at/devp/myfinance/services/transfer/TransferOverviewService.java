package at.devp.myfinance.services.transfer;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.dto.TransferOverviewDto;
import at.devp.myfinance.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferOverviewService {

  private final TransferRepository transferRepository;
  private final Converter converter;

  public List<TransferOverviewDto> createTransferOverview() {
    final var transfers = transferRepository.findAll();
    return converter.convert2TransferOverviewDtos(transfers);
  }
}
