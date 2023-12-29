package at.devp.myfinance.dto;

import at.devp.myfinance.types.Category;
import lombok.Data;

@Data
public class SpendingDto {
  private Long Id;
  private Category category;
  private String description;
  private Double amount;
  private RuleDto ruleDto;
  private TransferDto transferDto;
}
