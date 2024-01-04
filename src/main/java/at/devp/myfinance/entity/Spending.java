package at.devp.myfinance.entity;

import at.devp.myfinance.types.Category;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

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
  private Double amount;

  @ManyToOne
  @JoinColumn(name = "rule_id")
  @Setter(AccessLevel.NONE)
  private Rule rule;

  @ManyToOne
  @JoinColumn(name = "transfer_id")
  @Setter(AccessLevel.NONE)
  private Transfer transfer;


  @Transactional(value = MANDATORY)
  public void setTransferAndUpdateStatus(final Transfer transfer) {
    final var oldTransfer = this.transfer;
    if (oldTransfer != null) {
      oldTransfer.getSpendings().remove(this);
      oldTransfer.updateStatus();
    }
    transfer.getSpendings().add(this);
    this.transfer = transfer;
    this.transfer.updateStatus();
  }

  @Transactional(value = MANDATORY)
  public void setRuleAndUpdateStatus(final Rule rule) {
    final var oldRule = this.rule;
    if (oldRule != null) {
      oldRule.getSpendings().remove(this);
      oldRule.updateStatus();
    }
    rule.getSpendings().add(this);
    this.rule = rule;
    this.rule.updateStatus();
  }

  @Transactional(value = MANDATORY)
  public void removeRuleAndUpdateStatus() {
    final var oldRule = this.rule;
    if (oldRule != null) {
      oldRule.getSpendings().remove(this);
      oldRule.updateStatus();
    }
    this.rule = null;
  }

  @Transactional(value = MANDATORY)
  public void removeTransferAndUpdateStatus() {
    final var oldTransfer = this.transfer;
    if (oldTransfer != null) {
      oldTransfer.getSpendings().remove(this);
      oldTransfer.updateStatus();
    }
    this.transfer = null;
  }
}
