package com.example.mail.repository;

import java.util.*;
import com.example.mail.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository repository;

    @Override
    public  void saveNote(Note note) {
        repository.save(note);
    }

    @Override
    public  void updateStatus(int id, String status){
        Note update = repository.getOne(id);
        update.setStatus(status);
        repository.save(update);
    }

    @Override
    public ArrayList<Note> findAll() {
        return (ArrayList<Note>)repository.findAll();
    }

    @Override
    public void updateDate(int id) {
        Note update = repository.getOne(id);
        Date date = new Date();
        update.setDate(date);
        repository.save(update);
    }


}
