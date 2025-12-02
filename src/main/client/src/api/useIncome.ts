import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import { setupCache } from "axios-cache-interceptor";
import ResultReport from "../domain/ResultReport";
import Env from "../Env";

const URL = `${Env.API_BASE_URL}/report/income`;

const client = axios.create({
  baseURL: URL,
});

const cachedClient = setupCache(client);

const useIncome = (): ResultReport => {
  const [income, setIncome] = useState<ResultReport>();

  useEffect(() => {
    async function fetchData() {
      const request = cachedClient.get("/");
      const [response] = await Promise.all([request]);
      setIncome(response.data);
    }
    fetchData();
  }, []);
  return income ?? ({} as ResultReport);
};

export default useIncome;
