package repository.person;

import interfaces.IPersonRepository;
import interfaces.IRepository;
import model.people.Person;

import java.util.*;

public class PersonRepositoryHashMap implements IRepository<Person>, IPersonRepository {
    private final Map<UUID, Person> storage = new HashMap<>();

    @Override
    public void add(Person person) {
        storage.put(person.getId(), person);
    }

    @Override
    public List<Person> search() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Person searchById(UUID id) {
        return storage.get(id);
    }

    @Override
    public Person searchByName(String name) {
        for (Person person : storage.values()) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person searchByEmail(String email) {
        for (Person person : storage.values()) {
            if (person.getEmail().equalsIgnoreCase(email)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person searchByCpf(String cpf) {
        for (Person person : storage.values()) {
            if (person.getCpf().equals(cpf)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public void update(Person person) {
        UUID id = person.getId();
        if (storage.containsKey(id)) {
            storage.put(id, person);
        }
    }

    @Override
    public void delete(UUID id) {
        Person person = storage.get(id);
        if (person != null) {
            person.setStatus(false);
        }
    }

    @Override
    public List<Person> getByType(Class<?> clazz) {
        List<Person> result = new ArrayList<>();
        for (Person p : storage.values()) {
            if (clazz.isInstance(p)) {
                result.add(p);
            }
        }
        return result;
    }
}