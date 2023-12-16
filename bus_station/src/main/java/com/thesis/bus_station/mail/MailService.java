package com.thesis.bus_station.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;
@EnableAutoConfiguration
@Service
public class MailService<T> {
    private Environment env;
    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(Environment env, JavaMailSender javaMailSender) {
        this.env = env;
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendMail(String recipient, T params, MailFormatter<T> mailFormatter) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(message);
        mail.setTo(recipient);
        mail.setFrom(Objects.requireNonNull(this.env.getProperty("spring.mail.username")));
        mail.setSubject(mailFormatter.getSubject());
        mail.setText(mailFormatter.getText(params),true);
        javaMailSender.send(message);
    }

    public void sendMailWithInlineResources(String to, String subject, String fileToAttach)
    {
        MimeMessagePreparator preparator = new MimeMessagePreparator()
        {
            public void prepare(MimeMessage mimeMessage) throws Exception
            {
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(to));
                mimeMessage.setSubject(subject);

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setText("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>Document</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <h6>Postovani,</h6>\n" +
                        "    <h6>u prilogu se nalazi vasa rezervacija. </h6>\n" +
                        "    <br>\n" +
                        "    <h6>Vasa,</h6>\n" +
                        "    <h5>Autobuska stanica Sabac.</h5>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>",true);


                FileSystemResource res = new FileSystemResource(new File(fileToAttach));
                helper.addInline("identifier1234", res);
            }
        };
        javaMailSender.send(preparator);

    }

}
