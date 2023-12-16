export class BusSeatDTO {
    bus_id: number;
    seat_num: number;
    username:String;
    return_tic:boolean;
    type_of_discount:String;
    price:number;
    ticket_state:String;

    constructor(){
        this.bus_id = 0,
        this.seat_num = 0,
        this.username = "",
        this.return_tic = false,
        this.type_of_discount = "",
        this.price = 0,
        this.ticket_state = ""

    }
}