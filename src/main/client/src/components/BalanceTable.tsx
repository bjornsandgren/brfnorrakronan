import type Account from "../domain/Account";

interface BalanceTableProps {
  accounts: Account[];
}

export default function BalanceTable({ accounts }: BalanceTableProps) {
  return (
    <table className="min-w-full divide-y divide-gray-200 dark:divide-neutral-700">
      <thead>
        <tr>
          <th
            scope="col"
            className="px-6 py-2 text-start text-xs font-medium text-gray-500 uppercase dark:text-neutral-500"
          >
            Konto
          </th>
          <th
            scope="col"
            className="px-6 py-2 text-start text-xs font-medium text-gray-500 uppercase dark:text-neutral-500"
          >
            Kontonamn
          </th>
          <th
            scope="col"
            className="px-6 py-2 text-end text-xs font-medium text-gray-500 uppercase dark:text-neutral-500"
          >
            Ingående balans
          </th>
          <th
            scope="col"
            className="px-6 py-2 text-end text-xs font-medium text-gray-500 uppercase dark:text-neutral-500"
          >
            Utgående balans
          </th>
        </tr>
      </thead>
      <tbody className="divide-y divide-gray-200">
        {accounts &&
          accounts.map((account) => (
            <tr
              key={account.number}
              className="even:bg-white odd:bg-gray-100 dark:odd:bg-neutral-900 dark:even:bg-neutral-800"
            >
              <td className="px-6 py-2 whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
                {account.number}
              </td>
              <td className="px-6 py-2 whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
                {account.name}
              </td>
              <td className="px-6 py-2 text-end whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
                {account.openingBalance}
              </td>
              <td className="px-6 py-2 text-end whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
                {account.closingBalance}
              </td>
            </tr>
          ))}
      </tbody>
    </table>
  );
}
