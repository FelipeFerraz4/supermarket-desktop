package view.console;

import controllers.ProductController;
import model.products.Product;
import model.products.food.Beverage;
import model.products.food.ProcessedFood;
import model.products.food.Meat;
import model.products.food.Fruit;
import model.products.Utensil;
import model.products.HygieneProduct;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class ProductView {

    public static void menu(Scanner scanner, ProductController controller) {
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
                case 1 -> registerProduct(scanner, controller);
                case 2 -> updateProduct(scanner, controller);
                case 3 -> listProducts(controller);
                case 4 -> searchProductByName(scanner, controller);
                case 5 -> deleteProduct(scanner, controller);
                case 0 -> System.out.println("Voltando ao menu anterior...");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);
    }

    public static void registerProduct(Scanner scanner, ProductController controller) {
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

        LocalDate expirationDate;
        double weight;
        boolean refrigerated;
        String nutritionalInfo;
        double volume;
        String brand;
        String category;
        String origin;

        switch (option) {
            case "1": // Bebida
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
                category = scanner.nextLine();

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
                origin = scanner.nextLine();

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

    private static UUID findProduct(Scanner scanner, ProductController controller) {
        System.out.println("Como deseja buscar o produto?");
        System.out.println("1 - Por nome");
        System.out.println("2 - Por código");
        System.out.print("Escolha uma opção: ");

        String option = scanner.nextLine();
        UUID id = null;
        Product product;

        switch (option) {
            case "1":
                System.out.print("Nome do produto: ");
                String name = scanner.nextLine();
                product = controller.searchByName(name);
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

    public static void updateProduct(Scanner scanner, ProductController controller) {
        System.out.println("Qual tipo de produto você quer atualizar?");
        System.out.println("1. Bebida");
        System.out.println("2. Alimento processado");
        System.out.println("3. Carne");
        System.out.println("4. Fruta");
        System.out.println("5. Produto de higiene");
        System.out.println("6. Utensílio");

        int option = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        UUID id = findProduct(scanner, controller);

        switch (option) {
            case 1:
                System.out.println("Atualizando bebida");
                System.out.print("Código: ");
                String codB = scanner.nextLine();
                System.out.print("Nome: ");
                String nameB = scanner.nextLine();
                System.out.print("Preço: ");
                double priceB = scanner.nextDouble();
                System.out.print("Quantidade: ");
                int amountB = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Data de validade (yyyy-mm-dd): ");
                LocalDate expDateB = LocalDate.parse(scanner.nextLine());
                System.out.print("Peso: ");
                double weightB = scanner.nextDouble();
                System.out.print("Refrigerado (true/false): ");
                boolean refrigeratedB = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Informações nutricionais: ");
                String infoB = scanner.nextLine();
                System.out.print("Volume: ");
                double volumeB = scanner.nextDouble();
                System.out.print("Alcoólico (true/false): ");
                boolean alcoholic = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Sabor: ");
                String flavor = scanner.nextLine();
                System.out.print("Marca: ");
                String brandB = scanner.nextLine();

                controller.updateBeverage(id, codB, nameB, priceB, amountB, expDateB, weightB, refrigeratedB, infoB, volumeB, alcoholic, flavor, brandB);
                break;

            case 2:
                System.out.println("Atualizando alimento processado:");
                System.out.print("Código: ");
                String codP = scanner.nextLine();
                System.out.print("Nome: ");
                String nameP = scanner.nextLine();
                System.out.print("Preço: ");
                double priceP = scanner.nextDouble();
                System.out.print("Quantidade: ");
                int amountP = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Data de validade (yyyy-mm-dd): ");
                LocalDate expDateP = LocalDate.parse(scanner.nextLine());
                System.out.print("Peso: ");
                double weightP = scanner.nextDouble();
                System.out.print("Refrigerado (true/false): ");
                boolean refrigeratedP = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Informações nutricionais: ");
                String infoP = scanner.nextLine();
                System.out.print("Categoria: ");
                String category = scanner.nextLine();
                System.out.print("Marca: ");
                String brandP = scanner.nextLine();
                System.out.print("Contém conservantes (true/false): ");
                boolean preservatives = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Instruções de preparo: ");
                String instructions = scanner.nextLine();

                controller.updateProcessedFood(id, codP, nameP, priceP, amountP, expDateP, weightP, refrigeratedP, infoP, category, brandP, preservatives, instructions);
                break;

            case 3:
                System.out.println("Atualizando carne:");
                System.out.print("Código: ");
                String codM = scanner.nextLine();
                System.out.print("Nome: ");
                String nameM = scanner.nextLine();
                System.out.print("Preço: ");
                double priceM = scanner.nextDouble();
                System.out.print("Quantidade: ");
                int amountM = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Data de validade (yyyy-mm-dd): ");
                LocalDate expDateM = LocalDate.parse(scanner.nextLine());
                System.out.print("Peso: ");
                double weightM = scanner.nextDouble();
                System.out.print("Refrigerado (true/false): ");
                boolean refrigeratedM = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Informações nutricionais: ");
                String infoM = scanner.nextLine();
                System.out.print("Origem: ");
                String originM = scanner.nextLine();
                System.out.print("Instruções de armazenamento: ");
                String storage = scanner.nextLine();

                controller.updateMeat(id, codM, nameM, priceM, amountM, expDateM, weightM, refrigeratedM, infoM, originM, storage);
                break;

            case 4:
                System.out.println("Atualizando fruta:");
                System.out.print("Código: ");
                String codF = scanner.nextLine();
                System.out.print("Nome: ");
                String nameF = scanner.nextLine();
                System.out.print("Preço: ");
                double priceF = scanner.nextDouble();
                System.out.print("Quantidade: ");
                int amountF = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Data de validade (yyyy-mm-dd): ");
                LocalDate expDateF = LocalDate.parse(scanner.nextLine());
                System.out.print("Peso: ");
                double weightF = scanner.nextDouble();
                System.out.print("Refrigerado (true/false): ");
                boolean refrigeratedF = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Informações nutricionais: ");
                String infoF = scanner.nextLine();
                System.out.print("Variedade: ");
                String variety = scanner.nextLine();
                System.out.print("Origem: ");
                String originF = scanner.nextLine();
                System.out.print("É sazonal (true/false): ");
                boolean seasonal = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Tipo de embalagem: ");
                String packaging = scanner.nextLine();

                controller.updateFruit(id, codF, nameF, priceF, amountF, expDateF, weightF, refrigeratedF, infoF, variety, originF, seasonal, packaging);
                break;

            case 5:
                System.out.println("Atualizando produto de higiene:");
                System.out.print("Código: ");
                String codH = scanner.nextLine();
                System.out.print("Nome: ");
                String nameH = scanner.nextLine();
                System.out.print("Preço: ");
                double priceH = scanner.nextDouble();
                System.out.print("Quantidade: ");
                int amountH = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Tipo: ");
                String type = scanner.nextLine();
                System.out.print("Marca: ");
                String brandH = scanner.nextLine();
                System.out.print("Para pele sensível (true/false): ");
                boolean sensitive = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Modo de uso: ");
                String usage = scanner.nextLine();
                System.out.print("É tóxico (true/false): ");
                boolean toxic = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Fragrância: ");
                String scent = scanner.nextLine();
                System.out.print("Volume: ");
                double volumeH = scanner.nextDouble();

                controller.updateHygieneProduct(id, codH, nameH, priceH, amountH, type, brandH, sensitive, usage, toxic, scent, volumeH);
                break;

            case 6:
                System.out.println("Atualizando utensílio:");
                System.out.print("Código: ");
                String codU = scanner.nextLine();
                System.out.print("Nome: ");
                String nameU = scanner.nextLine();
                System.out.print("Preço: ");
                double priceU = scanner.nextDouble();
                System.out.print("Quantidade: ");
                int amountU = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Material: ");
                String material = scanner.nextLine();
                System.out.print("Categoria: ");
                String categoryU = scanner.nextLine();
                System.out.print("Reutilizável (true/false): ");
                boolean reusable = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Tamanho: ");
                String size = scanner.nextLine();

                controller.updateUtensil(id, codU, nameU, priceU, amountU, material, categoryU, reusable, size);
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void listProducts(ProductController controller) {
        Map<String, Class<?>> categories = Map.of(
                "🍹 Bebidas", Beverage.class,
                "🥫 Alimentos Processados", ProcessedFood.class,
                "🥩 Carnes", Meat.class,
                "🍎 Frutas", Fruit.class,
                "🧼 Produtos de Higiene", HygieneProduct.class,
                "🍴 Utensílios", Utensil.class
        );

        boolean anyProductFound = false;

        System.out.println("==== LISTA DE PRODUTOS ====");

        for (Map.Entry<String, Class<?>> entry : categories.entrySet()) {
            List<Product> products = controller.getProductsByCategory(entry.getValue());
            if (!products.isEmpty()) {
                anyProductFound = true;
                System.out.println("\n=== " + entry.getKey() + " ===");
                products.forEach(System.out::println);
            }
        }

        if (!anyProductFound) {
            System.out.println("Nenhum produto encontrado.");
        }
    }


    private static void searchProductByName(Scanner scanner, ProductController controller) {
        System.out.print("Nome do produto: ");
        String name = scanner.nextLine();
        Product product = controller.searchByName(name);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public static void deleteProduct(Scanner scanner, ProductController controller) {
        UUID id = findProduct(scanner, controller);
        if (id == null) return;

        controller.deleteProduct(id);
        System.out.println("Produto removido com sucesso!");
    }

}
