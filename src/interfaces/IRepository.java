package interfaces;

import java.util.List;
import java.util.UUID;

public interface IRepository<T> {
    void add(T object);
    List<T> search();
    T searchById(UUID id);
    void update(T object);
    void delete(UUID id);
}