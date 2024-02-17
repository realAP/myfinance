package at.devp.myfinance.services.financeoverview;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpendingRowDto {
  private Long id;
  private String description;
  private String ruleDescription;
  private String transferDescription;
  private BigDecimal amount;
  private String category;
}
