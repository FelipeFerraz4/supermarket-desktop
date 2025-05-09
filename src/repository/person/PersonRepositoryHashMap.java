package repository.person;

import exceptions.DuplicateEntityException;
import exceptions.EntityNotFoundException;
import interfaces.IPersonRepository;
import model.people.Person;

import java.util.*;

public class PersonRepositoryHashMap implements IPersonRepository {
    private final Map<UUID, Person> storage = new HashMap<>();

    @Override
    public void add(Person person) throws IllegalArgumentException, DuplicateEntityException {
        if (person == null) throw new IllegalArgumentException("Person cannot be null.");
        if (person.getId() == null) throw new IllegalArgumentException("Person ID cannot be null.");
        if (storage.containsKey(person.getId())) {
            throw new DuplicateEntityException("A person with ID " + person.getId() + " already exists.");
        }
        storage.put(person.getId(), person);
    }

    @Override
    public Person searchById(UUID id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("ID cannot be null.");
        return storage.get(id);
    }

    @Override
    public Person searchByName(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank.");
        for (Person person : storage.values()) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person searchByEmail(String email) throws IllegalArgumentException {
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email cannot be null or blank.");
        for (Person person : storage.values()) {
            if (person.getEmail().equalsIgnoreCase(email)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person searchByCpf(String cpf) throws IllegalArgumentException {
        if (cpf == null || cpf.isBlank()) throw new IllegalArgumentException("CPF cannot be null or blank.");
        for (Person person : storage.values()) {
            if (person.getCpf().equals(cpf)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public void update(Person person) throws IllegalArgumentException, EntityNotFoundException {
        if (person == null) throw new IllegalArgumentException("Person cannot be null.");
        UUID id = person.getId();
        if (id == null) throw new IllegalArgumentException("Person ID cannot be null.");
        if (!storage.containsKey(id)) {
            throw new EntityNotFoundException("Person with ID " + id + " not found for update.");
        }
        storage.put(id, person);
    }

    @Override
    public void delete(UUID id) throws IllegalArgumentException, EntityNotFoundException {
        if (id == null) throw new IllegalArgumentException("ID cannot be null.");
        Person person = storage.get(id);
        if (person == null) {
            throw new EntityNotFoundException("Person with ID " + id + " not found for deletion.");
        }
        person.setStatus(false);
    }

    @Override
    public List<Person> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Person> getByType(Class<?> clazz) throws IllegalArgumentException{
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
