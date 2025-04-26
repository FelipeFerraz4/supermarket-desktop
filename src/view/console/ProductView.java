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

    public void registerProduct() {
        System.out.println("Qual tipo de produto deseja cadastrar?");
        System.out.println("1 - Bebida");
        System.out.println("2 - Alimento Processado");
        System.out.println("3 - Carne");
        System.out.println("4 - Fruta");
        System.out.println("5 - Produto de Higiene");
        System.out.println("6 - Utensílio");
        System.out.print("Escolha uma opção: ");
        String option = scanner.nextLine();

        System.out.print("Código do produto: ");
        String cod = scanner.nextLine();

        System.out.print("Nome do produto: ");
        String name = scanner.nextLine();

        System.out.print("Preço: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Quantidade: ");
        int amount = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case "1": // Bebida
                System.out.print("Data de validade (AAAA-MM-DD): ");
                LocalDate expirationDate = LocalDate.parse(scanner.nextLine());

                System.out.print("Peso: ");
                double weight = Double.parseDouble(scanner.nextLine());

                System.out.print("Refrigerado (true/false): ");
                boolean refrigerated = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Informações nutricionais: ");
                String nutritionalInfo = scanner.nextLine();

                System.out.print("Volume (em litros): ");
                double volume = Double.parseDouble(scanner.nextLine());

                System.out.print("É alcoólica? (true/false): ");
                boolean alcoholic = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Sabor: ");
                String flavor = scanner.nextLine();

                System.out.print("Marca: ");
                String brand = scanner.nextLine();

                controller.registerBeverage(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, volume, alcoholic, flavor, brand);
                break;

            case "2": // Alimento Processado
                expirationDate = LocalDate.parse(scanner.nextLine());

                System.out.print("Peso: ");
                weight = Double.parseDouble(scanner.nextLine());

                System.out.print("Refrigerado (true/false): ");
                refrigerated = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Informações nutricionais: ");
                nutritionalInfo = scanner.nextLine();

                System.out.print("Categoria: ");
                String category = scanner.nextLine();

                System.out.print("Marca: ");
                brand = scanner.nextLine();

                System.out.print("Contém conservantes? (true/false): ");
                boolean containsPreservatives = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Instruções de preparo: ");
                String cookingInstructions = scanner.nextLine();

                controller.registerProcessedFood(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, category, brand, containsPreservatives, cookingInstructions);
                break;

            case "3": // Carne
                expirationDate = LocalDate.parse(scanner.nextLine());

                System.out.print("Peso: ");
                weight = Double.parseDouble(scanner.nextLine());

                System.out.print("Refrigerado (true/false): ");
                refrigerated = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Informações nutricionais: ");
                nutritionalInfo = scanner.nextLine();

                System.out.print("Tipo de corte: ");
                String cutType = scanner.nextLine();

                System.out.print("Origem: ");
                String origin = scanner.nextLine();

                System.out.print("Orgânico? (true/false): ");
                boolean isOrganic = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Tipo de animal: ");
                String animalType = scanner.nextLine();

                System.out.print("Instruções de armazenamento: ");
                String storageInstructions = scanner.nextLine();

                controller.registerMeat(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, cutType, origin, isOrganic, animalType, storageInstructions);
                break;

            case "4": // Fruta
                expirationDate = LocalDate.parse(scanner.nextLine());

                System.out.print("Peso: ");
                weight = Double.parseDouble(scanner.nextLine());

                System.out.print("Refrigerado (true/false): ");
                refrigerated = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Informações nutricionais: ");
                nutritionalInfo = scanner.nextLine();

                System.out.print("Variedade: ");
                String variety = scanner.nextLine();

                System.out.print("Origem: ");
                origin = scanner.nextLine();

                System.out.print("É sazonal? (true/false): ");
                boolean seasonal = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Tipo de embalagem: ");
                String packagingType = scanner.nextLine();

                controller.registerFruit(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, variety, origin, seasonal, packagingType);
                break;

            case "5": // Produto de Higiene
                System.out.print("Tipo: ");
                String type = scanner.nextLine();

                System.out.print("Marca: ");
                brand = scanner.nextLine();

                System.out.print("Para pele sensível? (true/false): ");
                boolean forSensitiveSkin = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Instruções de uso: ");
                String usageInstructions = scanner.nextLine();

                System.out.print("É tóxico? (true/false): ");
                boolean toxic = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Fragrância: ");
                String scent = scanner.nextLine();

                System.out.print("Volume (em ml): ");
                volume = Double.parseDouble(scanner.nextLine());

                controller.registerHygieneProduct(cod, name, price, amount, type, brand, forSensitiveSkin, usageInstructions, toxic, scent, volume);
                break;

            case "6": // Utensílio
                System.out.print("Material: ");
                String material = scanner.nextLine();

                System.out.print("Categoria: ");
                category = scanner.nextLine();

                System.out.print("É reutilizável? (true/false): ");
                boolean isReusable = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Tamanho: ");
                String size = scanner.nextLine();

                controller.registerUtensil(cod, name, price, amount, material, category, isReusable, size);
                break;

            default:
                System.out.println("Opção inválida.");
                return;
        }

        System.out.println("Produto registrado com sucesso!");
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
