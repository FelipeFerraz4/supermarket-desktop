package services;

import interfaces.IRepository;
import model.people.Person;

public class AuthServices {

    public static Person login(String email, String password, IRepository<Person> repository) {
        for (Person person : repository.search()) {
            if (person.getEmail().equalsIgnoreCase(email) &&
                    person.getPassword().equals(password)) {
                return person;
            }
        }
        return null;
    }
}
