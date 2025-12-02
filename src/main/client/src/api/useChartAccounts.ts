import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import { setupCache } from "axios-cache-interceptor";
import Env from "../Env";

const URL = `${Env.API_BASE_URL}/accounts/`;

const client = axios.create({
  baseURL: URL,
});

const cachedClient = setupCache(client);

const useAccounts = () => {
  const [accounts, setAccounts] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const request = cachedClient.get("/");
      const [response] = await Promise.all([request]);
      setAccounts(response.data);
    }
    fetchData();
  }, []);
  return accounts;
};

export default useAccounts;
