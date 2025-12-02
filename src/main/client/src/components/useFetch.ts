import { useState, useEffect } from "react";

const useFetch = (url: string) => {
  const [data, setData] = useState(null);

  useEffect(() => {
    try {
      fetch(url)
        .then((response) => {
          if (response.ok) {
            return response.json();
          }
          const errorMsg = `Error fetching ${url}: ${response.status} (${response.statusText})`;
          throw new Error(errorMsg);
        })
        .then((data) => setData(data));
    } catch (error) {
      console.error("Fetch error: ", error);
    }
  }, [url]);

  return data;
};

export default useFetch;
