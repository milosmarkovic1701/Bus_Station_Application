export class LogInDTO {
    username: String;
    password: String;
    role:String;


    constructor(){
        this.username = "",
        this.password = "",
        this.role = ""
    }
}