import { NavLink, Outlet } from "react-router-dom";

export default function Economi() {
  return (
    <div className="col-span-7">
      <h2 className="p-3 text-gray-400 text-sm bg-white">Ekonomi</h2>

      <nav className="border border-blue-100 bg-white">
        <ul className="inline-flex w-full">
          <li className="w-50 hover:border-b-1 hover:text-blue-500 p-3 text-center text-gray-500">
            <NavLink
              className={({ isActive }) =>
                isActive ? "text-blue-500" : "gray"
              }
              to="balansrapport"
            >
              Balansrapport
            </NavLink>
          </li>
          <li className="w-50 hover:border-b-1 hover:text-blue-500 p-3 text-center text-gray-500">
            <NavLink
              className={({ isActive }) =>
                isActive ? "text-blue-500" : "gray"
              }
              to="resultatrapport"
            >
              Resultatrapport
            </NavLink>
          </li>
          <li className="w-50 hover:border-b-1 hover:text-blue-500 p-3 text-center text-gray-500">
            <NavLink
              className={({ isActive }) =>
                isActive ? "text-blue-400" : "gray"
              }
              to="budget"
            >
              Budget
            </NavLink>
          </li>
        </ul>
      </nav>
      <Outlet />
    </div>
  );
}
