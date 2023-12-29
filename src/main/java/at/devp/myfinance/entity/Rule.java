package at.devp.myfinance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
  private Double amount;

  @Column
  private Double oldAmount;

  @Column
  private boolean isChange;

  public Double calculateAmount() {
    return spendings.stream().mapToDouble(Spending::getAmount).sum();
  }

  public void calculateHasChange() {
    this.isChange = !Objects.equals(oldAmount, amount);
  }
}
