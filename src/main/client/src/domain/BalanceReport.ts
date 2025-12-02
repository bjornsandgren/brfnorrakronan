import Account from "./Account";

export default class BalanceReport {
  private _assets: Account[];
  private _liabilitiesAndEquityCapital: Account[];

  constructor(assets: Account[], liabilitiesAndEquityCapital: Account[]) {
    this._assets = assets;
    this._liabilitiesAndEquityCapital = liabilitiesAndEquityCapital;
  }

  public get assets(): Account[] {
    return this._assets;
  }

  public get liabilitiesAndEquityCapital(): Account[] {
    return this._liabilitiesAndEquityCapital;
  }
}
