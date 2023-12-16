export class InterCityBusRideDTO {
    id: number;
    estimatedTravelTime: number;
    busCompanyName: String;
    timeOfDeparture:String;
    destination:String;
    frequency:String;
    platform:number;

    constructor(){
        this.id = 0,
        this.estimatedTravelTime = 0,
        this.busCompanyName = "",
        this.timeOfDeparture = "",
        this.destination = "",
        this.frequency = "",
        this.platform = 0
    }
}