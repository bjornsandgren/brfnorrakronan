import ChartAccount from "./ChartAccount";
export default class Account {
  private chartAccount: ChartAccount;
  private _openingBalance: number;
  private _closingBalance: number;

  constructor(
    number: number,
    name: string,
    openingBalance: number,
    closingBalance: number
  ) {
    this._openingBalance = openingBalance;
    this._closingBalance = closingBalance;
    this.chartAccount = new ChartAccount(number, name);
  }

  public get number(): number {
    return this.chartAccount.number;
  }

  public get name(): string {
    return this.chartAccount.name;
  }

  public get openingBalance(): number {
    return this._openingBalance;
  }

  public get closingBalance(): number {
    return this._closingBalance;
  }
}
