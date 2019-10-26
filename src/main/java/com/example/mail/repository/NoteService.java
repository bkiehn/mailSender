package com.example.mail.repository;

import com.example.mail.entity.Note;

import java.util.ArrayList;

public interface NoteService {
    void saveNote(Note note);
    void updateStatus(int id, String status);
    void updateDate(int id);
    ArrayList<Note> findAll();
}
