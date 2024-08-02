import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import LoginPage from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import Books from "./pages/Books";

import Navbar from "./components/Navbar";
import ShowNavbar from "./components/ShowNavbar";

function App() {
  return (
    <>
      <BrowserRouter>
        <ShowNavbar>
          <Navbar />
        </ShowNavbar>
        <Routes>
          <Route exact path="/login" element={<LoginPage />} />
          <Route exact path="/successlogin" element={<Dashboard />} />
          <Route exact path="/books" element={<Books />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
