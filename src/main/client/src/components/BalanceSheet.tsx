import useBalance from "../api/useBalance";
import Account from "../domain/Account";
import type BalanceReport from "../domain/BalanceReport";
import BalanceTable from "./BalanceTable";

export default function BalanceSheet() {
  const balanceReport: BalanceReport = useBalance();
  const assets: Account[] = balanceReport.assets;
  const liabilitiesAndEquityCapital: Account[] =
    balanceReport.liabilitiesAndEquityCapital;

  return (
    <main className="bg-white my-4 mx-5 col-span-8 py-3 rounded-xl border border-gray-200 shadow-sm h-screen">
      <h1 className="p-5 font-bold text-gray-400 border-b border-gray-200">
        Tillgångar
      </h1>
      <BalanceTable accounts={assets} />
      <h1 className="p-5 font-bold text-gray-400 border-b border-gray-200">
        Skulder och Eget Kapital
      </h1>
      <BalanceTable accounts={liabilitiesAndEquityCapital} />
    </main>
  );
}
