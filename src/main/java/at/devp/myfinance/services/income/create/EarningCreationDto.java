package at.devp.myfinance.services.income.create;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EarningCreationDto {
  private String description;
  private BigDecimal amount;
}
