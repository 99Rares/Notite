package dan.rares;

import dan.rares.controller.NoteController;
import dan.rares.repository.NoteRepository;
import dan.rares.view.UI;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        NoteRepository noteRepository = new NoteRepository();
        NoteController noteController = new NoteController(noteRepository);
        try {
            noteController.populateListWithNotes();

        } catch (FileNotFoundException e) {
            System.err.println("no file found.");
        }catch (EOFException f){
            System.err.println("file is empty.");
        }

        UI ui = new UI(noteController);
        ui.menu();
    }
}
