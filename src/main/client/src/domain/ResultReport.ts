import Account from "./Account";

export default class ResultReport {
  private _income: Account[];
  private _expenses: Account[];
  private _financial: Account[];

  constructor(income: Account[], expenses: Account[], financial: Account[]) {
    this._income = income;
    this._expenses = expenses;
    this._financial = financial;
  }

  public get income(): Account[] {
    return this._income;
  }

  public get expenses(): Account[] {
    return this._expenses;
  }

  public get financial(): Account[] {
    return this._financial;
  }
}
