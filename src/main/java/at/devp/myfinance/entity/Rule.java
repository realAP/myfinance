package at.devp.myfinance.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String description;

  @OneToMany(mappedBy = "rule")
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

  public Double calculateAmount() {
    return spendings.stream().map(Spending::getAmount).filter(Objects::nonNull).mapToDouble(Double::doubleValue).sum();
  }

  public boolean calculateHasChange() {
    return !Objects.equals(oldAmount, amount);
  }

  @Transactional(value = MANDATORY)
  public void updateStatus() {
    this.amount = calculateAmount();
    this.isChange = calculateHasChange();
  }
}
