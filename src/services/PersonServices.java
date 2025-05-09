package services;

import exceptions.DuplicateEntityException;
import exceptions.EntityNotFoundException;
import interfaces.IRepository;
import model.people.Employee;
import model.people.Client;
import model.people.Person;
import repository.person.PersonRepositoryHashMap;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class PersonServices {

    public static void RegisterClient(
            String name,
            String cpf,
            LocalDate birthDate,
            String email,
            String password,
            String phone,
            LocalDate accountCreationDate,
            LocalDate dateLastPurchase,
            IRepository<Person> repository) throws IllegalArgumentException, DuplicateEntityException {

        if (repository == null) throw new IllegalArgumentException("Repository cannot be null.");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank.");
        if (cpf == null || cpf.isBlank()) throw new IllegalArgumentException("CPF cannot be null or blank.");
        if (birthDate == null) throw new IllegalArgumentException("Birth date cannot be null.");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email cannot be null or blank.");
        if (password == null || password.isBlank()) throw new IllegalArgumentException("Password cannot be null or blank.");
        if (phone == null || phone.isBlank()) throw new IllegalArgumentException("Phone cannot be null or blank.");
        if (accountCreationDate == null) throw new IllegalArgumentException("Account creation date cannot be null.");
        if (dateLastPurchase == null) throw new IllegalArgumentException("Last purchase date cannot be null.");

        Client client = new Client(name, cpf, birthDate, email, password, phone, accountCreationDate, dateLastPurchase);
        repository.add(client);
    }

    public static void RegisterEmployee(
            String name,
            String cpf,
            LocalDate birthDate,
            String email,
            String password,
            String phone,
            String position,
            double salary,
            LocalDate hireDate,
            IRepository<Person> repository) throws IllegalArgumentException, DuplicateEntityException {

        if (repository == null) throw new IllegalArgumentException("Repository cannot be null.");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank.");
        if (cpf == null || cpf.isBlank()) throw new IllegalArgumentException("CPF cannot be null or blank.");
        if (birthDate == null) throw new IllegalArgumentException("Birth date cannot be null.");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email cannot be null or blank.");
        if (password == null || password.isBlank()) throw new IllegalArgumentException("Password cannot be null or blank.");
        if (phone == null || phone.isBlank()) throw new IllegalArgumentException("Phone cannot be null or blank.");
        if (position == null || position.isBlank()) throw new IllegalArgumentException("Position cannot be null or blank.");
        if (salary <= 0) throw new IllegalArgumentException("Salary must be greater than 0.");
        if (hireDate == null) throw new IllegalArgumentException("Hire date cannot be null.");

        Employee employee = new Employee(name, cpf, birthDate, email, password, phone, position, salary, hireDate);
        repository.add(employee);
    }

    public static void updateClient(UUID id, String phone, String email, String password, IRepository<Person> repository)
            throws IllegalArgumentException, EntityNotFoundException {
        if (repository == null) throw new IllegalArgumentException("Repository cannot be null.");
        if (id == null) throw new IllegalArgumentException("Client ID cannot be null.");

        Client client = (Client) repository.searchById(id);
        if (client == null) throw new IllegalArgumentException("Client not found with given ID.");

        if (phone != null && !phone.isBlank()) {
            client.setPhone(phone);
        }
        if (email != null && !email.isBlank()) {
            client.setEmail(email);
        }
        if (password != null && !password.isBlank()) {
            client.setPassword(password);
        }

        repository.update(client);
    }

    public static void updateEmployee(UUID id, String phone, String position, double salary, String email,
                                      String password, IRepository<Person> repository) throws IllegalArgumentException, EntityNotFoundException {
        if (repository == null) throw new IllegalArgumentException("Repository cannot be null.");
        if (id == null) throw new IllegalArgumentException("Employee ID cannot be null.");

        Employee employee = (Employee) repository.searchById(id);
        if (employee == null) throw new IllegalArgumentException("Employee not found with given ID.");

        if (phone != null && !phone.isBlank()) {
            employee.setPhone(phone);
        }
        if (position != null && !position.isBlank()) {
            employee.setPosition(position);
        }
        if (salary > 0) {
            employee.setSalary(salary);
        }
        if (email != null && !email.isBlank()) {
            employee.setEmail(email);
        }
        if (password != null && !password.isBlank()) {
            employee.setPassword(password);
        }

        repository.update(employee);
    }

    public static void updateClientCart(UUID id, Map<UUID, Double> cart, PersonRepositoryHashMap repository)
                throws IllegalArgumentException, EntityNotFoundException {
        if (repository == null) throw new IllegalArgumentException("Repository cannot be null.");
        if (id == null) throw new IllegalArgumentException("Client ID cannot be null.");
        if (cart == null) throw new IllegalArgumentException("Cart cannot be null.");

        Client client = (Client) repository.searchById(id);
        if (client == null) throw new IllegalArgumentException("Client not found with given ID.");

        client.setCart(cart);
        repository.update(client);
    }

    public static void addItemToClientCart(UUID id, UUID productId, double price, PersonRepositoryHashMap repository)
            throws IllegalArgumentException, EntityNotFoundException {
        if (repository == null) throw new IllegalArgumentException("Repository cannot be null.");
        if (id == null) throw new IllegalArgumentException("Client ID cannot be null.");
        if (productId == null) throw new IllegalArgumentException("Product ID cannot be null.");
        if (price <= 0) throw new IllegalArgumentException("Price must be greater than 0.");

        Client client = (Client) repository.searchById(id);
        if (client == null) throw new IllegalArgumentException("Client not found with given ID.");

        client.addToCart(productId, price);
        repository.update(client);
    }

    public static Map<UUID, Double> getClientCart(UUID uuid, PersonRepositoryHashMap repository) throws IllegalArgumentException {
        if (repository == null) throw new IllegalArgumentException("Repository cannot be null.");
        if (uuid == null) throw new IllegalArgumentException("Client ID cannot be null.");

        Client client = (Client) repository.searchById(uuid);
        if (client == null) throw new IllegalArgumentException("Client not found with given ID.");

        return client.getCart();
    }
}
