package at.devp.myfinance.dto;

import lombok.Data;

@Data
public class RuleCreationDto {
  private String description;
  private String from;
  private String to;
}