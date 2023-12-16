import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { BusLineDTO } from '../dtos/BusLineDTO'
import { BusTermDTO } from '../dtos/BusTermDTO'
import { OptionDTO } from '../dtos/OptionDTO'
import Select from "react-select"
import { FrequencyDTO } from '../dtos/frequencyDTO'

const CityPage = () => {
  const [busLines,setBusLines]=useState<BusLineDTO[]>()
  const [busLine,setBusLine]=useState<BusLineDTO>()
  const [termsA,setTermA]=useState<String[]>()
  const [termsB,setTermB]=useState<String[]>()
  const [options,setOptions] = useState<OptionDTO[]>()
  const [myList,setMyList] = useState<String[]>()
  const [frequency,setFrequency] = useState("")
  const freqOptions = [
    { value: "radnidan", label: "radni dan" },
    { value: "vikend", label: "vikend" },
  ];
  const option : OptionDTO = {value:"",label:""}
  const option1 : OptionDTO[] = []
  function myFunc(x:BusLineDTO) {
  option.value = x.id + "-" + x.name;
  option.label = x.id + "-" + x.name ;
  console.log(option);
  if(option1.includes(option))
    return
  option1.push(option);}
  
  useEffect(() => {
      axios.get("http://localhost:8090/cityBusRide/getAll").then(res =>{
        setBusLines(res.data)
        console.log(res.data)
        res.data?.map(myFunc)
        setOptions(option1)
      }
      )
     
  }, [])
  const handleChange = (selectedOption: { value: React.SetStateAction<string>; }) => {
    console.log(selectedOption.value)
    axios.get("http://localhost:8090/cityBusRide/get/" + selectedOption.value.toString().split("-")[0]).then(res =>{
      setBusLine(res.data)
      console.log(res.data)
    }
    )
  };

  const handleChangeFreq = (selectedOption: { value: React.SetStateAction<string>; }) => {
    setFrequency(selectedOption.value)
    axios.get("http://localhost:8090/term/getTerms/"+ busLine?.id + "/" + selectedOption.value +  "/A" ).then(res=>{
      setTermA(res.data)
    } )
    axios.get("http://localhost:8090/term/getTerms/"+ busLine?.id + "/" + selectedOption.value +  "/B" ).then(res=>{
      setTermB(res.data)
    } )
    
  }
  const handleClick = () =>{
    setMyList(new Array(Math.max(termsA.length,termsB.length)).fill('hello'))
     console.log(termsA,termsB)
  }
  function reverseString(name:String){
    let parts = name.split("-")
    let ret_parts = []
    for(let i=parts.length;i--;i>0){
      ret_parts.push(parts[i])
      if(i>0)
      ret_parts.push("-")

    }
    return ret_parts
  }
  return (
    <>
    <h1>GRADSKI RASPORED VOZNJE</h1>
    <div className="row justify-content-md-center">   
        <div id= "cn" className="col col-lg-2">
        <label>Unesite liniju:   </label>
        </div>  
        <div  id= "cn"  className="col col-lg-2" >
        <Select options={options} placeholder={"Izaberite destinaciju"}  onChange={handleChange} autoFocus={true}  />
      </div> 
      </div>  
      <div className="row justify-content-md-center">   
      <div id= "cn" className="col col-lg-2">
        <label>Izaberite dan:   </label>
        </div>  
        <div  id= "cn"  className="col col-lg-2" >
        <Select options={freqOptions} placeholder={"Izaberite opciju"}   onChange={handleChangeFreq} autoFocus={true}  />
        
       

      </div> 
     
     </div> 
     <div className="row justify-content-md-center">  
     <button id="specialButton" className="btn btn-success" onClick={handleClick}>Pretrazi</button>
     </div>
    <table className="table .table-sm table-dark table-striped table-bordered  ">
        <thead>
            <tr> 
                <th colSpan={2}>LINIJA {busLine?.name}</th>
                
            </tr>
            <tr> 
              {!busLine? <><th>Smer A </th><th>Smer B </th></>:<><th>Smer A - {busLine?.name}</th><th>Smer B - {reverseString(busLine?.name)}</th></>}
                
            </tr>
        </thead>
        <tbody>
        
          {myList?.map((element,index)=>
             <tr key = {index}>
               <td><label id = "Hour">{termsA?.at(index)?.substring(0,2)}</label> <label id="Minutes">{termsA?.at(index)?.substring(3)}</label></td>
               <td><label id = "Hour">{termsB?.at(index)?.substring(0,2)}</label> <label id="Minutes">{termsB?.at(index)?.substring(3)}</label></td>
            </tr> 
            )}
  </tbody>
    </table>
    </>
  )
}

export default CityPage