import { IDLE_FETCHER } from '@remix-run/router'
import axios from 'axios'
import React, { useEffect, useState } from 'react'
import {PiArmchairFill} from 'react-icons/pi'
import {PiArmchairLight} from 'react-icons/pi'
import { BusSeatDTO } from '../dtos/BusSeatDTO'
interface Props{
  pop:number,
  busId:number,
  clickCounter: ()=> void;
  numOfClicks:number;
  handleReserve: (arg0: BusSeatDTO) => void
}
const BusSeat = ({pop,busId,clickCounter,numOfClicks,handleReserve}:Props) => {
  const[reserved,setReserved] = useState(false);
  const[isSelected,setIsSelected] = useState(false);
  const[counter,setCounter] = useState(0);
  const[selectedSeat,setSelectedSeat] = useState<BusSeatDTO>();
  function handleClick(){
    clickCounter();
    console.log(numOfClicks)
    console.log(isSelected)
    if(!isSelected){
      setIsSelected(true)
      let seat = new BusSeatDTO();
        seat.bus_id=busId
        seat.seat_num=pop
        seat.username = localStorage.getItem("username")?.toString()
        seat.ticket_state = "reserved"
        handleReserve(seat)
    }
    
  }
  
  
  useEffect(() => {
    axios.get("http://localhost:8090/busSeat/isSeatReserved/" + busId + "/" + pop)
  .then(res=> {
    setReserved(res.data)
  })
  } ,[]);
  
  return (
    
    <>
    {isSelected?<PiArmchairLight color="blue" size="50"   onClick={()=>setIsSelected(false) }></PiArmchairLight> : 
    !reserved? <PiArmchairLight color="red" size="50"  onClick={handleClick}></PiArmchairLight> : <PiArmchairFill color="red" onClick={()=>console.log(pop)} size="50" ></PiArmchairFill>} 
 
    </>
  )
}

export default BusSeat