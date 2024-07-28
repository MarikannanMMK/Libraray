import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginFields } from "../constants/FormFields";
import FormAction from "./FormAction";
import FormExtra from "./FormExtra";
import Input from "./Input";
import axios from "axios";
import { jwtDecode } from "jwt-decode";

const fields = loginFields;
let fieldsState = {};
fields.forEach((field) => (fieldsState[field.id] = ""));

export default function Login() {
  const [loginState, setLoginState] = useState(fieldsState);
  const navigate = useNavigate();
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setLoginState({ ...loginState, [e.target.id]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    authenticatorAPI();
  };

  const authenticatorAPI = async () => {
    console.log(loginState);
    try {
      const response = await axios.post(
        "http://localhost:8080/login",
        loginState,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      const token = response.data;
      console.log(response);
      localStorage.setItem("token", token);
      const decoded = jwtDecode(token);
      console.log(decoded);
      console.log(decoded.role)
      localStorage.setItem('UserRole',decoded.role);
      localStorage.setItem('Username',decoded.sub)
      //localStorage.setItem('refreshToken', refreshToken);
      navigate("/successlogin");
    } catch (error) {
      setError("Inavalid Username or Password !!");
    }
  };

  return (
    <form className="mt-8 space-y-6">
      <div className="-space-y-px">
        {fields.map((field) => (
          <Input
            key={field.id}
            handleChange={handleChange}
            value={loginState[field.id]}
            labelText={field.labelText}
            labelFor={field.labelFor}
            id={field.id}
            name={field.name}
            type={field.type}
            isRequired={field.isRequired}
            placeholder={field.placeholder}
          />
        ))}
      </div>
      <FormExtra />
      <FormAction handleSubmit={handleSubmit} text="Login" />
      {setError && <p className="text-sm text-red-500 ">{error}</p>}
    </form>
  );
}
