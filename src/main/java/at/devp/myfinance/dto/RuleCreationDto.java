package at.devp.myfinance.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RuleCreationDto {
  private String description;
  private LocalDate dateOfExecution;
  private String from;
  private String to;
}