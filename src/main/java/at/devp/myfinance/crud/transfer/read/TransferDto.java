package at.devp.myfinance.crud.transfer.read;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransferDto {
  private Long id;
  private LocalDate dateOfExecution;
  private String description;
  private String fromBankName;
  private String toBankName;
  private BigDecimal amount;
  private BigDecimal oldAmount;
  private Boolean isChange;
}
