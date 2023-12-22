package at.devp.myfinance.dto;

import lombok.Data;

@Data
public class SpendingDto {
  private Long id;
  private String description;
  private Double amount;

  private RuleDto ruleDto;
  private TransferDto transferDto;
}
