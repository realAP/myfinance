package at.devp.myfinance.crud.income.read;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncomeDto {
  private Long id;
  private BigDecimal amount;
  private String description;
}
