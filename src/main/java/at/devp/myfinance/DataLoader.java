package at.devp.myfinance;

import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class DataLoader implements ApplicationRunner {

  private final SpendingRepository spendingRepository;

  private final TransferRepository transferRepository;

  private final RuleRepository ruleRepository;


  @Override
  @Transactional
  public void run(ApplicationArguments args) {

/*
    final var spending = new Spending();
    spending.setDescription("Netflix");
    spending.setAmount(10.00);
    spending.setCategory(Category.VERGNUEGEN);

    final var transfer = new Transfer();
    transfer.setDescription("Netflix to Bank1");
    transferRepository.save(transfer);


    final var rule = new Rule();
    rule.setDescription("main1");
    rule.setFrom("Einnahmen");
    rule.setTo("Main");
    rule.setAmount(10.0);
    ruleRepository.save(rule);


    spending.setRule(rule); // Change these to valid Rule objects in your actual test
    spending.setTransfer(transfer); // Change these to valid Transfer objects in your actual test

    // Save item
    spendingRepository.save(spending);
*/
  }
}
