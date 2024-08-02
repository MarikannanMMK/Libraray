import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

const ShowNavbar = ({ children }) => {
  const location = useLocation();
  const [navbar, setnavbar] = useState(false);

  useEffect(() => {
    console.log(location.pathname);
    if (location.pathname === "/login") {
      setnavbar(false);
    } else {
      setnavbar(true);
    }
  }, [location]);

  return <div> {navbar && children}</div>;
};

export default ShowNavbar;
