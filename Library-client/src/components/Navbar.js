import { IoIosArrowDropdown } from "react-icons/io";
import Logo from "../assests/LibraryLogo.svg";
import { useNavigate } from "react-router-dom";

export default function Navbar() {
  const userName = localStorage.getItem("Username");
  const navigate = useNavigate();

  return (
    <div className="container mt-2 mx-auto ">
      <nav className=" bg-purple-600 rounded-2xl flex justify-between items-center mx-3">
        <div className="">
          <a href="/">
            <img src={Logo} alt="Libraray Logo" className="w-14 h-14 mx-2" />
          </a>
        </div>
        <div className="">
          <ul className="flex justify-between space-x-5  text-white font-medium ">
            <li
              className="pb-1 leading-tight hover:link link-underline hover:link-underline-black hover:link-underline:hover"
              onClick={() => navigate("/books")}
            >
              Books
            </li>
            <li className="pb-1 leading-tight hover:link link-underline hover:link-underline-black hover:link-underline:hover">
              User
            </li>
            <li className="pb-1 leading-tight hover:link link-underline hover:link-underline-black hover:link-underline:hover">
              Request
            </li>
            <li className="pb-1 leading-tight hover:link link-underline hover:link-underline-black hover:link-underline:hover">
              Transaction
            </li>
          </ul>
        </div>

        <div className="">
          <button
            id="dropdownAvatarNameButton"
            data-dropdown-toggle="dropdownAvatarName"
            class="flex items-center text-sm pe-1 font-medium text-white rounded-full hover:text-blue-600 dark:hover:text-blue-500 "
            type="button"
          >
            <img className="w-8 h-8 me-3 rounded-full" src={Logo} alt="user" />
            <p className="me-5">{userName}</p>

            <div className="me-3 scale-150">
              {" "}
              <IoIosArrowDropdown />
            </div>
          </button>

          <div class="hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700 dark:divide-gray-600">
            <div class="px-4 py-3 text-sm text-gray-900 dark:text-white">
              <div class="font-medium ">Pro User</div>
              <div class="truncate">name@flowbite.com</div>
            </div>
            <ul class="py-2 text-sm text-gray-700 dark:text-gray-200">
              <li>
                <a
                  href="/"
                  class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
                >
                  Dashboard
                </a>
              </li>
              <li>
                <a
                  href="/"
                  class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
                >
                  Settings
                </a>
              </li>
              <li>
                <a
                  href="/"
                  class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
                >
                  Earnings
                </a>
              </li>
            </ul>
            <div class="py-2">
              <a
                href="/"
                class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
              >
                Sign out
              </a>
            </div>
          </div>
        </div>
      </nav>
    </div>
  );
}
