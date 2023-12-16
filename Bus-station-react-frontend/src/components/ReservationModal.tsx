import React, { useState } from 'react'
import { Modal, Button } from 'react-bootstrap'
import { InterCityBusRideDTO } from '../dtos/InterCityBusRideDTO';
import BusSeat from './BusSeat';
import {AiOutlineMinusSquare} from 'react-icons/ai'
import "./styles/ReservationModal.css"
import { BusSeatDTO } from '../dtos/BusSeatDTO';
import axios from 'axios';
import Combobox from "react-widgets/Combobox";
import { PiCodesandboxLogoDuotone, PiSquareSplitVerticalDuotone } from 'react-icons/pi';
import Select from "react-select"
interface Props{
    show:boolean;
    bus:InterCityBusRideDTO ;
    handleClose: ()=> void;

}

const ReservationModal = ({show,bus,handleClose}:Props) => {

  let counter = 0;
const handleCLick = () => {
  counter++;
}
const [selectedSeat,setSelectedSeat] =useState<BusSeatDTO>(
  {
    bus_id: 0,
    seat_num: 0,
    username:"",
    return_tic:false,
    type_of_discount:"",
    price:0,
    ticket_state:"",
  }
)
const [isChecked,setIsChecked] =useState(false)
const [box,setBox] =useState("")

const handleReserve = (data:BusSeatDTO) =>{
    console.log(data)
    setSelectedSeat(prevState => ({...prevState, bus_id:data.bus_id,seat_num:data.seat_num,username:data.username,return_tic:data.return_tic,type_of_discount:data.type_of_discount,price:data.price,ticket_state:data.ticket_state}))
}

const handleChange = (selectedOption: { value: React.SetStateAction<string>; }) => {
  console.log(selectedOption.value)
   setSelectedSeat(({...selectedSeat,type_of_discount:selectedOption.value.toString() }))
    console.log(selectedSeat)

   
};
const handleReservation = () => {
  let flag = false
  if(selectedSeat!=null) flag = true;
  axios.put("http://localhost:8090/busSeat/reserveSeat/",selectedSeat)
  .then(res=> {
    console.log(res.data)
  })
  if(flag){
    close()
    alert("Uspeno rezervisano sediste " + selectedSeat?.seat_num)
  }
}
function close(){
  handleClose()
  setIsChecked(false)
  setBox("")
  setSelectedSeat(undefined)
  console.log(isChecked,box)
}
const handleTic = (flag:boolean) =>{
  console.log(isChecked)
  setIsChecked(flag)
  console.log(isChecked)
  setSelectedSeat({...selectedSeat,return_tic: flag})

  console.log(selectedSeat)
}
const options = [
  { value: "student", label: "student" },
  { value: "penzioner", label: "penzioner" },
];
const lastRow = [1,2,3,4,5]
const cols = [1,2,3,4,5,6,7,8,9,10,11,12]
const backrowFunc= lastRow.map((x)=> <BusSeat key={x} pop={x} busId={bus.id} clickCounter={handleCLick} handleReserve={handleReserve} numOfClicks={counter}></BusSeat>)
const otherRowsFunc = (col:number) =>  <>
<BusSeat key={5 + col*4  + 1} pop={5 + col*4  + 1} busId={bus.id} clickCounter={handleCLick} handleReserve={handleReserve} numOfClicks={counter}/><BusSeat key={5 + col*4  + 2} pop={5 + col*4  + 2} busId={bus.id} clickCounter={handleCLick} handleReserve={handleReserve} numOfClicks={counter}/>
<AiOutlineMinusSquare key = {col*4+1} color="white" size = "50"/>
<BusSeat key={5 + col*4  + 3} pop={5 + col*4  + 3} busId={bus.id} clickCounter={handleCLick} handleReserve={handleReserve} numOfClicks={counter}/><BusSeat key={5 + col*4  + 4} pop={5 + col*4  + 4} busId={bus.id} clickCounter={handleCLick} handleReserve={handleReserve} numOfClicks={counter}/><br/>
</>

const finalFunc =  cols.map((element,index)=> otherRowsFunc(index))

  return (   
    <>
    <Modal show={show} size="lg">
        
        <Modal.Header closeButton onClick={handleClose}>
          <Modal.Title>Prevoznik - {bus.busCompanyName} ,Destinacija {bus.destination}  {bus.timeOfDeparture.substring(0,10)} {bus.timeOfDeparture.substring(11,16)}  </Modal.Title>
        </Modal.Header>
        <Modal.Body>
            
          
        
          <div>
          {backrowFunc}
          <br/>
          {finalFunc}
          </div>
          <hr></hr> 
          <div className="row">     
            <div className="col">     
              <div className="w-80">
              <Select options={options} placeholder={"Izaberite popust"}  onChange={handleChange} autoFocus={true} />
              </div>
          </div>
         
         
          
          </div>
        </Modal.Body>
        <Modal.Footer>
        
  <br/>
      <div className='row justify-content-md-center'>
        <div className='col'>
          <Button variant="danger" onClick={()=> close()} >
            Close
          </Button>
          </div>
          <div  className='col'>
          <Button variant="success" onClick={handleReservation}>
            Rezervisi
          </Button>    
          </div>
          </div>        
        </Modal.Footer>
      </Modal>
    </>
  )
}

export default ReservationModal