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
  @Setter(AccessLevel.NONE)
  private Rule rule;

  @ManyToOne
  @JoinColumn(name = "transfer_id")
  @Setter(AccessLevel.NONE)
  private Transfer transfer;

  public void setTransfer(final Transfer transfer) {
    this.transfer = transfer;
  }

  @Transactional(value = MANDATORY)
  public void setTransferAndUpdateStatus(final Transfer transfer) {
    final var oldTransfer = this.transfer;
    if (oldTransfer != null) {
      oldTransfer.getSpendings().remove(this);
      oldTransfer.updateAmountAndChange();
    }
    transfer.getSpendings().add(this);
    this.transfer = transfer;
    this.transfer.updateAmountAndChange();
  }

  public void setRule(final Rule rule) {
    this.rule = rule;
  }

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

  @Transactional(value = MANDATORY)
  public void removeRuleAndUpdateStatus() {
    final var oldRule = this.rule;
    if (oldRule != null) {
      oldRule.getSpendings().remove(this);
      oldRule.updateAmountAndChange();
    }
    this.rule = null;
  }

  @Transactional(value = MANDATORY)
  public void removeTransferAndUpdateStatus() {
    final var oldTransfer = this.transfer;
    if (oldTransfer != null) {
      oldTransfer.getSpendings().remove(this);
      oldTransfer.updateAmountAndChange();
    }
    this.transfer = null;
  }
}
