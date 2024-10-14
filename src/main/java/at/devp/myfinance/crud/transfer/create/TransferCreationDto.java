package at.devp.myfinance.crud.transfer.create;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransferCreationDto {
    private String description;
    private LocalDate dateOfExecution;
    private Long fromBankId;
    private Long toBankId;
}
