import {SpendingOverviewPageComponent} from "./spending-overview-page.component";
import {Mock, mock} from 'ts-jest-mocker';
import {BackendService} from "../../service/backend/backend.service";
import {MessageService} from "primeng/api";
import {of} from "rxjs";
import {SpendingCategoryBlockDto} from "../../model/backend";

describe('SpendingOverviewPageComponent', () => {
  let underTest: SpendingOverviewPageComponent;
  let backendService: Mock<BackendService>;
  let messageService: Mock<MessageService>;

  beforeEach(() => {
    backendService = mock(BackendService);
    messageService = mock(MessageService);
    underTest = new SpendingOverviewPageComponent(backendService, messageService);
  })

  it('onInit should take data from backend and store it into spendingCategoryBlockDtos', () => {
    backendService.getSpendingCategoryBlockDto.mockReturnValue(of([
      {
        category: 'BANK',
        spendingRowDtos: [],
        spendingSumPerCategory: 100,
        percentageToIncome: 10
      }
    ] as SpendingCategoryBlockDto[]));

    backendService.getSpendingSum.mockReturnValue(of(100));
    backendService.getDiffBetweenInAndOut.mockReturnValue(of(20));

    underTest.ngOnInit();

    expect(underTest.spendingCategoryBlockDtos.length).toBe(1);
    expect(underTest.spendingCategoryBlockDtos[0].category).toBe('BANK');
  });

});
