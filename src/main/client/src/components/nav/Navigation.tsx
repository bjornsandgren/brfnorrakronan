import { useState } from "react";
import FirstMenu from "./FirstMenu";

export default function Navigation() {
  const [isOpen, setIsOpen] = useState(false);
  //Handles the opening and closing of the men
  const handleClick = () => {
    setIsOpen(!isOpen);
  };

  return (
    <menu onClick={handleClick} aria-label="Menu">
      <div
        className={`border-r border-b border-gray-300 p-8 absolute bg-white mt-19 h-50 rounded ${
          isOpen ? "opacity-100" : "opacity-0 pointer-events-none"
        }`}
      >
        <FirstMenu />
      </div>
    </menu>
  );
}
