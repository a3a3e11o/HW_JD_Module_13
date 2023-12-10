package org.example.data.repository;

import org.example.data.entity.Note;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteRepository {
    private final Map<Long, Note> notes= new HashMap<>();

    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    public Note add(Note note) {
        Long id = generateId();
        note.setId(id);
        notes.put(id, note);
        return note;
    }

    public void deleteById(long id) {
        if (!notes.containsKey(id)) {
            throw new RuntimeException("Note not found with id: " + id);
        }
        notes.remove(id);
    }

    public void update(Note note) {
        Long id = note.getId();
        if (!notes.containsKey(id)) {
            throw new RuntimeException("Note not found with id: " + id);
        }
        notes.put(id,  note);
    }

    public Note getById(long id) {
        if (!notes.containsKey(id)) {
            throw new RuntimeException("Note not found with id: " + id);
        }
        return notes.get(id);
    }

    private Long generateId() {
        return System.currentTimeMillis();
    }

}
