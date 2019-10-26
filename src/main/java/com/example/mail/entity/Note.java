package com.example.mail.entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@Entity //посследующие данные класса, имееют отношение к "Сущности" (к спрингу)
@Table (name = "сlients")
public class Note {

    @Id //последующий атрибут является индитификатором
    @GeneratedValue (strategy = GenerationType.AUTO) //автоматический итератор
    private int id;
    @Column (name = "topic")
    private String topic;
    @Column (name = "message")
    private String message;
    @Column (name = "mail")
    private String mail;
    @Column (name = "date")
    private Date date;
    @Column (name = "status")
    private String status;

    public Note() {
    }

    public Note(String topic, String message, String mail) {
        this.mail = mail;
        this.topic = topic;
        this.message = message;
        this.date = null;
        this.status = "not sent";
    }

    @Override
    public String toString() {
        return String.format (
                "id: %d, Topic: %s, message: %s mail: %s",
                this.id, this.topic, this.message, this.mail
        );
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public String getMail() {
        return mail;
    }

    public int getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    public Date getDate() {
        return this.date;
    }

    public String getStatus() {
        return this.status;
    }
}
