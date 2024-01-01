package at.devp.myfinance.createspending;

import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.services.spending.createspending.SpendingCreatorService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpendingCreatorServiceTest {

  @Autowired
  private SpendingRepository spendingRepository;

  @Autowired
  private RuleRepository ruleRepository;

  @Autowired
  private SpendingCreatorService underTest;


  @Test
  @Transactional
  void createSpending() {


    /*final var spendingDto = new SpendingOverviewDto();
    spendingDto.setAmount(10D);
    spendingDto.setDescription("Spotify");

    final var ruleDto = new RuleDto();

    ruleDto.setDescription("entertainment");

    spendingDto.setRuleDropDownDto(ruleDto);



    final var transferDto = new TransferDto();
    transferDto.setDescription("Spotify / Entertainment");

    spendingDto.setTransferDto(transferDto);


    final var result = underTest.createSpending(spendingDto);

    //System.out.println("result: " + result.toString());


    final var rules = ruleRepository.findAll();

    System.out.println(rules.get(0).getSpendings());*/

  }

}