interface HamburgerProps {
  isOpen: boolean;
}

export default function Hamburger({ isOpen }: HamburgerProps) {
  return (
    <div className="inline text-blue-500 rounded-lg p-2 float-left my-4 mx-10 cursor-pointer hover:bg-gray-100">
      <button className="justify-center items-center">
        <span
          className={`bg-blue-500 block transition-all duration-300 ease-out 
                    h-0.5 w-6 rounded-sm ${
                      isOpen ? "rotate-45 translate-y-1" : "-translate-y-0.5"
                    }`}
        ></span>
        <span
          className={`bg-blue-500 block transition-all duration-300 ease-out 
                    h-0.5 w-6 rounded-sm my-0.5 ${
                      isOpen ? "opacity-0" : "opacity-100"
                    }`}
        ></span>
        <span
          className={`bg-blue-500 block transition-all duration-300 ease-out 
                    h-0.5 w-6 rounded-sm ${
                      isOpen ? "-rotate-45 -translate-y-1" : "translate-y-0.5"
                    }`}
        ></span>
      </button>
      <span className="ml-2">MENY</span>
    </div>
  );
}
