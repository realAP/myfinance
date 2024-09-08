import {Component} from '@angular/core';
import {CardModule} from "primeng/card";
import {BankCreationComponent} from "../../component/bank-creation/bank-creation.component";
import {CategoryCreationComponent} from "../../component/category-creation/category-creation.component";
import {RuleCreationComponent} from "../../component/rule-creation/rule-creation.component";
import {TransferFormComponent} from "../../component/transfer-form/transfer-form.component";
import {SpaceCreationComponent} from "../../component/space-creation/space-creation.component";
import {SpendingCreationComponent} from "../../component/spending-creation/spending-creation.component";
import {ConnectedSquaresComponent} from "../../component/connected-squares/connected-squares.component";
import {BackendService} from "../../service/backend.service";
import {MessageService} from "primeng/api";
import {TransferCreationDto} from "../../model/backend";

@Component({
  selector: 'app-backoffice-page',
  standalone: true,
  imports: [
    BankCreationComponent,
    CategoryCreationComponent,
    RuleCreationComponent,
    TransferFormComponent,
    SpaceCreationComponent,
    SpendingCreationComponent,
    CardModule,
    ConnectedSquaresComponent
  ],
  templateUrl: './backoffice-page.component.html',
  styleUrl: './backoffice-page.component.scss'
})
export class BackofficePageComponent {


  constructor(private backendService: BackendService,
              private messageService: MessageService) {
  }

  onTransferCreate(transferCreationDto: TransferCreationDto) {
    this.backendService.createTransfer(transferCreationDto).subscribe();
    this.messageService.add({severity: 'success', summary: 'created Transfer:', detail: transferCreationDto.description});
  }

}
