package dan.rares;

import dan.rares.controller.NoteController;
import dan.rares.repository.NoteRepository;
import dan.rares.view.UI;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        NoteRepository noteRepository =new NoteRepository();
        NoteController noteController =new NoteController(noteRepository);
        noteController.populateListWithNotes();
        UI ui = new UI(noteController);
        ui.menu();
    }
}
