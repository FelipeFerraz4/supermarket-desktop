package services;

import interfaces.IRepository;
import model.people.Employee;
import model.people.Client;
import model.people.Person;
import repository.person.PersonRepositoryHashMap;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class PersonServices {

    public static void RegistreClient(
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

    public static void updateClient(UUID id, String phone, String email, String password, IRepository<Person> repository){
        Client client = (Client) repository.searchById(id);
        if (phone != null) {
            client.setPhone(phone);
        }
        if (email != null) {
            client.setEmail(email);
        }
        if (password != null) {
            client.setPassword(password);
        }
        repository.update(client);
    }

    public static void updateEmployee(UUID id, String phone, String position, double salary, String email, String password, IRepository<Person> repository) {
        Employee employee = (Employee) repository.searchById(id);
        if (phone != null) {
            employee.setPhone(phone);
        }
        if (position != null) {
            employee.setPosition(position);
        }
        if (email != null) {
            employee.setEmail(email);
        }
        if (password != null) {
            employee.setPassword(password);
        }
        if (salary != 0) {
            employee.setSalary(salary);
        }

        repository.update(employee);
    }

    public static void updateClientCart(UUID id, Map<UUID, Double> cart, PersonRepositoryHashMap repository) {
        Client client = (Client) repository.searchById(id);
        if (client != null) {
            client.setCart(cart);
            repository.update(client);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void addItemToClientCart(UUID id, UUID productId, double price, PersonRepositoryHashMap repository) {
        Client client = (Client) repository.searchById(id);
        if (client != null) {
            client.addToCart(productId, price);
            repository.update(client);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static Map<UUID, Double> getClientCart(UUID uuid, PersonRepositoryHashMap repository) {
        Client client = (Client) repository.searchById(uuid);
        if (client != null) {
            return client.getCart();
        } else {
            System.out.println("Cliente não encontrado.");
            return null;
        }
    }
}