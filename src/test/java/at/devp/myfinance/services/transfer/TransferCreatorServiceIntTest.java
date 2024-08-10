package at.devp.myfinance.services.transfer;

import at.devp.myfinance.dto.TransferCreationDto;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class TransferCreatorServiceIntTest {

  @Autowired
  private TransferCreatorService underTest;

  @Autowired
  private TransferRepository transferRepository;

  @Autowired
  private SpendingRepository spendingRepository;


  @Test
  void whenCreateTransferGivenTransferCreationDtoThenReturnCreatedTransfer() {
    assertThat(transferRepository.findAll(), hasSize(0));

    final var transferCreationDto = new TransferCreationDto();
    transferCreationDto.setDescription("Spotify");
    transferCreationDto.setFrom("Main");
    transferCreationDto.setTo("Volksbank");
    transferCreationDto.setDateOfExecution(LocalDate.of(2024, 8, 24));

    underTest.createTransfer(transferCreationDto);
    final var result = transferRepository.findAll();

    assertThat(result, hasSize(1));
    final var resultTransfer = result.get(0);
    assertThat(resultTransfer.getDescription(), is(transferCreationDto.getDescription()));
    assertThat(resultTransfer.getFrom(), is(transferCreationDto.getFrom()));
    assertThat(resultTransfer.getTo(), is(transferCreationDto.getTo()));
    assertThat(resultTransfer.getAmount(), is(new BigDecimal("0.00")));
    assertThat(resultTransfer.getOldAmount(), is(new BigDecimal("0.00")));
    assertThat(resultTransfer.isChange(), is(false));
    assertThat(resultTransfer.getDateOfExecution(), is(LocalDate.of(2024, 8, 24)));
    assertThat(spendingRepository.findAll(), hasSize(0));
  }

}