import { BackendService } from './backend.service';
import {mock, Mock} from "ts-jest-mocker";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

describe('BackendServiceTsService', () => {
  let httpClientMock: Mock<HttpClient>;
  let underTest: BackendService;

  beforeEach(() => {
    httpClientMock = mock(HttpClient);
    underTest = new BackendService(httpClientMock);
  });

  it('should be created', () => {
    expect(underTest).toBeTruthy();
  });

  it('getSpendingOverviewDto should return data from HttpClient', () => {
    underTest.getSpendingCategoryBlockDto();

    expect(httpClientMock.get).toHaveBeenCalledWith(`${environment.apiUrl}/fe/overview`);
  })
});
