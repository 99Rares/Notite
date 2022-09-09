package dan.rares.repository;

import dan.rares.Exceptions.NullObjectException;
import dan.rares.Exceptions.NullTitleException;
import dan.rares.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NoteRepository implements ICrudRepository<Note> {

    private List<Note> notes = new ArrayList<>();

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public Note save(Note entity) {
        try {
            if (entity == null)
                throw new NullObjectException("entity must not be null");

            for (Note note : notes)
                if (Objects.equals(note.getTitle(), entity.getTitle())) {
                    System.err.println("Note already exists");
                    return null;
                }

            notes.add(entity);
            return entity;
        } catch (NullObjectException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Iterable<Note> findAll() {
        return notes;
    }

    @Override
    public Note delete(String title) {
        try {
            if (title == null)
                throw new NullTitleException("title most not be null");

            for (Note note : notes)  // Searching for the notes with title "title"
                if (Objects.equals(note.getTitle(), title)) {
                    notes.remove(note);  // If the lists contains the note with the title "title", remove it from the list
                    return note;  // and return the removed note
                }
            System.err.println("Note not found!");
        } catch (NullTitleException e) {
            e.printStackTrace();
        }

        return null;  // If the list doesn't contain the student with studentId "id"
    }

    @Override
    public String toString() {
        return "notes=" + notes;
    }

    @Override
    public Note findOne(String title) {
        try {
            if (title == null)
                throw new NullTitleException("title most not be null");

            for (Note note : notes)
                if (Objects.equals(note.getTitle(), title))
                    return note;
        } catch (NullTitleException e) {
            e.printStackTrace();
        }

        return null;
    }
}
