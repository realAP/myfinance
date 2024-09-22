import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Button} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputGroupAddonModule} from "primeng/inputgroupaddon";
import {InputGroupModule} from "primeng/inputgroup";
import {InputNumberModule} from "primeng/inputnumber";
import {InputTextModule} from "primeng/inputtext";
import {CalendarModule} from "primeng/calendar";
import {FormsModule} from "@angular/forms";
import {BackendService} from "../../service/backend.service";
import {RuleCreationDto, SpaceDto} from "../../model/backend";
import {MessageService} from "primeng/api";

export interface RuleFormDto {
  ruleCreationDto: RuleCreationDto;
  isPreFilled: boolean;
}

@Component({
  selector: 'app-rule-form',
  standalone: true,
  imports: [
    Button,
    DropdownModule,
    FloatLabelModule,
    InputGroupAddonModule,
    InputGroupModule,
    InputNumberModule,
    InputTextModule,
    CalendarModule,
    FormsModule
  ],
  templateUrl: './rule-form.component.html',
  styleUrl: './rule-form.component.scss'
})
export class RuleFormComponent implements OnInit {
  date: any;
  spaces: SpaceDto[] = [];
  selectedFromSpace: SpaceDto = {} as SpaceDto;
  selectedTargetSpace: SpaceDto = {} as SpaceDto;
  name: string = "";

  @Input() preFilledRuleFormDto?: RuleFormDto;
  @Output() formSubmit = new EventEmitter<RuleCreationDto>

  constructor(private backendService: BackendService,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.backendService.getSpaces().subscribe(spaceDtos => {
      this.spaces = spaceDtos;

      if (this.preFilledRuleFormDto?.isPreFilled) {

        const preFilledData = this.preFilledRuleFormDto?.ruleCreationDto;

        this.selectedFromSpace = this.spaces.find(space => space.id === preFilledData?.fromSpaceId)!;
        this.selectedTargetSpace = this.spaces.find(space => space.id === preFilledData?.toSpaceId)!;
        this.date = new Date(preFilledData.dateOfExecution);
        this.name = preFilledData.description;
      }
    });
  }

  onClick() {
    if (!this.selectedFromSpace.id || !this.selectedTargetSpace.id || !this.date || !this.name) {
      this.messageService.add({severity: 'error', summary: 'Error', detail: 'Please fill in all fields'});
      return;
    }
    const ruleCreationDto: RuleCreationDto = {
      description: this.name,
      dateOfExecution: this.date.toLocaleDateString("en-CA"),
      fromSpaceId: this.selectedFromSpace.id,
      toSpaceId: this.selectedTargetSpace.id,
    }

    this.formSubmit.emit(ruleCreationDto);
  }
}
