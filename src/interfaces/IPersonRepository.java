package interfaces;

import java.util.List;

import exceptions.EntityNotFoundException;
import model.people.Person;

public interface IPersonRepository extends IRepository<Person> {
    Person searchByName(String name) throws IllegalArgumentException, EntityNotFoundException;
    Person searchByEmail(String email) throws IllegalArgumentException, EntityNotFoundException;
    Person searchByCpf(String cpf) throws IllegalArgumentException, EntityNotFoundException;
    List<Person> getByType(Class<?> clazz) throws IllegalArgumentException;
}