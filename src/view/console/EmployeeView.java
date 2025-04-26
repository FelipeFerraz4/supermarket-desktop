package view.console;

import controllers.PersonController;
import controllers.ProductController;

import java.util.Scanner;

public class EmployeeView {

    public static void menu(Scanner scanner, PersonController personController, ProductController productController) {
        int option;
        do {
            System.out.println("\n=== MENU DE PESSOAS ===");
            System.out.println("1. Pessoas");
            System.out.println("2. Produtos");
            System.out.println("0. Voltar");
            System.out.print("Escolha a opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> PersonView.menu(scanner, personController);
                case 2 -> ProductView.menu(scanner, productController);
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);
    }
}
