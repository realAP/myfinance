package at.devp.myfinance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    // See: https://stackoverflow.com/a/32443004 why to use LocalDate
    @Column(nullable = false)
    private LocalDate dateOfExecution;

    @Column
    private String description;

    @OneToMany(mappedBy = "transfer")
    private List<Spending> spendings = new ArrayList<>();

    @Column
    private String from;

    @Column
    private String to;

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
