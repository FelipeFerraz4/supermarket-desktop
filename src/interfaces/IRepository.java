package interfaces;

import exceptions.DuplicateEntityException;
import exceptions.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface IRepository<T> {
    void add(T object) throws IllegalArgumentException, DuplicateEntityException;
    T searchById(UUID id) throws IllegalArgumentException, EntityNotFoundException;
    void update(T object) throws IllegalArgumentException, EntityNotFoundException;
    void delete(UUID id) throws IllegalArgumentException, EntityNotFoundException;
    List<T> getAll();
}