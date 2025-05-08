package interfaces;

import java.util.List;
import model.people.Person;

public interface IPersonRepository {
    Person searchByName(String name) throws IllegalArgumentException;
    Person searchByEmail(String email) throws IllegalArgumentException;
    Person searchByCpf(String cpf) throws IllegalArgumentException;
    List<Person> getByType(Class<?> clazz) throws IllegalArgumentException;
}