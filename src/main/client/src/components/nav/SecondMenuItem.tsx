import { NavLink } from "react-router-dom";

interface SecondMenuItemProps {
  value: string;
  toLink: string;
}

export default function SecondMenuItem({ value, toLink }: SecondMenuItemProps) {
  return (
    <div className="w-50 hover:border-b-1 hover:text-blue-500 p-3 text-center text-gray-500">
      <NavLink
        className={({ isActive }) => (isActive ? "text-blue-500" : "gray")}
        to={toLink}
      >
        {value}
      </NavLink>
    </div>
  );
}
