package com.example.mail.Controllers;

import com.example.mail.entity.Note;

import java.awt.*;
import java.util.*;
import com.example.mail.repository.NoteRepository;
import com.example.mail.repository.NoteService;
import com.example.mail.repository.NoteServiceImpl;
import feign.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

//@Configuration
@EnableTransactionManagement
@EnableScheduling
@RestController
public class NoteController {

    @Autowired
    private NoteService repository;
    @Autowired
    public JavaMailSender emailSender;

    class DTO {
        private String message;
        private String topic;
        private String mail;

        public void setMessage(String message) {
            this.message = message;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getMessage() {
            return message;
        }

        public String getTopic() {
            return topic;
        }

        public String getMail() {
            return mail;
        }
    }

    @RequestMapping(path = "/Note", method = RequestMethod.POST,
                        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String note (@RequestBody DTO dto) {
        System.out.println(dto);
//        repository.saveNote(new Note(dto.topic, dto.message, dto.mail));
        return "Запись добавлена";
    }

//    @RequestMapping("/Note")
//    public String note (@RequestParam(value="message", defaultValue="Where many?") String message,
//                      @RequestParam(value="topic", defaultValue="debt") String topic,
//                      @RequestParam(value="mail", defaultValue="test@mail.ru") String mail) {
//        repository.saveNote(new Note(topic, message, mail));
//        return "Запись добавлена";
//    }

    @RequestMapping(path = "/show", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public String show() {
       ArrayList<Note> list  = repository.findAll();
       StringBuilder str = new StringBuilder();
       str.append("<table border='1'><tr><td>ID</td><td>Topic</td><td>Message</td><td>Email</td>" +
               "<td>Send time</td><td>Status</td></tr>");

       for (Note obj : list) {
           str.append("<tr><td>" + obj.getId() + "</td><td>" + obj.getTopic() + "</td><td>" + obj.getMessage() +
                   "</td><td>" + obj.getMail() + "</td><td>" + obj.getDate() + "</td><td>" + obj.getStatus() + "</td></tr>");
       }
       str.append("</table><br/><form method='get' action='send'>" +
               "<button>Send mail</button></form>");
       return str.toString();
    }
    @Scheduled(fixedDelay = 30000)
    @RequestMapping("/send")
    @Transactional
    public void send() throws Exception {
        System.out.println("Timer works");
        ArrayList<Note> list  = (ArrayList<Note>)repository.findAll();
        for (Note obj : list) {
            if (obj.getStatus().equals("not sent")) {
                SimpleMailMessage message = new SimpleMailMessage();

                message.setTo(obj.getMail());
                message.setSubject(obj.getTopic());
                message.setText(obj.getMessage());

                try {
                    this.emailSender.send(message);
                    obj.setStatus("sent");

                }
                catch (Exception e) {
                    obj.setStatus("error");
                }
                obj.setDate(new Date());
                repository.saveNote(obj);
            }
        }
    }

}
