package at.devp.myfinance.services.income;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EarningDto {
  private Long id;
  private BigDecimal amount;
  private String description;
}
