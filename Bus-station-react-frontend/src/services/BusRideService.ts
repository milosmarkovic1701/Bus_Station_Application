import axios from "axios";
import { InterCityBusRideDTO } from "../dtos/InterCityBusRideDTO";
import { LogInDTO } from "../dtos/LogInDTO";

const api = "http://localhost:8090/busRide"

class BusUserService {

    
    login(username: String,password: String,role:String){
      let loginDto :LogInDTO
        console.log(username,password)
        axios.post(api+"/login", {
      username: username,
      password: password,
      role:role
    })
    .then((response) => {
      loginDto = response.data
      console.log(response);
      localStorage.setItem("username",loginDto.username.toString());
      localStorage.setItem("role",loginDto.role.toString());
      
    });
    }

    getAll():any{
       let busList : InterCityBusRideDTO[]
        
    axios.get(api+"/getInter")
    .then(res=> {
         return res.data

    })
    
    }
}

export default new BusUserService