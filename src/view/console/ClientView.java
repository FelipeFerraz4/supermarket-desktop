package view.console;

import controllers.PersonController;
import controllers.ProductController;

import java.util.Scanner;

public class ClientView {

    public static void menu(Scanner scanner, PersonController personController, ProductController productController) {
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