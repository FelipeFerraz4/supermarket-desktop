package services;

import interfaces.IRepository;
import model.people.Person;

public class AuthServices {

    public static Person login(String email, String password, IRepository<Person> repository) throws IllegalArgumentException {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank.");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank.");
        }

        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null.");
        }

        for (Person person : repository.getAll()) {
            if (person.getEmail() != null &&
                    person.getPassword() != null &&
                    person.getEmail().equalsIgnoreCase(email) &&
                    person.getPassword().equals(password)) {
                return person;
            }
        }

        return null;
    }
}
