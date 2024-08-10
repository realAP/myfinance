import {SpendingOverviewPageComponent} from "./spending-overview-page.component";
import {Mock, mock} from 'ts-jest-mocker';
import {BackendService} from "../../service/backend.service";
import {of} from "rxjs";
import {Category, SpendingCategoryBlockDto} from "../../model/backend";

describe('SpendingOverviewPageComponent', () => {
  let underTest: SpendingOverviewPageComponent;
  let backendService: Mock<BackendService>;

  beforeEach(() => {
    backendService = mock(BackendService);
    underTest = new SpendingOverviewPageComponent(backendService);
  })

  it('onInit should take data from backend and store it into spendingCategoryBlockDtos', () => {
    backendService.getSpendingCategoryBlockDto.mockReturnValue(of([
      {
        category: Category.BANK,
        spendingRowDtos: [{ }],
        spendingSumPerCategory: 100
      }
    ] as SpendingCategoryBlockDto[]));

    underTest.ngOnInit();

    expect(underTest.spendingCategoryBlockDtos.length).toBe(1);
    expect(underTest.spendingCategoryBlockDtos[0].category).toBe(Category.BANK);
  });

});
