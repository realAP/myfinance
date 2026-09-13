import {SpendingOverviewPageComponent} from "./spending-overview-page.component";
import {BackendService} from "../../service/backend/backend.service";
import {MessageService} from "primeng/api";
import {of} from "rxjs";
import {SpendingCategoryBlockDto} from "../../model/backend";

describe('SpendingOverviewPageComponent', () => {
  let backendService: {
    getSpendingCategoryBlockDto: ReturnType<typeof vi.fn>;
    getSpendingSum: ReturnType<typeof vi.fn>;
    getDiffBetweenInAndOut: ReturnType<typeof vi.fn>;
  };
  let underTest: SpendingOverviewPageComponent;

  beforeEach(() => {
    backendService = {
      getSpendingCategoryBlockDto: vi.fn().mockReturnValue(of([])),
      getSpendingSum: vi.fn().mockReturnValue(of(100)),
      getDiffBetweenInAndOut: vi.fn().mockReturnValue(of(20)),
    };
    underTest = new SpendingOverviewPageComponent(
      backendService as unknown as BackendService,
      {add: vi.fn()} as unknown as MessageService
    );
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

    underTest.ngOnInit();

    expect(underTest.spendingCategoryBlockDtos.length).toBe(1);
    expect(underTest.spendingCategoryBlockDtos[0].category).toBe('BANK');
  });
});
