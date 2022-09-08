package dan.rares.repository;

public interface ICrudRepository<E> {

    E save(E entity);

    Iterable<E> findAll();

    E delete(String title);

    E update(E entity);

    E findOne(String title);
}
