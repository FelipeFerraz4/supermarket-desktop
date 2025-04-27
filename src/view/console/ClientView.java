package view.console;

import controllers.PersonController;
import controllers.ProductController;
import model.products.Product;
import model.products.Utensil;
import model.products.food.Beverage;
import model.products.food.Fruit;
import model.products.food.Meat;
import model.products.HygieneProduct;
import model.products.food.ProcessedFood;

import java.util.*;

public class ClientView {

    public static void menu(Scanner scanner, PersonController personController, ProductController productController) {
        Map<UUID, Double> cart = new HashMap<>();

        int option;
        do {
            System.out.println("\n--- MENU DE CLIENTE ---");
            System.out.println("1. Adicionar produto ao carrinho");
            System.out.println("2. Ver carrinho");
            System.out.println("3. Finalizar compra");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (option) {
                case 1 -> addProductByType(scanner, productController, cart);
                case 2 -> viewCart(cart, productController);
                case 3 -> finalizePurchase(cart, productController);
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);
    }

    private static void addProductByType(Scanner scanner, ProductController productController, Map<UUID, Double> cart) {
        while (true) {
            System.out.println("\n--- Escolha o tipo de produto ---");
            System.out.println("1. Bebidas");
            System.out.println("2. Carnes");
            System.out.println("3. Frutas");
            System.out.println("4. Alimentos Processados");
            System.out.println("5. Produtos de Higiene");
            System.out.println("6. Utensílios");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            int type = Integer.parseInt(scanner.nextLine());

            List<Product> filteredProducts = new ArrayList<>();

            switch (type) {
                case 1 -> {
                    filteredProducts = productController.getProductsByCategory(Beverage.class);
                    System.out.println("\n--- Lista de Bebidas ---");
                }
                case 2 -> {
                    filteredProducts = productController.getProductsByCategory(Meat.class);
                    System.out.println("\n--- Lista de Carnes ---");
                }
                case 3 -> {
                    filteredProducts = productController.getProductsByCategory(Fruit.class);
                    System.out.println("\n--- Lista de Frutas ---");
                }
                case 4 -> {
                    filteredProducts = productController.getProductsByCategory(ProcessedFood.class);
                    System.out.println("\n--- Lista de Alimentos Processados ---");
                }
                case 5 -> {
                    filteredProducts = productController.getProductsByCategory(HygieneProduct.class);
                    System.out.println("\n--- Lista de Produtos de Higiene ---");
                }
                case 6 -> {
                    filteredProducts = productController.getProductsByCategory(Utensil.class);
                    System.out.println("\n--- Lista de Utensílios ---");
                }
                case 0 -> { return; }
                default -> {
                    System.out.println("Opção inválida.");
                    continue;
                }
            }

            for (Product p : filteredProducts) {
                System.out.println(p);
            }

            System.out.println("\nDeseja adicionar algum produto?");
            System.out.println("1. Sim");
            System.out.println("0. Voltar");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                continue;
            }

            System.out.print("Digite o Código do produto: ");
            String cod = scanner.nextLine();
            try {
                Product product = productController.searchByCod(cod);
                if (product == null) {
                    System.out.println("Produto não encontrado.");
                    continue;
                }

                if (type == 2) {
                    System.out.print("Digite o peso desejado (em kg): ");
                    double weight = scanner.nextDouble();
                    scanner.nextLine();


                    cart.put(product.getId(), weight);
                } else {
                    System.out.print("Digite a quantidade: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    cart.put(product.getId(), (double) quantity);
                }

                System.out.println("Produto adicionado ao carrinho!");

            } catch (IllegalArgumentException e) {
                System.out.println("ID inválido.");
            }
        }
    }

    private static void viewCart(Map<UUID, Double> cart, ProductController productController) {
        System.out.println("\n--- Carrinho de Compras ---");
        if (cart.isEmpty()) {
            System.out.println("Carrinho vazio.");
            return;
        }

        double total = 0;
        for (Map.Entry<UUID, Double> entry : cart.entrySet()) {
            UUID id = entry.getKey();
            double quantityOrWeight = entry.getValue();

            Product product = productController.searchById(id);

            if (product == null) {
                System.out.println("Produto com ID " + id + " não encontrado.");
                continue;
            }

            if (product instanceof Meat) {
                System.out.printf("%s - %.2f kg - R$ %.2f\n",
                        product.getName(),
                        quantityOrWeight,
                        product.getPrice() * quantityOrWeight);
            } else {
                System.out.printf("%s - %d unidades - R$ %.2f\n",
                        product.getName(),
                        (int) quantityOrWeight,
                        product.getPrice() * quantityOrWeight);
            }

            total += product.getPrice() * quantityOrWeight;
        }

        System.out.printf("Total: R$ %.2f\n", total);
    }


    private static void finalizePurchase(Map<UUID, Double> cart, ProductController productController) {
        System.out.println("\n--- Finalizando Compra ---");
        viewCart(cart, productController);
        System.out.println("Compra finalizada com sucesso!");
        cart.clear();
    }
}
