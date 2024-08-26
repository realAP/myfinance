package at.devp.myfinance.services.financeoverview;

import at.devp.myfinance.types.CategoryEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpendingOverviewDto {
  private Long id;
  private CategoryEnum category;
  private String description;
  private BigDecimal amount;
  private String ruleDescription;
  private String transferDescription;
}
