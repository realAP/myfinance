package at.devp.myfinance.dto;

import lombok.Data;

@Data
public class TransferCreationDto {
  private String description;
  private String from;
  private String to;
}
