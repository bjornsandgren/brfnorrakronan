export default class Verification {
  number: number;
  serie: string;
  date: string;
  text: string;
  transactions: Transaction[];

  constructor(
    number: number,
    serie: string,
    date: string,
    text: string,
    transactions: Transaction[]
  ) {
    this.number = number;
    this.serie = serie;
    this.date = date;
    this.text = text;
    this.transactions = transactions;
  }

  public static amount(verification: Verification): string {
    return verification.transactions
      .reduce(
        (total, transaction) =>
          transaction.amount > 0 ? total + transaction.amount : 0,
        0
      )
      .toFixed(2);
  }
}

class Transaction {
  accountNumber: string;
  accountName: string;
  amount: number;

  constructor(accountNumber: string, accountName: string, amount: number) {
    this.accountNumber = accountNumber;
    this.accountName = accountName;
    this.amount = amount;
  }
}
