package at.devp.myfinance.dto;

import at.devp.myfinance.types.Category;
import lombok.Data;

@Data
public class SpendingCreationDto {
  private Long id;
  private Category category;
  private String description;
  private Double amount;
  private Long ruleId;
  private Long transferId;
}
