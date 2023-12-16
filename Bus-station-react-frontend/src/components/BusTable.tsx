import axios from "axios";
import { useEffect, useState } from "react";
import { InterCityBusRideDTO } from "../dtos/InterCityBusRideDTO";
import BusRideService from "../services/BusRideService";
import BusSeat from "./BusSeat";
import { Modal, Button } from 'react-bootstrap'
import BusReservationModal from "./ReservationModal";
import ReservationModal from "./ReservationModal";


interface Props{
  time:string;
  destination:string;
}
function BusTable({time,destination}:Props){
  
   const [busRides,setBusRides ] = useState<InterCityBusRideDTO[]>([]);
   const[buttonState,setButtonState]=useState(0)
   const [isLoading,setIsLoading] = useState(false);
   const [selectedBusRide,setSelectedBusRide ] = useState<InterCityBusRideDTO>();
   const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = (bus:InterCityBusRideDTO) => {setShow(true); setSelectedBusRide(bus);console.log(bus) }
   const handleClick = () => {
    console.log(time,destination)
    setIsLoading(true)
    axios.get("http://localhost:8090/busRide/getInterByParams/" + time + "/" + destination)
  .then(res=> {
    setBusRides(res.data); 
    console.log(busRides)
    setIsLoading(false)
  })
  setButtonState(1)
   }
   useEffect(() => {
    
  } ,[]);

    return ( 
      
     
       <>
       { time && destination ? 

<button id="specialButton" className="btn btn-success" onClick={handleClick}>Pretrazi</button>
:
null
}
{ 
time && destination && buttonState ? 

        <div className="mb-3">
        {isLoading && <div className="spinner-border"></div>}
        <table className="table .table-sm table-dark table-striped table-bordered  ">
  <thead>
    <tr >
      <th scope="col">Prevoznik</th>
      <th scope="col">Destinacija</th>
      <th scope="col">Vreme kretanja</th>
      <th scope="col">Vreme putovanja</th>
      <th scope="col">Peron</th>
      { localStorage.getItem("username") ? <th scope="col">Rezervisanje</th>:null}
    </tr>
  </thead>
  <tbody>
      {busRides.map(busRide=>
      <tr key = {busRide.id}>
        <td>{busRide.busCompanyName}</td>
        <td>{busRide.destination}</td>
        <td>{busRide.timeOfDeparture.substring(11,16)}</td>
        <td>{busRide.estimatedTravelTime} minuta</td>
        <td>{busRide.platform}</td>
       
        { localStorage.getItem("username") ? <td><button type="button"  className="btn btn-primary"  onClick={()=>handleShow(busRide)}>Rezervisite</button></td>:null}
    </tr> 
        )}
  </tbody>
</table>

  
</div> : null
}
{selectedBusRide?<ReservationModal show={show} bus={selectedBusRide} handleClose={handleClose}></ReservationModal>: null}

</>
    );
}
export default BusTable;