"use client";

import { NavLink } from "react-router-dom";

export default function FirstMenu() {
  return (
    <aside>
      <div className="px-15 pt-10">
        <ul>
          <li className="hover:text-blue-400 mb-4">
            <NavLink
              className={({ isActive }) =>
                isActive ? "text-blue-400" : "gray"
              }
              to="/bokforing/verifikationer"
            >
              Föreningen
            </NavLink>
          </li>
          <li className="hover:text-blue-400 mb-4">
            <NavLink
              className={({ isActive }) =>
                isActive ? "text-blue-400" : "gray"
              }
              to="/ekonomi"
            >
              Ekonomi
            </NavLink>
          </li>
          <li className="hover:text-blue-400 mb-4">
            <NavLink
              className={({ isActive }) =>
                isActive ? "text-blue-400" : "gray"
              }
              to="/bokforing/verifikationer"
            >
              Medlemmar
            </NavLink>
          </li>
          <li className="hover:text-blue-400 mb-4">
            <NavLink
              className={({ isActive }) =>
                isActive ? "text-blue-400" : "gray"
              }
              to="/bokforing/verifikationer"
            >
              Bokningar
            </NavLink>
          </li>
          <li className="hover:text-blue-400 mb-4">
            <NavLink
              className={({ isActive }) =>
                isActive ? "text-blue-400" : "gray"
              }
              to="/bokforing/verifikationer"
            >
              Styrelse
            </NavLink>
          </li>
          <li className="hover:text-blue-400 mb-4">
            <NavLink
              className={({ isActive }) =>
                isActive ? "text-blue-400" : "gray"
              }
              to="/bokforing/verifikationer"
            >
              Bokföring
            </NavLink>
          </li>
        </ul>
      </div>
    </aside>
  );
}
