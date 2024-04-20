package at.devp.myfinance.services.income;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class IncomeDto {
  private List<EarningDto> earningDtos = new ArrayList<>();
  private BigDecimal sumOfEarnings;
}
