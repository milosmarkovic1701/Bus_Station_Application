export class BusTermDTO {
    line_id: number;
    direction: String;
    frequency:String;
    time_of_departure:String;

    constructor(){
        this.line_id = 0,
        this.direction = "",
        this.frequency = "",
        this.time_of_departure = ""

    }
}