import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import { setupCache } from "axios-cache-interceptor";
import Env from "../Env";

const URL = `${Env.API_BASE_URL}/daybook/`;

const client = axios.create({
  baseURL: URL,
});

console.log("Creating Axios client:");

const cachedClient = setupCache(client);

const useVerifications = () => {
  const [verifications, setVerifications] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const request = cachedClient.get("/");
      const [response] = await Promise.all([request]);
      console.log("Verifications response:", response.data);
      console.log("Cached response: " + response.cached);
      setVerifications(response.data);
    }
    fetchData();
  }, []);
  return verifications;
};

export default useVerifications;
