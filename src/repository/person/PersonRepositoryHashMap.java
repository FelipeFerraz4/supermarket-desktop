package repository.person;

import interfaces.IPersonRepository;
import interfaces.IRepository;
import model.people.Person;

import java.util.*;

public class PersonRepositoryHashMap implements IRepository<Person>, IPersonRepository {
    private final Map<UUID, Person> storage = new HashMap<>();

    @Override
    public void add(Person person) {
        if (person == null) throw new IllegalArgumentException("Person cannot be null.");
        if (person.getId() == null) throw new IllegalArgumentException("Person ID cannot be null.");
        if (storage.containsKey(person.getId())) {
            throw new IllegalStateException("A person with ID " + person.getId() + " already exists.");
        }
        storage.put(person.getId(), person);
    }

    @Override
    public List<Person> search() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Person searchById(UUID id) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null.");
        return storage.get(id);
    }

    @Override
    public Person searchByName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank.");
        for (Person person : storage.values()) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person searchByEmail(String email) {
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email cannot be null or blank.");
        for (Person person : storage.values()) {
            if (person.getEmail().equalsIgnoreCase(email)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person searchByCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) throw new IllegalArgumentException("CPF cannot be null or blank.");
        for (Person person : storage.values()) {
            if (person.getCpf().equals(cpf)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public void update(Person person) {
        if (person == null) throw new IllegalArgumentException("Person cannot be null.");
        UUID id = person.getId();
        if (id == null) throw new IllegalArgumentException("Person ID cannot be null.");
        if (!storage.containsKey(id)) {
            throw new NoSuchElementException("Person with ID " + id + " not found for update.");
        }
        storage.put(id, person);
    }

    @Override
    public void delete(UUID id) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null.");
        Person person = storage.get(id);
        if (person == null) {
            throw new NoSuchElementException("Person with ID " + id + " not found for deletion.");
        }
        person.setStatus(false);
    }

    @Override
    public List<Person> getByType(Class<?> clazz) {
        if (clazz == null) throw new IllegalArgumentException("Type cannot be null.");
        List<Person> result = new ArrayList<>();
        for (Person p : storage.values()) {
            if (clazz.isInstance(p)) {
                result.add(p);
            }
        }
        return result;
    }
}
