export interface SpendingCategoryBlockDto {
  category: Category;
  spendingRowDtos: SpendingRowDto[];
  spendingSumPerCategory: number; // Use appropriate numeric type (e.g., number, BigDecimal, etc.)
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
