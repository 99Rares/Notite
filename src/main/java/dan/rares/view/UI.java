package dan.rares.view;

import dan.rares.Exceptions.InputMenuException;
import dan.rares.controller.NoteController;
import dan.rares.model.Note;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    NoteController noteController;

    public UI(NoteController noteController) {
        this.noteController = noteController;
    }

    private void menuText() {
        System.out.print("""
                1. Add Note
                2. List all notes
                3. List a specific note
                4. Delete a specific note
                0. Exit
                """
        );
    }

    public void menu() {
        menuText();
        int option = getOption();
        while (option != 0) {

            switch (option) {
                case 1 -> addNote();
                case 2 -> listAllNotes();
                case 3 -> listSpecificNote();
                case 4 -> deleteNote();
            }
            menuText();
            option = getOption();
        }
        noteController.saveDataToFile();
    }

    private void deleteNote() {
        Scanner console = new Scanner(System.in);
        System.out.println("\nEnter Note Title");
        String title = console.nextLine();
        Note note = noteController.delete(title);
        System.out.println("Deleted note " + note.getTitle() + "!");
    }

    private void listSpecificNote() {
        Scanner console = new Scanner(System.in);
        System.out.println("\nEnter Note Title");
        String title = console.nextLine();
        Note note = noteController.findOne(title);
        if (note != null)
            System.out.println(note);
        else System.err.println("Note not found!");
    }

    private void listAllNotes() {
        noteController.findAll().forEach(note -> System.out.println(note.getTitle()));
    }

    private void addNote() {
        Scanner console = new Scanner(System.in);
        System.out.println("\nEnter note title");
        String title = console.nextLine();
        System.out.println("\nEnter note content");
        String content = console.nextLine();
        Note note = new Note(title, content);
        note = noteController.save(note);
        if (note != null)
            System.out.println("Saved note " + note.getTitle() + "!");

    }


    private void inputMenuCheck(int option) throws InputMenuException {
        if (option > 4 || option < 0)
            throw new InputMenuException("Input should be between 0 and 3");
    }

    private int getOption() {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter option : ");
        int option = 0;
        try {
            option = console.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("InputMismatchException enter an integer");
            option = 4;
        } finally {
            try {
                inputMenuCheck(option);
            } catch (InputMenuException e) {
                e.printStackTrace();
            }
        }
        return option;
    }
}
