package com.example.mail.repository;

import com.example.mail.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {
} //храним сущность Note, integer - целочисленый итератор для обращения
