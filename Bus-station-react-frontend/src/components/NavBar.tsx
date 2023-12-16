import BusUserService from "../services/BusUserService";
import "./styles/NavBar.css"
import {BsFillBusFrontFill} from 'react-icons/bs'
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
const NavBar = () => {
  const navigate = useNavigate();
  const logout = () => {
    BusUserService.logout()
    navigate('/logIn')
   
  }

   
  return (
   <>
   <nav className="navbar sticky-top navbar-expand-sm fixed-top bg-body-tertiary "  data-bs-theme="dark">
  <div className="container-fluid">
    <a className="navbar-brand" >Autobuska stanica Sabac <BsFillBusFrontFill/></a>
    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>
    
    <div className="collapse navbar-collapse " id="navbarNav">
    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
      <ul className="navbar-nav">
        <li className="nav-item">        
          <a className="nav-link active" aria-current="page" href="/pocetna">Pocetna</a>
        </li>
        <li className="nav-item">
          <a className="nav-link" href="/city">Gradska voznja</a>
        </li>
        <li className="nav-item">
          <a className="nav-link" href="/cityRides">Medjugradska voznja</a>
        </li>
        <li className="nav-item">
          <a className="nav-link" href="/intCityRides">Inostrana voznja</a>
        </li>
        <li className="nav-item ">
          <a className="nav-link "  href="/profile">Moj profil</a>
        </li>
        </ul>
        </ul>
        <div
            className="collapse navbar-collapse justify-content-end"
            id="navbarNav"
          >
            <ul className="navbar-nav justify-content-end">
        
       {localStorage.getItem("username")?.toString() ? 
        
        <li className="nav-item ">
         <button type="button" onClick={logout } className="btn btn-danger">Log out</button>
        </li>:null}
        </ul>
        
        </div>
      
    </div>
  </div>
</nav>
   </>
  )
}

export default NavBar