package services;

import interfaces.IRepository;
import model.people.Employee;
import model.people.Client;
import model.people.Person;

import java.time.LocalDate;
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

    public static void updateClient(UUID id, String phone, IRepository<Person> repository){
        Client client = (Client) repository.searchById(id);
        client.setPhone(phone);
        repository.update(client);
    }

    public static void updateEmployee(UUID id, String phone, String position, double salary, IRepository<Person> repository) {
        Employee employee = (Employee) repository.searchById(id);
        if (phone != null) {
            employee.setPhone(phone);
        }
        if (position != null) {
            employee.setPosition(position);
        }
        if (salary != 0) {
            employee.setSalary(salary);
        }

        repository.update(employee);
    }
}