import { Outlet } from "react-router-dom";
import SecondMenuItem from "./SecondMenuItem";

export default function Accounting() {
  return (
    <div className="col-span-7">
      <h2 className="p-3 text-gray-400 text-sm bg-white">Bokföring</h2>

      <nav className="border-t border-b border-gray-200 bg-white">
        <ul className="inline-flex w-full">
          <li>
            <SecondMenuItem value="Verifikationer" toLink="verifikationer" />
          </li>
          <li>
            <SecondMenuItem value="Kontoplan" toLink="kontoplan" />
          </li>
        </ul>
      </nav>
      <Outlet />
    </div>
  );
}
