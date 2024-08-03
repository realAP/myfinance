package at.devp.myfinance.services;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CategoryServiceTest {

  private final CategoryService underTest = new CategoryService();

  @Test
  void whenGivenCategoryEnumsThenReturnAListOfAllCategoriesWithItsCorrespondingId() {

    final var result = underTest.createCategories();
    final var resultIterator = result.iterator();

    final var firstResult = resultIterator.next();
    assertThat(firstResult.getCategorie(), is("VERGNUEGEN"));
    assertThat(firstResult.getId(), is(0));

    final var secondResult = resultIterator.next();
    assertThat(secondResult.getCategorie(), is("SPORT"));
    assertThat(secondResult.getId(), is(1));

    final var thirdResult = resultIterator.next();
    assertThat(thirdResult.getCategorie(), is("BANK"));
    assertThat(thirdResult.getId(), is(2));

    final var fourthResult = resultIterator.next();
    assertThat(fourthResult.getCategorie(), is("INVESTITIONEN"));
    assertThat(fourthResult.getId(), is(3));
  }

}