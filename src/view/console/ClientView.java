package view.console;

import controllers.PersonControllers;

import java.util.Scanner;

public class ClientView {
    private final Scanner scanner;
    private final PersonControllers controller;

    public ClientView(Scanner scanner, PersonControllers controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public void menu() {
        int option;
        do {
            System.out.println("\n--- MENU DE CLIENTE ---");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);
    }
}