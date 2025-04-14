package controllers;

import model.people.Client;
import model.people.Person;
import model.people.Employee;
import repository.person.PersonRepositoryHashMap;
import services.AuthServices;
import services.PersonServices;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PersonControllers {
    private final PersonRepositoryHashMap repository;

    public PersonControllers() {
        this.repository = new PersonRepositoryHashMap();
        Employee admin = new Employee(
                "admin",
                "99988833300",
                LocalDate.parse("2000-01-01"),
                "admin@gmail.com",
                "123456",
                "",
                "Gerente",
                15000,
                LocalDate.parse("2015-06-06"));
        this.repository.add(admin);
    }

    public void registerClient(
            String name,
            String cpf,
            LocalDate birthDate,
            String email,
            String password,
            String phone,
            LocalDate accountCreationDate,
            LocalDate dateLastPurchase) {
        PersonServices.RegistreClient(name, cpf, birthDate, email, password, phone, accountCreationDate, dateLastPurchase, repository);
    }

    public void registerEmployee(
            String name,
            String cpf,
            LocalDate birthDate,
            String email,
            String password,
            String phone,
            String position,
            double salary,
            LocalDate hireDate){
        PersonServices.RegistreEmployee(name, cpf, birthDate, email, password, phone, position, salary, hireDate, repository);
    }

    public List<Person> listAll() {
        return repository.search();
    }

    public Person findById(UUID id) {
        return repository.searchById(id);
    }

    public Person findByName(String name){
        return repository.searchByName(name);
    }

    public Person findByCPF(String cpf){
        return repository.searchByCpf(cpf);
    }

    public Person findByEmail(String email){
        return repository.searchByEmail(email);
    }

    public void updateEmployee(UUID id, String phone, String position, double salary) {
        PersonServices.updateEmployee(id, phone, position, salary, repository);
    }

    public void updateClient(UUID id, String phone) {
        PersonServices.updateClient(id, phone, repository);
    }

    public void deletePerson(UUID id) {
        repository.delete(id);
    }

    public List<Person> listClients() {
        return repository.getByType(Client.class);
    }

    public List<Person> listEmployees() {
        return repository.getByType(Employee.class);
    }

    public Person login(String email, String password){
        return AuthServices.login(email, password, repository);
    }
}
