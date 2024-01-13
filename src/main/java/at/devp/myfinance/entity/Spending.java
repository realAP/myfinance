package at.devp.myfinance.entity;

import at.devp.myfinance.types.Category;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.transaction.Transactional.TxType.MANDATORY;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Spending {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String description;

  @Column
  private Category category;

  @Column
  private BigDecimal amount;

  @ManyToOne
  @JoinColumn(name = "rule_id")
  private Rule rule;

  @ManyToOne
  @JoinColumn(name = "transfer_id")
  private Transfer transfer;


  @Transactional(value = MANDATORY)
  public void setRuleAndUpdateStatus(final Rule rule) {
    final var oldRule = this.rule;
    if (oldRule != null) {
      oldRule.getSpendings().remove(this);
      oldRule.updateAmountAndChange();
    }
    rule.getSpendings().add(this);
    this.rule = rule;
    this.rule.updateAmountAndChange();
  }
}
