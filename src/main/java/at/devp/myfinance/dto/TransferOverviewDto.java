package at.devp.myfinance.dto;

import lombok.Data;

@Data
public class TransferOverviewDto {
  private Long id;
  private String description;
  private String from;
  private String to;
  private Double amount;
  private Double oldAmount;
  private boolean isChange;
}
