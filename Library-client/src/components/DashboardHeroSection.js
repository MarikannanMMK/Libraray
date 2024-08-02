export default function DashboardHeroSection () {

    return(

     <div className="container mx-auto mt-12 flex">
        <div className="w-1/2 text-center my-auto ">
            <p className="font-playwritebevlg font-semibold text-3xl">
            Reading brings us unknown friends.
            </p>
        </div>
        <div className="w-1/2">
            <img src={require ("../assests/Dashboardpic.png")} alt="herosection" className="max-w-md m-auto"/>
        </div>
     </div>

    );
    
}