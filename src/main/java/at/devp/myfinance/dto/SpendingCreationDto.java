package at.devp.myfinance.dto;

import at.devp.myfinance.types.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpendingCreationDto {
  private Long id;
  private Category category;
  private String description;
  private BigDecimal amount;
  private Long ruleId;
  private Long transferId;
}
