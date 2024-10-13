package at.devp.myfinance.feature.financeOverview;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SpendingCategoryBlockDto {
  private String category;
  private List<SpendingRowDto> spendingRowDtos;
  private BigDecimal spendingSumPerCategory;
}
