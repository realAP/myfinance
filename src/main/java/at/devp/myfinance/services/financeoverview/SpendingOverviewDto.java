package at.devp.myfinance.services.financeoverview;

import at.devp.myfinance.types.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpendingOverviewDto {
  private Long id;
  private Category category;
  private String description;
  private BigDecimal amount;
  private String ruleDescription;
  private String transferDescription;
}
