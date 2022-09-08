package dan.rares.repository;

import dan.rares.model.Note;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NoteFileRepository implements FileRepository<Note> {
    private static FileWriter file;

    @Override
    public void writeData(Iterable<Note> list) {
        JSONObject object = new JSONObject();
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        ObjectMapper mapper = new ObjectMapper(jsonFactory);
        try {
            file = new FileWriter("notes.json");
            JSONArray notes = new JSONArray();
            list.forEach(notes::add);
            object.put("Notes", notes);
            mapper.writeValue(file, object);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Note> loadData() throws IOException {
        List<Note> notes = new ArrayList<>();
        Reader reader = new BufferedReader(new FileReader("notes.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode i:parser.path("Notes")){
            Note n =new Note(i.path("title").asText(),i.path("content").asText());
            notes.add(n);
        }
        reader.close();

        return notes;
    }
}
