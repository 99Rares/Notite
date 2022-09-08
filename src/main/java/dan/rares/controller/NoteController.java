package dan.rares.controller;

import dan.rares.model.Note;
import dan.rares.repository.NoteFileRepository;
import dan.rares.repository.NoteRepository;

import java.io.IOException;

public class NoteController {
    private NoteRepository noteRepository;


    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteRepository getNoteRepository() {
        return noteRepository;
    }


    public void populateListWithNotes() throws IOException {
        noteRepository.setNotes(new NoteFileRepository().loadData());
    }

    public void saveDataToFile() {
        new NoteFileRepository().writeData(getNoteRepository().findAll());
    }

    public Note findOne(String title) {
        return noteRepository.findOne(title);
    }

    public Iterable<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note save(Note entity) {
        return noteRepository.save(entity);
    }

    public Note delete(String title) {
        return noteRepository.delete(title);
    }


}

