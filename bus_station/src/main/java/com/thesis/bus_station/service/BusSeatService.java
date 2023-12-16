package com.thesis.bus_station.service;

import com.google.zxing.WriterException;
import com.thesis.bus_station.dto.BusSeatDTO;
import com.thesis.bus_station.mail.MailService;
import com.thesis.bus_station.mapper.BusSeatMapper;
import com.thesis.bus_station.model.users.BusRide;
import com.thesis.bus_station.model.users.BusSeat;
import com.thesis.bus_station.model.users.BusUser;
import com.thesis.bus_station.qrCode.PdfService;
import com.thesis.bus_station.qrCode.QRCodeGenerator;
import com.thesis.bus_station.repository.BusRideRepository;
import com.thesis.bus_station.repository.BusSeatRepository;
import com.thesis.bus_station.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BusSeatService {
    @Autowired
    BusSeatRepository busSeatRepository;
    @Autowired
    BusRideRepository busRideRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private PdfService pdfService;
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";
    private static final String PDF_PATH = "./src/main/resources/static/img/Reservation.pdf";


    private BusSeatMapper busSeatMapper = new BusSeatMapper();

    public boolean reserveSeat(BusSeatDTO busSeatDTO) {
        try {

            BusRide b = busRideRepository.findById(busSeatDTO.bus_id).get();
            double price = b.getPrice();
            if (busSeatDTO.type_of_discount.equals("student")) {
                price = price * 0.8;
            } else if (busSeatDTO.type_of_discount.equals("penzioner")) {
                price = price * 0.9;
            }
            busSeatDTO.setPrice(price);
            BusUser u = userRepository.getByUsername(busSeatDTO.username);
            Long id = new Long(busSeatRepository.findAll().size() + 1);
            System.out.println(id);
            BusSeat s = busSeatMapper.busSeatDTOToBusSeat(id, busSeatDTO, u, price);
            busSeatRepository.save(s);


            this.getQRCode("http://192.168.100.8:8090/busSeat/buy/" + s.getId());
            pdfService.createPdf(busSeatDTO,b,u,QR_CODE_IMAGE_PATH);
            mailService.sendMailWithInlineResources(u.getEmail(), "Rezervacija",PDF_PATH);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void getQRCode(String hyperLink) {
        byte[] image = new byte[0];
        try {
            QRCodeGenerator.generateQRCodeImage(hyperLink, 250, 250, QR_CODE_IMAGE_PATH);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    public boolean buySeat(Long id) {
        try {
       BusSeat s = busSeatRepository.findById(id).get();
        s.setTicketState("bought");
        busSeatRepository.save(s);
        return true;
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
            return false;
        }
    }
    public void deleteSeats(){
        for (BusRide busRide: busRideRepository.getRidesForToday(LocalDateTime.now())
             ) {
            System.out.println("gas");
            if(busRide.getTimeOfDeparture().minusMinutes(10).plusSeconds(30).isBefore(LocalDateTime.now())  ){
                busSeatRepository.deleteAll(busSeatRepository.allSeatsOfRide(busRide.getId()));
            }
        }
    }
}
