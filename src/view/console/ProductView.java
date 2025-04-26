package view.console;

import controllers.ProductControllers;
import model.products.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ProductView {
    private final Scanner scanner;
    private final ProductControllers controller;

    public ProductView(Scanner scanner, ProductControllers controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public void menu() {
        int option;
        do {
            System.out.println("\n=== MENU DE PRODUTOS ===");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Atualizar produto");
            System.out.println("3. Listar todos os produtos");
            System.out.println("4. Buscar produto por nome");
            System.out.println("5. Deletar produto");
            System.out.println("0. Voltar");
            System.out.print("Escolha a opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> registerProduct();
                case 2 -> updateProduct();
                case 3 -> listProducts();
                case 4 -> searchProductByName();
                case 5 -> deleteProduct();
                case 0 -> System.out.println("Voltando ao menu anterior...");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);
    }

    private UUID findProduct() {
        System.out.println("Como deseja buscar o produto?");
        System.out.println("1 - Por nome");
        System.out.println("2 - Por código");
        System.out.print("Escolha uma opção: ");

        String option = scanner.nextLine();
        UUID id = null;

        switch (option) {
            case "1":
                System.out.print("Nome do produto: ");
                String name = scanner.nextLine();
                var product = controller.searchByName(name);
                if (product != null) {
                    id = product.getId();
                }
                break;

            case "2":
                System.out.print("Código do produto: ");
                String cod = scanner.nextLine();
                product = controller.searchByCod(cod);
                if (product != null) {
                    id = product.getId();
                }
                break;

            default:
                System.out.println("Opção inválida.");
                return null;
        }

        if (id != null) {
            return id;
        } else {
            System.out.println("Produto não encontrado ou inativo.");
            return null;
        }
    }


    private void updateProduct() {
        UUID id = findProduct();


    }

    private void listProducts() {
        List<Product> products = controller.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            products.forEach(System.out::println);
        }
    }

    private void searchProductByName() {
        System.out.print("Nome do produto: ");
        String name = scanner.nextLine();
        Product product = controller.searchByName(name);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void deleteProduct() {
        UUID id = findProduct();
        if (id == null) return;

        controller.deleteProduct(id);
        System.out.println("Produto removido com sucesso!");
    }

}
