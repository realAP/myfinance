package at.devp.myfinance.dto;

import lombok.Data;

@Data
public class RuleOverviewDto {
  private Long id;
  private String description;
  private String from;
  private String to;
  private Double amount;
  private Double oldAmount;
  private boolean isChange;
}