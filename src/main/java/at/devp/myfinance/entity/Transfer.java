package at.devp.myfinance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Transfer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String description;

  @OneToMany(mappedBy = "transfer")
  private List<Spending> spendings = new ArrayList<>();

  @Column
  private String from;

  @Column
  private String to;

  @Column
  private Double amount = 0.0;

  @Column
  private Double oldAmount = 0.0;

  @Column
  private boolean isChange;

  public void addSpendingAndUpdate(final Spending spending) {
    spendings.add(spending);
    updateAmountAndChange();
  }

  public void removeSpendingAndUpdate(final Spending spending) {
    spendings.remove(spending);
    updateAmountAndChange();
  }

  public Double calculateAmount() {
    return spendings.stream().map(Spending::getAmount).filter(Objects::nonNull).mapToDouble(Double::doubleValue).sum();
  }

  public void updateAmount() {
    this.amount = calculateAmount();
  }

  public void updateOldAmount() {
    this.oldAmount = calculateAmount();
  }

  public void updateChange() {
    this.isChange = calculateHasChange();
  }

  public boolean calculateHasChange() {
    return !Objects.equals(oldAmount, amount);
  }

  public void updateAmountAndChange() {
    this.amount = calculateAmount();
    this.isChange = calculateHasChange();
  }

}
