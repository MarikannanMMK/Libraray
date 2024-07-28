import LibraryLogo from '../assests/LibraryLogo.svg';

export default function Header({
    heading
}){
    return(
        <div className="mb-10">
            <div className="flex justify-center">
             <img src ={LibraryLogo}  className="h-14 w-14" alt="Library logo"/>

            </div>
            <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
                {heading}
            </h2>
        </div>
    )
}