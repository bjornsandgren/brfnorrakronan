import { useState } from "react";
import Verification from "../domain/Verification";
import VerificationList from "../domain/VerificationList";
import useVerifications from "../api/useVerifications";
//import useFetch from "./useFetch";

export default function Verifications() {
  const allVerifications: Verification[] = useVerifications();

  // useFetch("http://localhost:8080/api/daybook/") || [];
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const [selectedSerie, setSelectedSerie] = useState("");
  const [verifications, setVerifications] = useState(allVerifications);

  const handleDropdownClick = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  const handleSerieSelect = (serie: string) => {
    setSelectedSerie(serie);
    setIsDropdownOpen(false);

    if (serie == "Alla") {
      setVerifications(allVerifications);
      return;
    }

    const copyVerifications = VerificationList.deepCopy(
      allVerifications,
      serie
    );
    setVerifications(copyVerifications);
  };

  return (
    <main className="bg-white my-4 mx-5 col-span-8 py-3 rounded-xl border border-gray-200 shadow-sm h-screen">
      <div className="mx-10 my-4">
        <button
          id="dropdownDefaultButton"
          data-dropdown-toggle="dropdown"
          className="inline-flex items-center justify-center text-black-600 border border-gray-200 rounded-xl shadow-xs font-medium leading-5 rounded-base text-sm px-4 py-2.5 hover:bg-gray-100"
          type="button"
          onClick={handleDropdownClick}
        >
          Verifikationsserie
          <svg
            className="w-4 h-4 ms-1.5 -me-0.5"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            fill="none"
            viewBox="0 0 24 24"
          >
            <path
              stroke="currentColor"
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth="2"
              d="m19 9-7 7-7-7"
            />
          </svg>
        </button>

        <div
          id="dropdown"
          className={`${
            isDropdownOpen ? "block" : "hidden"
          } border border-gray-200 rounded-xl  shadow-lg w-44 absolute bg-white mt-1`}
        >
          <ul
            className="p-2 text-sm text-body font-medium"
            aria-labelledby="dropdownDefaultButton"
          >
            {allVerifications &&
              VerificationList.series(allVerifications).map((serie) => (
                <li key={serie}>
                  <a
                    href="#"
                    className={` ${
                      selectedSerie === serie ? "bg-gray-100" : null
                    } inline-flex items-center w-full p-2 hover:bg-neutral-tertiary-medium hover:text-heading rounded`}
                    onClick={() => handleSerieSelect(serie)}
                  >
                    {serie}
                  </a>
                </li>
              ))}
          </ul>
        </div>
      </div>

      <div>
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
                Datum
              </th>
              <th
                scope="col"
                className="px-6 py-2 text-start text-xs font-medium text-gray-500 uppercase dark:text-neutral-500"
              >
                Text
              </th>
              <th
                scope="col"
                className="px-6 py-2 text-end text-xs font-medium text-gray-500 uppercase dark:text-neutral-500"
              >
                Summa
              </th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-200">
            {verifications &&
              verifications.map((ver) => (
                <tr
                  key={ver.serie + ver.number}
                  className="even:bg-white odd:bg-gray-100 dark:odd:bg-neutral-900 dark:even:bg-neutral-800"
                >
                  <td className="px-6 py-2 whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
                    {ver.serie}
                    {ver.number}
                  </td>
                  <td className="px-6 py-2 whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
                    {ver.date}
                  </td>
                  <td className="px-6 py-2 whitespace-nowrap text-sm font-medium text-gray-600 dark:text-neutral-200">
                    {ver.text}
                  </td>
                  <td className="px-6 py-2 whitespace-nowrap text-end text-sm font-medium text-gray-600 dark:text-neutral-200">
                    {Verification.amount(ver)}
                  </td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
    </main>
  );
}
