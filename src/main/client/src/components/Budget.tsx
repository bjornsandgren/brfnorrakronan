function ResultReport() {
  const listItems = resultReport.map((row) => (
    <tr
      key={row.id}
      className="even:bg-white odd:bg-gray-100 dark:odd:bg-neutral-900 dark:even:bg-neutral-800"
    >
      <td className="px-6 py-2 whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
        {row.id}
      </td>
      <td className="px-6 py-2 whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
        {row.text}
      </td>
      <td className="px-6 py-2 text-end whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
        {row.last}
      </td>
      <td className="px-6 py-2 text-end whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
        {row.current}
      </td>
      <td className="px-6 py-2 text-end whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
        {row.budget}
      </td>
    </tr>
  ));
  return (
    <div>
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
              Föregående
            </th>
            <th
              scope="col"
              className="px-6 py-2 text-end text-xs font-medium text-gray-500 uppercase dark:text-neutral-500"
            >
              Innevarande
            </th>
            <th
              scope="col"
              className="px-6 py-2 text-end text-xs font-medium text-gray-500 uppercase dark:text-neutral-500"
            >
              Budget
            </th>
          </tr>
        </thead>
        <tbody className="divide-y divide-gray-200">{listItems}</tbody>
      </table>
    </div>
  );
}

export default function Budget() {
  return <ResultReport />;
}

const resultReport = [
  {
    id: "3021",
    text: "Årsavgifter bostäder",
    last: "33 727",
    current: "917 197",
    budget: "990 000",
  },
  {
    id: "3073",
    text: "Bredband",
    last: "0",
    current: "64 271",
    budget: "70 001",
  },
  {
    id: "3073",
    text: "Värde",
    last: "3 727",
    current: "7 197",
    budget: "8 000",
  },
  {
    id: "3092",
    text: "Övriga intäkter",
    last: "0",
    current: "271",
    budget: "0",
  },
];
