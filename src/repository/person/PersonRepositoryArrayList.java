package repository.person;

import exceptions.DuplicateEntityException;
import exceptions.EntityNotFoundException;
import interfaces.IPersonRepository;
import interfaces.IRepository;
import model.people.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonRepositoryArrayList implements IRepository<Person>, IPersonRepository {
    private final List<Person> people = new ArrayList<>();

    @Override
    public void add(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("A pessoa não pode ser nula.");
        }
        if (searchById(person.getId()) != null) {
            throw new DuplicateEntityException("Pessoa com ID já existe: " + person.getId());
        }
        people.add(person);
    }

    @Override
    public List<Person> search() {
        return new ArrayList<>(people);
    }

    @Override
    public Person searchById(UUID id) {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo.");
        for (Person person : people) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person searchByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        for (Person person : people) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person searchByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email inválido.");
        }
        for (Person person : people) {
            if (person.getEmail().equalsIgnoreCase(email)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person searchByCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        for (Person person : people) {
            if (person.getCpf().equals(cpf)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public void update(Person updatedPerson) {
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
    public void delete(UUID id) {
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
    public List<Person> getByType(Class<?> clazz) {
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
