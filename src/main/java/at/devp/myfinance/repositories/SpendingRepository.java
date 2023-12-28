package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Spending;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingRepository extends JpaRepository<Spending, Long> {


  Spending findAllByAmountIsGreaterThan(final Double amount);

}