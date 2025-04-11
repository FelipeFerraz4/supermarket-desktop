package services;

import interfaces.IRepository;
import model.people.Employee;
import model.people.Client;
import model.people.Person;

import java.time.LocalDate;

public class PersonServices {

    public static void RegistreGuest(
            String name,
            String cpf,
            LocalDate birthDate,
            String email,
            String password,
            String phone,
            LocalDate accountCreationDate,
            LocalDate dateLastPurchase,
            IRepository<Person> repository) {
        Client client = new Client(name, cpf, birthDate, email, password, phone, accountCreationDate, dateLastPurchase);
        repository.add(client);
    }

    public static void RegistreEmployee(
            String name,
            String cpf,
            LocalDate birthDate,
            String email,
            String password,
            String phone,
            String position,
            double salary,
            LocalDate hireDate,
            IRepository<Person> repository) {
        Employee employee = new Employee(name, cpf, birthDate, email, password, phone, position, salary, hireDate);
        repository.add(employee);
    }
}