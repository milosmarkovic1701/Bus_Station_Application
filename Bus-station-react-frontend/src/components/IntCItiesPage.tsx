import axios from 'axios';
import React, { useEffect, useState } from 'react'
import BusTable from './BusTable';
import Select from "react-select"

const IntCItiesPage = () => {
  
    const [selectedTime,setSelectedTime]=useState("")
    const [selectedDestination,setSelectedDestination]=useState("")
    const [options,setOptions]=useState([])
    
  
    useEffect(() => {
      axios.get("http://localhost:8090/cities/geOutter").then(res=> 
      {
        console.log(res.data)
        setOptions(res.data)
        console.log(options)
      } )
    } ,[]);
    const handleChange = (selectedOption: { value: React.SetStateAction<string>; }) => {
      console.log(selectedOption.value)
      setSelectedDestination(selectedOption.value)
    };
    const onChange = (date:string)  => {
      axios.get("http://localhost:8090/cities/getOutter").then(res=> 
      {
        console.log(res.data)
        setOptions(res.data)
        console.log(options)
      } 
      )
  
      setSelectedTime(date)
      
    }
    
    return (
      <>
      <h1>INTERNACIONALNI RASPORED VOZNJE</h1>
     <div className="row justify-content-md-center">   
        <div id= "cn" className="col col-lg-2">
        <label>Unesite datum putovanja: </label>
        </div>  
        <div  id= "cn"  className="col col-lg-2" >
        <input type="date" onChange={(event) => onChange(event.target.value)} className="form-control"></input>
        </div>  
      </div>
      { selectedTime ? 
      <div className="row justify-content-md-center">   
        <div id= "cn" className="col col-lg-2">
        <label>Unesite destinaciju putovanja: </label>
        </div>  
        <div  id= "cn"  className="col col-lg-2" >
        <Select options={options} placeholder={"Izaberite destinaciju"}  onChange={handleChange} autoFocus={true} />
      </div>  
     </div> : null 
  }
  
   <BusTable time = {selectedTime} destination = {selectedDestination} />
      
      </>
      
    )
  }



export default IntCItiesPage