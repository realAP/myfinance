export interface SpendingCategoryBlockDto {
  category: Category;
  spendingRowDtos: SpendingRowDto[];
  spendingSumPerCategory: number; // Use appropriate numeric type (e.g., number, BigDecimal, etc.)
}

export interface CategoryCreationDto {
  name: string;
}

export interface CategoryDto {
  id: number;
  name: string;
}

export enum Category {
  VERGNUEGEN = 'VERGNUEGEN',
  SPORT = 'SPORT',
  BANK = 'BANK',
  INVESTITIONEN = 'INVESTITIONEN',
}

export interface SpendingRowDto {
  id: number; // Use appropriate numeric type (e.g., number, bigint, etc.)
  description: string;
  ruleDescription: string;
  transferDescription: string;
  amount: number; // Use appropriate numeric type (e.g., number, BigDecimal, etc.)
  category: string;
}

export interface SpaceCreationDto {
  name: string;
}

export interface SpaceDto {
  id: number;
  name: string;
}

export interface BankCreationDto {
  name: string;
}

export interface BankDto {
  id: number;
  name: string;
}

export interface RuleCreationDto {
  description: string;
  dateOfExecution: string;
  fromSpaceId: number;
  toSpaceId: number;
}

export interface RuleDto {
  id: number;
  description: string;
}

export interface TransferCreationDto {
  description: string;
  dateOfExecution: string;
  fromBankId: number;
  toBankId: number;
}

export interface TransferDto {
  id: number;
  dateOfExecution: string;
  description: string;
  fromBankName: string;
  toBankName: string;
  amount: number;
  oldAmount: number;
  isChange: boolean;
}

export interface SpendingCreationDto {
  categoryId: number;
  description: string;
  amount: number;
  ruleId: number
  transferId: number;
}
