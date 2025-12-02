import useIncome from "../api/useIncome";
import ResultTable from "./ResultTable";
import ResultReports from "../domain/ResultReport";

export default function ResultReport() {
  const result: ResultReports = useIncome();

  return (
    <main className="bg-white my-4 mx-5 col-span-8 py-3 rounded-xl border border-gray-200 shadow-sm h-screen">
      <h1 className="p-5 font-bold text-gray-400 border-b border-gray-200">
        Resultat
      </h1>
      <ResultTable accounts={result.income} />
      <h1 className="p-5 font-bold text-gray-400 border-b border-gray-200">
        Kostnader
      </h1>
      <ResultTable accounts={result.expenses} />
      <h1 className="p-5 font-bold text-gray-400 border-b border-gray-200">
        Finansiella intäkter och kostnader
      </h1>
      <ResultTable accounts={result.financial} />
    </main>
  );
}
