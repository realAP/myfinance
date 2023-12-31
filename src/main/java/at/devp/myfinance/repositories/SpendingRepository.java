package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpendingRepository extends JpaRepository<Spending, Long> {


  Spending findAllByAmountIsGreaterThan(final Double amount);

  @Query("select spending.rule from Spending spending " +
         "join spending.rule rule")
  Rule findRuleBySpendingId(final Long id);

  @Query("select distinct(spending.rule.id) from Spending spending " +
         "join spending.rule rule")
  Long findRuleIdBySpendingId(final Long id);
  @Query("select distinct(spending.transfer.id) from Spending spending " +
         "join spending.transfer transfer")
  Long findTransferIdBySpendingId(final Long id);

}