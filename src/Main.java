import view.console.ConsoleMenu;
import view.swing.SwingMenu;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha a interface:");
        System.out.println("1 - Console");
        System.out.println("2 - Swing (Interface Gráfica)");
        System.out.print("Opção: ");
        int option = Integer.parseInt(scanner.nextLine());

        if (option == 1) {
            ConsoleMenu menu = new ConsoleMenu();
            menu.start(scanner);
        } else if (option == 2) {
            openSwing();
        } else {
            System.out.println("Opção inválida.");
        }

        scanner.close();
    }

    private static void openSwing() {
        // Java recomenda que toda criação ou atualização de telas
        // Swing seja feita dentro da Event Dispatch Thread (EDT) para evitar bugs

        // Cria a janela Swing de forma segura na thread correta EDT
        SwingUtilities.invokeLater(SwingMenu::display);

        //Mesmo que usar isso
        //SwingUtilities.invokeLater(() -> {
        //      SwingMenu.exibir();
        //});
    }
}
