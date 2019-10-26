package com.example.mail.repository;

import com.example.mail.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class dataInsert implements ApplicationRunner {
    @Autowired
    private NoteService repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        repository.saveNote(new Note("Test", "Test Java", "leha5500d.mail.ru"));
    }

}
