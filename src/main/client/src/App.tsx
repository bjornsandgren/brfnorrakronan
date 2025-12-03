import Budget from "./components/Budget";
import Verifications from "./components/Verifications";
import "./App.css";
import { Routes, Route } from "react-router-dom";
import EconomyMenu from "./components/nav/EconomyMenu";
import AccountingMenu from "./components/nav/AccountingMenu";
import ChartAccounts from "./components/ChartAccounts";
import BalanceSheet from "./components/BalanceSheet";
import ResultReport from "./components/ResultReport";
import Hamburger from "./components/nav/Hamburger";
import FirstMenu from "./components/nav/FirstMenu";
import { useState } from "react";
import { useEffect } from "react";
import Env from "./Env";

function App() {
  const [isOpen, setIsOpen] = useState(false);
  //Handles the opening and closing of the men
  const handleClick = () => {
    setIsOpen(!isOpen);
  };

  useEffect(() => {
    fetch(`${Env.API_BASE_URL}/ping/`)
      .then((response) => response.text())
      .then((body) => console.log(body));
  }, []);

  return (
    <div className="font-sans text-gray-500 grid grid-cols-8 bg-gray-50">
      <header
        id="logo"
        className="font-bold col-span-8 border-b border-gray-200 bg-white"
      >
        <div onClick={handleClick}>
          <Hamburger isOpen={isOpen} />
        </div>

        <div className="float-right mr-20 pt-7">BRF Norra Kronan</div>
      </header>
      <aside className="col-span-1 border-r border-gray-200 min-h-screen bg-white h-full">
        <menu onClick={handleClick} aria-label="Menu">
          <FirstMenu />
        </menu>
      </aside>

      <Routes>
        <Route path="/bokforing" element={<AccountingMenu />}>
          <Route path="verifikationer" element={<Verifications />} />
          <Route path="kontoplan" element={<ChartAccounts />} />
        </Route>
        <Route path="/ekonomi" element={<EconomyMenu />}>
          <Route path="balansrapport" element={<BalanceSheet />} />
          <Route path="resultatrapport" element={<ResultReport />} />
          <Route path="budget" element={<Budget />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
