package controllers;

import interfaces.IPersonRepository;
import model.people.Client;
import model.people.Person;
import model.people.Employee;
import repository.person.PersonRepositoryHashMap;
import services.AuthServices;
import services.PersonServices;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PersonController {
    private final IPersonRepository repository;

    public PersonController() {
        this.repository = new PersonRepositoryHashMap();
        this.initializePeople();
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
        PersonServices.RegisterClient(name, cpf, birthDate, email, password, phone, accountCreationDate, dateLastPurchase, repository);
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
        PersonServices.RegisterEmployee(name, cpf, birthDate, email, password, phone, position, salary, hireDate, repository);
    }

    public List<Person> listAll() {
        return repository.getAll();
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

    public void updateEmployee(UUID id, String phone, String position, double salary, String email, String password) {
        PersonServices.updateEmployee(id, phone, position, salary, email, password, repository);
    }


    public void updateClient(UUID id, String phone, String email, String password) {
        PersonServices.updateClient(id, phone, email, password, repository);
    }

    public void updateClientCart(UUID id, Map<UUID, Double> cart) {
        PersonServices.updateClientCart(id, cart, repository);
    }

    public void addItemToClientCart(UUID id, UUID productId, double price) {
        PersonServices.addItemToClientCart(id, productId, price, repository);
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

    public Map<UUID, Double> getClientCart(UUID uuid) {
        return PersonServices.getClientCart(uuid, repository);
    }

    public void initializePeople() {
        registerEmployee(
                "João Silva",
                "11122233344",
                LocalDate.parse("1985-03-10"),
                "joao@empresa.com",
                "123",
                "85999990000",
                "Vendedor",
                3000.0,
                LocalDate.parse("2020-02-15")
        );

        registerEmployee(
                "Maria Oliveira",
                "55566677788",
                LocalDate.parse("1990-07-25"),
                "maria@empresa.com",
                "123",
                "85988887777",
                "Caixa",
                2800.0,
                LocalDate.parse("2021-08-10")
        );
        Employee admin = new Employee(
                "admin",
                "99988833300",
                LocalDate.parse("2000-01-01"),
                "admin@gmail.com",
                "1234",
                "88999998888",
                "Gerente",
                15000,
                LocalDate.parse("2015-06-06"));
        this.repository.add(admin);

        registerClient(
                "Carlos Souza",
                "99988877766",
                LocalDate.parse("1995-05-20"),
                "carlos@gmail.com",
                "123",
                "85977776666",
                LocalDate.now().minusYears(1), // Conta criada há 1 ano
                LocalDate.now().minusMonths(2) // Última compra há 2 meses
        );

        registerClient(
                "Ana Paula",
                "44455566677",
                LocalDate.parse("1998-11-15"),
                "ana@gmail.com",
                "123",
                "85966665555",
                LocalDate.now().minusMonths(6), // Conta criada há 6 meses
                LocalDate.now().minusDays(10) // Última compra há 10 dias
        );
    }

}
