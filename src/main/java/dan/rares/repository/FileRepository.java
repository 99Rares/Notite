package dan.rares.repository;

import java.io.IOException;
import java.util.List;

public interface FileRepository<E> {
    void writeData(Iterable<E> list) throws IOException;

    List<E> loadData() throws IOException;
}
