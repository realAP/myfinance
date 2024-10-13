package at.devp.myfinance.crud.transfer.create;

import at.devp.myfinance.entity.Bank;
import at.devp.myfinance.repositories.BankRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import org.junit.jupiter.api.BeforeEach;
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
class TransferCreationServiceIntTest {

    @Autowired
    private TransferCreationService underTest;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private SpendingRepository spendingRepository;

    @Autowired
    private BankRepository bankRepository;

    private final Bank volksbank = new Bank();

    private final Bank n26 = new Bank();

    @BeforeEach
    void setUp() {
        volksbank.setName("Volksbank");
        n26.setName("N26");
        bankRepository.save(volksbank);
        bankRepository.save(n26);
    }


    @Test
    void whenCreateTransferGivenTransferCreationDtoThenReturnCreatedTransfer() {
        assertThat(transferRepository.findAll(), hasSize(0));

        final var transferCreationDto = new TransferCreationDto();
        transferCreationDto.setDescription("Spotify");
        transferCreationDto.setFromBankId(n26.getId());
        transferCreationDto.setToBankId(volksbank.getId());
        transferCreationDto.setDateOfExecution(LocalDate.of(2024, 8, 24));

        underTest.createTransfer(transferCreationDto);
        final var result = transferRepository.findAll();

        assertThat(result, hasSize(1));
        final var resultTransfer = result.get(0);
        assertThat(resultTransfer.getDescription(), is(transferCreationDto.getDescription()));
        assertThat(resultTransfer.getFromBank().getName(), is("N26"));
        assertThat(resultTransfer.getToBank().getName(), is("Volksbank"));
        assertThat(resultTransfer.getAmount(), is(new BigDecimal("0.00")));
        assertThat(resultTransfer.getOldAmount(), is(new BigDecimal("0.00")));
        assertThat(resultTransfer.isChange(), is(false));
        assertThat(resultTransfer.getDateOfExecution(), is(LocalDate.of(2024, 8, 24)));
        assertThat(spendingRepository.findAll(), hasSize(0));
    }

}