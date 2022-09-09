package dan.rares.model;

public class Note {
    private final String title;
    private final String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Title: " + title + '\n' +
                "Content:\n" + content + "\n";
    }
}
