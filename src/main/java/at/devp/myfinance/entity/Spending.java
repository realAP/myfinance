package at.devp.myfinance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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
  private Double amount;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "rule_id")
  private Rule rule;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "transfer_id")
  private Transfer transfer;
}
