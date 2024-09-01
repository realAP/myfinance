package at.devp.myfinance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    // See: https://stackoverflow.com/a/32443004 why to use LocalDate
    @Column(nullable = false)
    private LocalDate dateOfExecution;

    @Column
    private String description;

    @OneToMany(mappedBy = "rule")
    private List<Spending> spendings = new ArrayList<>();

    @Column
    private String from;

    @Column
    private String to;

    @ManyToOne
    @JoinColumn(name = "from_space_id")
    private Space fromSpace;

    @ManyToOne
    @JoinColumn(name = "to_space_id")
    private Space toSpace;

    @Column
    private BigDecimal amount = new BigDecimal("0.00");

    @Column
    private BigDecimal oldAmount = new BigDecimal("0.00");

    @Column
    private boolean isChange;

    public BigDecimal calculateAmount() {
        return spendings.stream().map(Spending::getAmount).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean calculateHasChange() {
        return !Objects.equals(oldAmount, amount);
    }

    public void updateAmountAndChange() {
        this.amount = calculateAmount();
        this.isChange = calculateHasChange();
    }
}
