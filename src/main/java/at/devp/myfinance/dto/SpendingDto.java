package at.devp.myfinance.dto;

import at.devp.myfinance.types.Category;
import lombok.Data;

@Data
public class SpendingDto {
  private String description;
  private Double amount;
  private Category category;
  private RuleDto ruleDto;
  private TransferDto transferDto;
}
