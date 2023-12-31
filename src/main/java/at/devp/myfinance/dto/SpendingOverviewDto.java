package at.devp.myfinance.dto;

import at.devp.myfinance.types.Category;
import lombok.Data;

@Data
public class SpendingOverviewDto {
  private Long id;
  private Category category;
  private String description;
  private Double amount;
  private String ruleDescription;
  private String transferDescription;
}
