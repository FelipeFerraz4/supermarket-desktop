package view.console.product;

import controllers.ProductController;
import dtos.*;
import exceptions.DuplicateEntityException;
import model.products.Product;
import model.products.food.Beverage;
import model.products.food.ProcessedFood;
import model.products.food.Meat;
import model.products.food.Fruit;
import model.products.Utensil;
import model.products.HygieneProduct;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class ProductView {

    public static void menu(Scanner scanner, ProductController controller) {
        int option = -1;
        do {
            try {
                System.out.println("\n=== MENU DE PRODUTOS ===");
                System.out.println("1. Cadastrar produto");
                System.out.println("2. Atualizar produto");
                System.out.println("3. Listar todos os produtos");
                System.out.println("4. Buscar produto por nome");
                System.out.println("5. Deletar produto");
                System.out.println("0. Voltar");
                System.out.print("Escolha a opção: ");
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1 -> registerProduct(scanner, controller);
                    case 2 -> UpdateProduct.updateProduct(scanner, controller);
                    case 3 -> listProducts(controller);
                    case 4 -> searchProductByName(scanner, controller);
                    case 5 -> deleteProduct(scanner, controller);
                    case 0 -> System.out.println("Voltando ao menu anterior...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (option != 0);
    }

    public static void registerProduct(Scanner scanner, ProductController controller) {
        try {
            System.out.println("Qual tipo de produto deseja cadastrar?");
            System.out.println("0 - Voltar");
            System.out.println("1 - Bebida");
            System.out.println("2 - Alimento Processado");
            System.out.println("3 - Carne");
            System.out.println("4 - Fruta");
            System.out.println("5 - Produto de Higiene");
            System.out.println("6 - Utensílio");
            System.out.print("Escolha uma opção: ");
            String option = scanner.nextLine();

            if (option.equals("0")) {
                System.out.println("Voltando ao menu anterior...");
                return;
            }

            System.out.print("Nome do produto: ");
            String name = scanner.nextLine();

            System.out.print("Preço: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Quantidade: ");
            int amount = Integer.parseInt(scanner.nextLine());

            String cod;
            List<Product> products;
            LocalDate expirationDate;
            double weight;
            boolean refrigerated;
            String nutritionalInfo;
            double volume;
            String brand;
            String category;
            String origin;

            switch (option) {
                case "1" -> {
                    products = controller.getProductsByCategory(Beverage.class);
                    cod = String.format("BE%04d", products.size() + 1);

                    System.out.print("Data de validade (AAAA-MM-DD): ");
                    expirationDate = LocalDate.parse(scanner.nextLine());

                    System.out.print("Peso: ");
                    weight = Double.parseDouble(scanner.nextLine());

                    System.out.print("Refrigerado (true/false): ");
                    refrigerated = Boolean.parseBoolean(scanner.nextLine());

                    System.out.print("Informações nutricionais: ");
                    nutritionalInfo = scanner.nextLine();

                    System.out.print("Volume (em litros): ");
                    volume = Double.parseDouble(scanner.nextLine());

                    System.out.print("É alcoólica? (true/false): ");
                    boolean alcoholic = Boolean.parseBoolean(scanner.nextLine());

                    System.out.print("Sabor: ");
                    String flavor = scanner.nextLine();

                    System.out.print("Marca: ");
                    brand = scanner.nextLine();

                    BeverageDTO beverageDTO = new BeverageDTO(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, volume, alcoholic, flavor, brand);
                    controller.registerBeverage(beverageDTO);
                }
                case "2" -> {
                    products = controller.getProductsByCategory(ProcessedFood.class);
                    cod = String.format("PR%04d", products.size() + 1);

                    System.out.print("Data de validade (AAAA-MM-DD): ");
                    expirationDate = LocalDate.parse(scanner.nextLine());

                    System.out.print("Peso: ");
                    weight = Double.parseDouble(scanner.nextLine());

                    System.out.print("Refrigerado (true/false): ");
                    refrigerated = Boolean.parseBoolean(scanner.nextLine());

                    System.out.print("Informações nutricionais: ");
                    nutritionalInfo = scanner.nextLine();

                    System.out.print("Categoria: ");
                    category = scanner.nextLine();

                    System.out.print("Marca: ");
                    brand = scanner.nextLine();

                    System.out.print("Contém conservantes? (true/false): ");
                    boolean containsPreservatives = Boolean.parseBoolean(scanner.nextLine());

                    System.out.print("Instruções de preparo: ");
                    String cookingInstructions = scanner.nextLine();

                    ProcessedFoodDTO dto = new ProcessedFoodDTO(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, category, brand, containsPreservatives, cookingInstructions);
                    controller.registerProcessedFood(dto);
                }
                case "3" -> {
                    products = controller.getProductsByCategory(Meat.class);
                    cod = String.format("ME%04d", products.size() + 1);

                    System.out.print("Data de validade (AAAA-MM-DD): ");
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
                    origin = scanner.nextLine();

                    System.out.print("Orgânico? (true/false): ");
                    boolean isOrganic = Boolean.parseBoolean(scanner.nextLine());

                    System.out.print("Tipo de animal: ");
                    String animalType = scanner.nextLine();

                    System.out.print("Instruções de armazenamento: ");
                    String storageInstructions = scanner.nextLine();

                    MeatDTO dto = new MeatDTO(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, cutType, origin, isOrganic, animalType, storageInstructions);
                    controller.registerMeat(dto);
                }
                case "4" -> {
                    products = controller.getProductsByCategory(Fruit.class);
                    cod = String.format("FR%04d", products.size() + 1);

                    System.out.print("Data de validade (AAAA-MM-DD): ");
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

                    FruitDTO dto = new FruitDTO(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, variety, origin, seasonal, packagingType);
                    controller.registerFruit(dto);
                }
                case "5" -> {
                    products = controller.getProductsByCategory(HygieneProduct.class);
                    cod = String.format("HY%04d", products.size() + 1);

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

                    HygieneProductDTO dto = new HygieneProductDTO(cod, name, price, amount, type, brand, forSensitiveSkin, usageInstructions, toxic, scent, volume);
                    controller.registerHygieneProduct(dto);
                }
                case "6" -> {
                    products = controller.getProductsByCategory(Utensil.class);
                    cod = String.format("UT%04d", products.size() + 1);

                    System.out.print("Material: ");
                    String material = scanner.nextLine();

                    System.out.print("Categoria: ");
                    category = scanner.nextLine();

                    System.out.print("É reutilizável? (true/false): ");
                    boolean isReusable = Boolean.parseBoolean(scanner.nextLine());

                    System.out.print("Tamanho: ");
                    String size = scanner.nextLine();

                    UtensilDTO dto = new UtensilDTO(cod, name, price, amount, material, category, isReusable, size);
                    controller.registerUtensil(dto);
                }
                default -> {
                    System.out.println("Opção inválida.");
                    return;
                }
            }

            System.out.println("Produto registrado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro de formatação numérica: insira apenas números válidos.");
        } catch (DateTimeParseException e) {
            System.out.println("Erro de data: use o formato correto AAAA-MM-DD.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento: " + e.getMessage());
        } catch (DuplicateEntityException e) {
            System.out.println("Produto já cadastrado anteriormente: ");
        } catch (Exception e) {
            System.out.println("Erro inesperado ao registrar produto: " + e.getMessage());
        }
    }

    static UUID findProduct(Scanner scanner, ProductController controller) {
        while (true) {
            try {
                System.out.println("Como deseja buscar o produto?");
                System.out.println("1 - Por nome");
                System.out.println("2 - Por código");
                System.out.println("0 - Voltar");
                System.out.print("Escolha uma opção: ");

                String option = scanner.nextLine().trim();

                if (option.equals("0")) {
                    return null; // Voltar ao menu anterior
                }

                Product product;

                switch (option) {
                    case "1" -> {
                        System.out.print("Nome do produto: ");
                        String name = scanner.nextLine();
                        product = controller.searchByName(name);
                    }
                    case "2" -> {
                        System.out.print("Código do produto: ");
                        String cod = scanner.nextLine();
                        product = controller.searchByCod(cod);
                    }
                    default -> {
                        System.out.println("Opção inválida. Tente novamente.");
                        continue;
                    }
                }

                if (product != null) {
                    return product.getId();
                } else {
                    System.out.println("Produto não encontrado ou inativo.");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Erro de entrada: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro ao buscar produto: " + e.getMessage());
            }
        }
    }

    private static void listProducts(ProductController controller) {
        Map<String, Class<?>> categories = Map.of(
                "Bebidas", Beverage.class,
                "Alimentos Processados", ProcessedFood.class,
                "Carnes", Meat.class,
                "Frutas", Fruit.class,
                "Produtos de Higiene", HygieneProduct.class,
                "Utensílios", Utensil.class
        );

        boolean anyProductFound = false;

        System.out.println("==== LISTA DE PRODUTOS ====");

        try {
            for (Map.Entry<String, Class<?>> entry : categories.entrySet()) {
                List<Product> products = controller.getProductsByCategory(entry.getValue());
                if (!products.isEmpty()) {
                    anyProductFound = true;
                    System.out.println("\n=== " + entry.getKey() + " ===");
                    products.forEach(System.out::println);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao listar produtos: " + e.getMessage());
        }

        if (!anyProductFound) {
            System.out.println("Nenhum produto encontrado.");
        }
    }

    private static void searchProductByName(Scanner scanner, ProductController controller) {
        try {
            System.out.print("Nome do produto: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("O nome do produto não pode estar vazio.");
                return;
            }

            Product product = controller.searchByName(name);

            if (product != null) {
                System.out.println(product);
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Nome informado invalido");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao buscar o produto: " + e.getMessage());
        }
    }

    public static void deleteProduct(Scanner scanner, ProductController controller) {
        try {
            UUID id = findProduct(scanner, controller);
            if (id == null) {
                System.out.println("Produto não encontrado ou ID inválido.");
                return;
            }

            controller.deleteProduct(id);
            System.out.println("Produto removido com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("ID inválido: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao remover o produto: " + e.getMessage());
        }
    }
}
