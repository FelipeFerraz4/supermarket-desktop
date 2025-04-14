package view.console;

import controllers.PersonControllers;
import model.people.Person;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final PersonControllers controller = new PersonControllers();
    private final ClientView clientView = new ClientView(scanner, controller);
    private final EmployeeView employeeView = new EmployeeView(scanner, controller);

    public void start() {
        int option;
        do {
            System.out.println("\n==== SISTEMA DE SUPERMERCADO ====");
            System.out.println("1. Faça suas compras aqui");
            System.out.println("1. Faça login e ganhe ponto para trocar por descontos");
            System.out.println("2. Crie sua conta aqui");
            System.out.println("0. Sair");
            System.out.print("Escolha a opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> System.out.println("Lista de produtos");
                case 2 -> login();
                case 3 -> registerClient();
                case 0 -> System.out.println("Saindo do sistema. Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);
    }

    private void login() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        Person user = controller.login(email, password);
        if (user == null) {
            System.out.println("Credenciais inválidas.");
            return;
        }

        System.out.println("Login realizado com sucesso! Bem-vindo(a), " + user.getName() + ".");

        if (user instanceof model.people.Client) {
            clientView.menu();
        } else if (user instanceof model.people.Employee) {
            employeeView.menu();
        } else {
            System.out.println("Tipo de usuário desconhecido.");
        }
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
}