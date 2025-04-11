package interfaces;

import java.util.List;
import model.people.Person;

public interface IPersonRepository {
    Person searchByName(String name);
    Person searchByEmail(String email);
    Person searchByCpf(String cpf);
    List<Person> getByType(Class<?> clazz);
}