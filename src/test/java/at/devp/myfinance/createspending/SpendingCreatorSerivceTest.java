package at.devp.myfinance.createspending;

import at.devp.myfinance.dto.RuleDto;
import at.devp.myfinance.dto.SpendingDto;
import at.devp.myfinance.dto.TransferDto;
import at.devp.myfinance.repositories.RuleRepository;
import at.devp.myfinance.repositories.SpendingRepository;
import at.devp.myfinance.services.createspending.SpendingCreatorSerivce;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpendingCreatorSerivceTest {

  @Autowired
  private SpendingRepository spendingRepository;

  @Autowired
  private RuleRepository ruleRepository;

  @Autowired
  private SpendingCreatorSerivce underTest;


  @Test
  @Transactional
  void createSpending() {


    final var spendingDto = new SpendingDto();
    spendingDto.setAmount(10D);
    spendingDto.setDescription("Spotify");

    final var ruleDto = new RuleDto();

    ruleDto.setDescription("entertainment");

    spendingDto.setRuleDto(ruleDto);



    final var transferDto = new TransferDto();
    transferDto.setDescription("Spotify / Entertainment");

    spendingDto.setTransferDto(transferDto);


    final var result = underTest.createSpending(spendingDto);

    //System.out.println("result: " + result.toString());


    final var rules = ruleRepository.findAll();

    System.out.println(rules.get(0).getSpendings());

  }

}