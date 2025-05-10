package view.console;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleMenu {

    public void start(Scanner scanner, PersonController personController, ProductController productController) {
        int option = -1;
        do {
            try {
                System.out.println("\n==== SISTEMA DE SUPERMERCADO ====");
                System.out.println("1. Faça suas compras aqui");
                System.out.println("2. Faça login e ganhe ponto para trocar por descontos");
                System.out.println("3. Crie sua conta aqui");
                System.out.println("0. Sair");
                System.out.print("Escolha a opção: ");
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1 -> ClientView.menu(scanner, personController, productController, null);
                    case 2 -> login(scanner, personController, productController);
                    case 3 -> registerClient(scanner, personController, productController);
                    case 0 -> System.out.println("Saindo do sistema. Até logo!");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (option != 0);
    }

    private void login(Scanner scanner, PersonController personController, ProductController productController) {
        try {
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Senha: ");
            String password = scanner.nextLine();

            Person user = personController.login(email, password);
            if (user == null) {
                System.out.println("Credenciais inválidas.");
                return;
            }

            System.out.println("Login realizado com sucesso! Bem-vindo(a), " + user.getName() + ".");

            if (user instanceof model.people.Client) {
                ClientView.menu(scanner, personController, productController, user);
            } else if (user instanceof model.people.Employee) {
                EmployeeView.menu(scanner, personController, productController);
            } else {
                System.out.println("Tipo de usuário desconhecido.");
            }
        } catch (IllegalArgumentException e) {
                System.out.println("E-mail e senhas invalidos");
        } catch (Exception e) {
            System.out.println("Erro ao realizar login: " + e.getMessage());
        }
    }

    private void registerClient(Scanner scanner, PersonController personController, ProductController productController) {
        try {
            System.out.print("Nome: ");
            String name = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Data de nascimento (AAAA-MM-DD): ");
            String birthInput = scanner.nextLine();
            LocalDate birthDate;
            try {
                birthDate = LocalDate.parse(birthInput);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato AAAA-MM-DD.");
                return;
            }

            System.out.print("Telefone: ");
            String phone = scanner.nextLine();

            System.out.print("E-mail: ");
            String email = scanner.nextLine();

            System.out.print("Senha: ");
            String password = scanner.nextLine();

            LocalDate accountCreationDate = LocalDate.now();
            LocalDate dateLastPurchase = LocalDate.now();

            personController.registerClient(name, cpf, birthDate, email, password, phone, accountCreationDate, dateLastPurchase);
            System.out.println("Cliente cadastrado com sucesso!");

            ClientView.menu(scanner, personController, productController, personController.findByName(name));
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
}
