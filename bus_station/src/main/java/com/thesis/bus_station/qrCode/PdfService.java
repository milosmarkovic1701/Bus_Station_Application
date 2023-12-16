package com.thesis.bus_station.qrCode;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.thesis.bus_station.dto.BusSeatDTO;
import com.thesis.bus_station.model.users.BusRide;
import com.thesis.bus_station.model.users.BusUser;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PdfService {

    public void createPdf(BusSeatDTO busSeatDTO, BusRide busRide, BusUser user,String path) throws IOException, DocumentException, URISyntaxException {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("./src/main/resources/static/img/Reservation.pdf"));

        document.open();

        Font font = FontFactory.getFont(FontFactory.COURIER, 30, BaseColor.BLACK);
        Font font2 = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK);

        Chunk chunk1 = new Chunk("Autobuska stanica Sabac \n \n \n", font);

        Chunk chunk3 = new Chunk("\n \n ----------------------------", font);
        Chunk chunk = new Chunk("REZERVACIJA",font);
        Paragraph paragraph1 = new Paragraph();
        Paragraph paragraph2 = new Paragraph();
        Paragraph paragraph3 = new Paragraph();
        Paragraph paragraph4 = new Paragraph();
        paragraph4.setAlignment(Element.ALIGN_CENTER);
        Paragraph paragraph5 = new Paragraph();
       Chunk chunkDestination = new Chunk(" Destinacija : " + busRide.getDestination(),font2 );
        Chunk chunk2 = new Chunk(" \n \n Prevoznik : " + busRide.getBusCompanyName(), font2);
       Chunk chunkDisc = new Chunk("\n \n Kategorija : " + busSeatDTO.getType_of_discount(),font2 );

        Chunk chunkDate = new Chunk("  Datum : " + busRide.getTimeOfDeparture().getDayOfMonth() + "/" + "10" + "/" + busRide.getTimeOfDeparture().getYear() ,font2 );
        Chunk chunkTime = new Chunk(" \n \n Vreme: " + busRide.getTimeOfDeparture().getHour() + ":" + busRide.getTimeOfDeparture().getMinute(), font2);
        Chunk chunkPeron = new Chunk("\n \n Peron : " + busRide.getPlatform(),font2 );
        Chunk chunkSeat= new Chunk(" \n \n Sediste : " + (busSeatDTO.seat_num),font2 );
        Chunk chunkPrice = new Chunk("\n \n Cena : " + busSeatDTO.price,font2 );

        Chunk chunkNote = new Chunk("Postovani, dodjite 20 minuta pre polaska autobusa da potvrdite rezervaciju, u suprotnom ista ne vazi.",font2);

        Chunk chunkName = new Chunk("Informacije o kupcu",font);

        Chunk chunkLast = new Chunk("\n\nIme: " + user.getName() + "\n\nPrezime: " + user.getLastName() + "\n\nJmbg: " + user.getJmbg(),font2);
        System.out.println(busSeatDTO.price);
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        paragraph1.add(chunk1);
        paragraph1.add(chunk);
        paragraph1.add(chunk3);

        paragraph2.add(chunkDestination);
        paragraph2.add(chunk2);
        paragraph2.add(chunkDisc);
        paragraph2.add(chunk3);
        paragraph2.add(chunkDate);
        paragraph2.add(chunkTime);
        paragraph2.add(chunkPeron);
        paragraph2.add(chunkSeat);
        paragraph2.add(chunkPrice);
        paragraph2.add(chunk3);

        paragraph3.add(chunk3);
        paragraph3.add(chunkNote);

        paragraph4.add(chunkName);

        paragraph5.add(chunkLast);

        Image img = Image.getInstance(path);
        img.setAlignment(Element.ALIGN_CENTER);
        Header header = new Header("header",busRide.getBusCompanyName());
        document.add(header);
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(img);
        document.add(paragraph3);
        document.newPage();
        document.add(paragraph4);
        document.add(paragraph5);
        document.close();
    }

}
