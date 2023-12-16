import axios from "axios";
import { useNavigate } from "react-router-dom";
import { LogInDTO } from "../dtos/LogInDTO";

const api = "http://localhost:8090/busUser"
class BusUserService {
  
    
    login (username: String,password: String,role:String)  {
        console.log(username,password)
        axios.post(api+"/login", {
      username: username,
      password: password,
      role:role
    })
    .then((response) => {
      console.log(response);
      localStorage.setItem("username",response.data.username.toString());
      localStorage.setItem("role",response.data.role.toString());
      
    });

    }

    logout(){
    localStorage.removeItem("username");
    localStorage.removeItem("role");
    console.log("klota")
    }
}

export default new BusUserService