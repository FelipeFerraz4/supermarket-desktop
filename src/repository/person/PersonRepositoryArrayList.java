package repository.person;

import exceptions.DuplicateEntityException;
import exceptions.EntityNotFoundException;
import interfaces.IPersonRepository;
import model.people.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonRepositoryArrayList implements IPersonRepository {
    private final List<Person> people = new ArrayList<>();

    @Override
    public void add(Person person) throws IllegalArgumentException, DuplicateEntityException {
        if (person == null) {
            throw new IllegalArgumentException("A pessoa não pode ser nula.");
        }
        if (searchById(person.getId()) != null) {
            throw new DuplicateEntityException("Pessoa com ID já existe: " + person.getId());
        }
        people.add(person);
    }

    @Override
    public Person searchById(UUID id) throws IllegalArgumentException, EntityNotFoundException {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo.");
        for (Person person : people) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        throw new EntityNotFoundException("Pessoa com ID " + id + " não encontrada.");
    }

    @Override
    public Person searchByName(String name) throws IllegalArgumentException, EntityNotFoundException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        for (Person person : people) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        throw new EntityNotFoundException("Pessoa com nome " + name + " não encontrada.");
    }

    @Override
    public Person searchByEmail(String email) throws IllegalArgumentException, EntityNotFoundException {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email inválido.");
        }
        for (Person person : people) {
            if (person.getEmail().equalsIgnoreCase(email)) {
                return person;
            }
        }
        throw new EntityNotFoundException("Pessoa com email " + email + " não encontrada.");
    }

    @Override
    public Person searchByCpf(String cpf) throws IllegalArgumentException, EntityNotFoundException {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        for (Person person : people) {
            if (person.getCpf().equals(cpf)) {
                return person;
            }
        }
        throw new EntityNotFoundException("Pessoa com CPF " + cpf + " não encontrada.");
    }

    @Override
    public void update(Person updatedPerson) throws IllegalArgumentException, EntityNotFoundException {
        if (updatedPerson == null) {
            throw new IllegalArgumentException("Pessoa não pode ser nula.");
        }
        boolean found = false;
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId().equals(updatedPerson.getId())) {
                people.set(i, updatedPerson);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new EntityNotFoundException("Pessoa com ID " + updatedPerson.getId() + " não encontrada para atualização.");
        }
    }

    @Override
    public void delete(UUID id) throws IllegalArgumentException, EntityNotFoundException {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo.");
        boolean found = false;
        for (Person person : people) {
            if (person.getId().equals(id)) {
                person.setStatus(false);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new EntityNotFoundException("Pessoa com ID " + id + " não encontrada para exclusão.");
        }
    }

    @Override
    public List<Person> getAll() {
        return new ArrayList<>(people);
    }

    @Override
    public List<Person> getByType(Class<?> clazz) throws IllegalArgumentException {
        if (clazz == null) throw new IllegalArgumentException("Classe não pode ser nula.");
        List<Person> result = new ArrayList<>();
        for (Person person : people) {
            if (clazz.isInstance(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
