package at.devp.myfinance.repositories;

import at.devp.myfinance.entity.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpendingRepository extends JpaRepository<Spending, Long> {

    @Query("select spending.rule.id from Spending spending " +
            "where spending.id = :spendingId")
    Long findRuleIdBySpendingId(final Long spendingId);

    @Query("select spending.transfer.id from Spending spending " +
            "where spending.id = :spendingId")
    Long findTransferIdBySpendingId(final Long spendingId);

    @Query("select spending from Spending spending " +
            "join spending.rule rule where rule.id = :ruleId ")
    List<Spending> findAllSpendingsByRuleId(@Param("ruleId") Long ruleId);

}