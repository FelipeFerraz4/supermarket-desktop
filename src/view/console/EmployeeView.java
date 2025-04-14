package view.console;

import controllers.PersonControllers;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class EmployeeView {
    private final Scanner scanner;
    private final PersonControllers controller;

    public EmployeeView(Scanner scanner, PersonControllers controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public void menu() {
        int option;
        do {
            System.out.println("\n=== MENU DE FUNCIONÁRIOS ===");
            System.out.println("1. Cadastrar funcionário");
            System.out.println("2. Cadastrar clientes");
            System.out.println("3. Listar funcionários");
            System.out.println("4. Listar clientes");
            System.out.println("5. Atualizar funcionário");
            System.out.println("6. Atualizar cliente");
            System.out.println("7. Buscar pessoa");
            System.out.println("8. Inativar pessoa");
            System.out.println("0. Voltar");
            System.out.print("Escolha a opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> registerEmployee();
                case 2 -> registerClient();
                case 3 -> listEmployees();
                case 4 -> listClients();
                case 5 -> updateEmployee();
                case 6 -> updateClient();
                case 7 -> findPerson();
                case 8 -> deletePerson();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);
    }

    private void registerEmployee() {
        System.out.print("Nome: ");
        String name = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Telefone: ");
        String phone = scanner.nextLine();

        System.out.print("Data de Nascimento (AAAA-MM-DD): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Cargo: ");
        String position = scanner.nextLine();

        System.out.print("Salario: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        LocalDate hireDate = LocalDate.now();

        controller.registerEmployee(name, cpf, birthDate, email, password, phone, position, salary, hireDate);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private void registerClient() {
        System.out.print("Nome: ");
        String name = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Data de nascimento (AAAA-MM-DD): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Telefone: ");
        String phone = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        LocalDate accountCreationDate = LocalDate.now();
        LocalDate dateLastPurchase = LocalDate.now();

        controller.registerClient(name, cpf, birthDate, email, password, phone, accountCreationDate, dateLastPurchase);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void listEmployees() {
        System.out.println("\n--- Lista de Funcionários ---");
        controller.listEmployees().forEach(System.out::println);
    }

    private void listClients() {
        System.out.println("\n--- Lista de Hóspedes ---");
        controller.listClients().forEach(System.out::println);
    }

    private void updateEmployee() {
        UUID id = findPerson();

        System.out.print("Novo telefone: ");
        String phone = scanner.nextLine();

        System.out.print("Novo cargo: ");
        String position = scanner.nextLine();

        System.out.print("Novo salário: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        controller.updateEmployee(id, phone, position, salary);
        System.out.println("Funcionário atualizado com sucesso!");

    }

    private void updateClient() {
        UUID id = findPerson();

        System.out.print("=== Atualizar ===");
        System.out.print("Novo telefone: ");
        String phone = scanner.nextLine();

        controller.updateClient(id, phone);

        System.out.println("Cliente atualizado com sucesso!");
    }

    private UUID findPerson() {
        System.out.println("Como deseja buscar a pessoa?");
        System.out.println("1 - Por nome");
        System.out.println("2 - Por email");
        System.out.println("3 - Por CPF");
        System.out.print("Escolha uma opção: ");

        String option = scanner.nextLine();
        UUID id;

        switch (option) {
            case "1":
                System.out.print("Nome da pessoa: ");
                String name = scanner.nextLine();
                id = controller.findByName(name).getId();
                break;

            case "2":
                System.out.print("Email da pessoa: ");
                String email = scanner.nextLine();
                id = controller.findByEmail(email).getId();
                break;

            case "3":
                System.out.print("CPF da pessoa: ");
                String cpf = scanner.nextLine();
                id = controller.findByCPF(cpf).getId();
                break;

            default:
                System.out.println("Opção inválida.");
                return null;
        }

        if (id != null) {
            return id;
        } else {
            System.out.println("Pessoa não encontrada ou inativa.");
            return null;
        }
    }

    private void deletePerson() {
        UUID id = findPerson();

        controller.deletePerson(id);
        System.out.println("Pessoa inativada com sucesso.");
    }
}