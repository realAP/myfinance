import {TestBed} from '@angular/core/testing';
import {of} from "rxjs";
import {MessageService} from "primeng/api";

import {SpendingOverviewPage} from "./spending-overview-page";
import {BackendService} from "../../service/backend/backend";
import {SpendingCategoryBlockDto} from "../../model/backend";

describe('SpendingOverviewPage', () => {
  let backendService: {
    getSpendingCategoryBlockDto: ReturnType<typeof vi.fn>;
    getSpendingSum: ReturnType<typeof vi.fn>;
    getDiffBetweenInAndOut: ReturnType<typeof vi.fn>;
  };
  let underTest: SpendingOverviewPage;

  beforeEach(() => {
    backendService = {
      getSpendingCategoryBlockDto: vi.fn().mockReturnValue(of([])),
      getSpendingSum: vi.fn().mockReturnValue(of(100)),
      getDiffBetweenInAndOut: vi.fn().mockReturnValue(of(20)),
    };
    TestBed.configureTestingModule({
      providers: [
        {provide: BackendService, useValue: backendService},
        {provide: MessageService, useValue: {add: vi.fn()}},
      ]
    });
    underTest = TestBed.runInInjectionContext(() => new SpendingOverviewPage());
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
