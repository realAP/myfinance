package at.devp.myfinance.services.transfer;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.dto.TransferDropDownDto;
import at.devp.myfinance.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferDropDownService {

  private final TransferRepository transferRepository;
  private final Converter converter;

  public List<TransferDropDownDto> createDropDownDtos() {
    final var transfers = transferRepository.findAll();
    return converter.convert2TransferDropDownDtos(transfers);
  }


}
