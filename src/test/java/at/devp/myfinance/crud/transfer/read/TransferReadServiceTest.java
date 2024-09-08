package at.devp.myfinance.crud.transfer.read;

import at.devp.myfinance.converter.Converter;
import at.devp.myfinance.entity.Transfer;
import at.devp.myfinance.repositories.TransferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferReadServiceTest {
    @Mock
    private TransferRepository transferRepository;

    @Mock
    private Converter converter;

    @InjectMocks
    private TransferReadService transferReadService;


    @Test
    void whenGetTransferDtosGivenUnsortedThenReturnItSortedByDescription() {
        final var transfer1 = new Transfer();
        final var transfer2 = new Transfer();
        final var transfer3 = new Transfer();

        final var transferDto1 = new TransferDto();
        transferDto1.setId(1L);
        transferDto1.setDescription("Zebra");
        transferDto1.setDateOfExecution(LocalDate.now());
        transferDto1.setAmount(BigDecimal.valueOf(100));
        transferDto1.setFromBankName("Bank A");
        transferDto1.setToBankName("Bank B");

        final var transferDto2 = new TransferDto();
        transferDto2.setId(2L);
        transferDto2.setDescription("Apple");
        transferDto2.setDateOfExecution(LocalDate.now());
        transferDto2.setAmount(BigDecimal.valueOf(200));
        transferDto2.setFromBankName("Bank C");
        transferDto2.setToBankName("Bank D");

        final var transferDto3 = new TransferDto();
        transferDto3.setId(3L);
        transferDto3.setDescription("Monkey");
        transferDto3.setDateOfExecution(LocalDate.now());
        transferDto3.setAmount(BigDecimal.valueOf(300));
        transferDto3.setFromBankName("Bank E");
        transferDto3.setToBankName("Bank F");

        when(transferRepository.findAll()).thenReturn(List.of(transfer2, transfer3, transfer1));
        when(converter.convert2TransferDto(transfer1)).thenReturn(transferDto1);
        when(converter.convert2TransferDto(transfer2)).thenReturn(transferDto2);
        when(converter.convert2TransferDto(transfer3)).thenReturn(transferDto3);

        List<TransferDto> result = transferReadService.getTransferDtos();

        assertEquals(3, result.size());
        assertEquals("Apple", result.get(0).getDescription());
        assertEquals("Monkey", result.get(1).getDescription());
        assertEquals("Zebra", result.get(2).getDescription());
    }

}