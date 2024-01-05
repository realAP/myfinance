package at.devp.myfinance.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferOverviewDto {
  private Long id;
  private String description;
  private String from;
  private String to;
  private BigDecimal amount;
  private BigDecimal oldAmount;
  private boolean isChange;
}
