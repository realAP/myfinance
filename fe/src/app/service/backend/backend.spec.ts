import {TestBed} from '@angular/core/testing';
import {HttpClient} from "@angular/common/http";
import {of} from "rxjs";

import {BackendService} from './backend';
import {environment} from "../../../environments/environment";

describe('BackendService', () => {
  let httpClientMock: { get: ReturnType<typeof vi.fn> };
  let underTest: BackendService;

  beforeEach(() => {
    httpClientMock = {get: vi.fn().mockReturnValue(of([]))};
    TestBed.configureTestingModule({
      providers: [{provide: HttpClient, useValue: httpClientMock}]
    });
    underTest = TestBed.inject(BackendService);
  });

  it('should be created', () => {
    expect(underTest).toBeTruthy();
  });

  it('getSpendingOverviewDto should return data from HttpClient', () => {
    underTest.getSpendingCategoryBlockDto();

    expect(httpClientMock.get).toHaveBeenCalledWith(`${environment.apiUrl}/fe/overview`);
  })
});
