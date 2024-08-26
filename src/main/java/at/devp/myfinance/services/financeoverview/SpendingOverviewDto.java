package at.devp.myfinance.services.financeoverview;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpendingOverviewDto {
  private Long id;
  private String categoryDescription;
  private String description;
  private BigDecimal amount;
  private String ruleDescription;
  private String transferDescription;
}
