import { createBrowserRouter } from "react-router-dom";
import CityPage from "./components/CityPage";
import HomePage from "./components/HomePage";
import IntCItiesPage from "./components/IntCItiesPage";
import Layout from "./components/Layout";
import LogInScreen from "./components/LogInScreen";
import NavBar from "./components/NavBar";

 
 const router = createBrowserRouter([
    {
        path:'/',
        element:<Layout/>,
        children:[
            
            { path: '',element : <HomePage/>},
            { path: 'login',element : <LogInScreen/>},
            { path: 'cityRides',element : <HomePage/>},
            { path: 'intCityRides',element : <IntCItiesPage/>},
            { path: 'city',element : <CityPage/>},
        ]
    }
 ]);

 export default router;