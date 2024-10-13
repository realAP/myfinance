package at.devp.myfinance.crud.spending.edit;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpendingEditDto {
  private Long id;
  private Long categoryId;
  private String description;
  private BigDecimal amount;
  private Long ruleId;
  private Long transferId;
}
