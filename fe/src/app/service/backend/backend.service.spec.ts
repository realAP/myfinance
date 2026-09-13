import {BackendService} from './backend.service';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {of} from "rxjs";

describe('BackendService', () => {
  let httpClientMock: { get: ReturnType<typeof vi.fn> };
  let underTest: BackendService;

  beforeEach(() => {
    httpClientMock = {get: vi.fn().mockReturnValue(of([]))};
    underTest = new BackendService(httpClientMock as unknown as HttpClient);
  });

  it('should be created', () => {
    expect(underTest).toBeTruthy();
  });

  it('getSpendingOverviewDto should return data from HttpClient', () => {
    underTest.getSpendingCategoryBlockDto();

    expect(httpClientMock.get).toHaveBeenCalledWith(`${environment.apiUrl}/fe/overview`);
  })
});
