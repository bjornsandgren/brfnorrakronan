import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import { setupCache } from "axios-cache-interceptor";
import type BalanceReport from "../domain/BalanceReport";
import Env from "../Env";

const URL = `${Env.API_BASE_URL}/report/balance/`;

const client = axios.create({
  baseURL: URL,
});

const cachedClient = setupCache(client);

const useBalance = (): BalanceReport => {
  const [balance, setBalance] = useState<BalanceReport>();

  useEffect(() => {
    async function fetchData() {
      const request = cachedClient.get("/");
      const [response] = await Promise.all([request]);
      setBalance(response.data);
    }
    fetchData();
  }, []);
  return balance ?? ({} as BalanceReport);
};

export default useBalance;
