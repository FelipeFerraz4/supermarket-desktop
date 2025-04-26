import view.console.ConsoleMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsoleMenu menu = new ConsoleMenu();
        Scanner scanner = new Scanner(System.in);
        menu.start(scanner);
        scanner.close();
    }
}