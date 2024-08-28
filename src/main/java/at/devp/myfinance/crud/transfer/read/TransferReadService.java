package at.devp.myfinance.crud.transfer.read;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.dto.TransferDto;
import at.devp.myfinance.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransferReadService {
    private final TransferRepository transferRepository;
    private final Converter converter;

    public List<TransferDto> getTransferDtos() {
        return transferRepository.findAll().stream().map(converter::createTransferDto).toList();
    }
}