package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Rule;
import at.devp.myfinance.entity.Spending;
import at.devp.myfinance.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpendingRepository extends JpaRepository<Spending, Long> {


  Spending findAllByAmountIsGreaterThan(final Double amount);

  @Query("select spending.rule from Spending spending " +
         "join spending.rule rule")
  Rule findRuleBySpendingId(final Long id);

  @Query("select spending.transfer from Spending spending " +
         "join spending.transfer transfer")
  Transfer findTransferBySpendingId(final Long id);

  @Query("select distinct(spending.rule.id) from Spending spending " +
         "join spending.rule rule")
  Long findRuleIdBySpendingId(final Long id);

  @Query("select distinct(spending.transfer.id) from Spending spending " +
         "join spending.transfer transfer")
  Long findTransferIdBySpendingId(final Long id);

  @Query("select spending from Spending spending " +
         "join spending.rule rule where rule.id = :ruleId ")
  List<Spending> findAllSpendingsByRuleId(@Param("ruleId") Long ruleId);

  @Query("select spending from Spending spending " +
         "join spending.transfer transfer where transfer.id = :transferId ")
  List<Spending> findAllSpendingsByTransferId(@Param("transferId") Long transferId);


}