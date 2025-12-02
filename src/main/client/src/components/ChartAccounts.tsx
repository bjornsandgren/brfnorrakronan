import useChartAccounts from "../api/useChartAccounts";
import ChartAccount from "../domain/ChartAccount";

export default function ChartAccounts() {
  const chartAccounts: ChartAccount[] = useChartAccounts();

  return (
    <main className="bg-white my-4 mx-5 col-span-8 py-3 rounded-xl border border-gray-200 shadow-sm h-screen">
      <table className="min-w-full divide-y divide-gray-200 dark:divide-neutral-700">
        <thead>
          <tr>
            <th
              scope="col"
              className="px-6 py-2 text-start text-xs font-medium text-gray-500 uppercase dark:text-neutral-500"
            >
              Nummer
            </th>
            <th
              scope="col"
              className="px-6 py-2 text-start text-xs font-medium text-gray-500 uppercase dark:text-neutral-500"
            >
              Namn
            </th>
          </tr>
        </thead>
        <tbody className="divide-y divide-gray-200">
          {chartAccounts &&
            chartAccounts.map((chartAccount) => (
              <tr
                key={chartAccount.number}
                className="even:bg-white odd:bg-gray-100 dark:odd:bg-neutral-900 dark:even:bg-neutral-800"
              >
                <td className="px-6 py-2 whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
                  {chartAccount.number}
                </td>
                <td className="px-6 py-2 whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
                  {chartAccount.name}
                </td>
              </tr>
            ))}
        </tbody>
      </table>
    </main>
  );
}
