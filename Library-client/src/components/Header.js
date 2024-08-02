import  Logo  from "../assests/LibraryLogo.svg";

export default function Header({ heading }) {
  return (
    <div className="mb-10">
      <div className="flex justify-center">
      <img src={Logo} height="35%" width="35%" alt="Logo"/>
      </div>
      <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
        {heading}
      </h2>
    </div>
  );
}
