package view.console.product;

import controllers.ProductController;
import dtos.BeverageDTO;
import dtos.MeatDTO;
import dtos.ProcessedFoodDTO;
import model.products.food.Beverage;
import model.products.food.ProcessedFood;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class UpdateProduct {

    public static void updateProduct(Scanner scanner, ProductController controller) {
        System.out.println("Qual tipo de produto você quer atualizar?");
        System.out.println("1. Bebida");
        System.out.println("2. Alimento processado");
        System.out.println("3. Carne");
        System.out.println("4. Fruta");
        System.out.println("5. Produto de higiene");
        System.out.println("6. Utensílio");

        int option = scanner.nextInt();
        scanner.nextLine();

        UUID id = ProductView.findProduct(scanner, controller);

        switch (option) {
            case 1 -> updateBeverage(scanner, controller, id);
            case 2 -> updateProcessedFood(scanner, controller, id);
            case 3 -> updateMeat(scanner, controller, id);
            case 4 -> updateFruit(scanner, controller, id);
            case 5 -> updateHygieneProduct(scanner, controller, id);
            case 6 -> updateUtensil(scanner, controller, id);
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void updateBeverage(Scanner scanner, ProductController controller, UUID id) {
        BeverageDTO dto = BeverageDTO.toDTO((controller.searchById(id)));

        while (true) {
            System.out.println("\nEscolha o campo para alterar:");
            System.out.println("1. Código");
            System.out.println("2. Nome");
            System.out.println("3. Preço");
            System.out.println("4. Quantidade");
            System.out.println("5. Data de validade");
            System.out.println("6. Peso");
            System.out.println("7. Refrigerado");
            System.out.println("8. Informações nutricionais");
            System.out.println("9. Volume");
            System.out.println("10. Alcoólico");
            System.out.println("11. Sabor");
            System.out.println("12. Marca");
            System.out.println("0. Salvar e sair");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> { System.out.print("Novo Código: "); dto = dto.withCode(scanner.nextLine()); }
                case 2 -> { System.out.print("Novo Nome: "); dto = dto.withName(scanner.nextLine()); }
                case 3 -> { System.out.print("Novo Preço: "); dto = dto.withPrice(scanner.nextDouble()); scanner.nextLine(); }
                case 4 -> { System.out.print("Nova Quantidade: "); dto = dto.withAmount(scanner.nextInt()); scanner.nextLine(); }
                case 5 -> { System.out.print("Nova Data de validade (yyyy-mm-dd): "); dto = dto.withExpirationDate(LocalDate.parse(scanner.nextLine())); }
                case 6 -> { System.out.print("Novo Peso: "); dto = dto.withWeight(scanner.nextDouble()); scanner.nextLine(); }
                case 7 -> { System.out.print("É refrigerado (true/false): "); dto = dto.withRefrigerated(scanner.nextBoolean()); scanner.nextLine(); }
                case 8 -> { System.out.print("Novas Informações nutricionais: "); dto = dto.withNutritionalInfo(scanner.nextLine()); }
                case 9 -> { System.out.print("Novo Volume: "); dto = dto.withVolume(scanner.nextDouble()); scanner.nextLine(); }
                case 10 -> { System.out.print("É alcoólico (true/false): "); dto = dto.withAlcoholic(scanner.nextBoolean()); scanner.nextLine(); }
                case 11 -> { System.out.print("Novo Sabor: "); dto = dto.withFlavor(scanner.nextLine()); }
                case 12 -> { System.out.print("Nova Marca: "); dto = dto.withBrand(scanner.nextLine()); }
                case 0 -> {
                    controller.updateBeverage(id, dto);
                    System.out.println("Bebida atualizada com sucesso!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    public static void updateProcessedFood(Scanner scanner, ProductController controller, UUID id) {
        ProcessedFoodDTO dto = ProcessedFoodDTO.toDTO((controller.searchById(id)));

        while (true) {
            System.out.println("\nEscolha o campo para alterar:");
            System.out.println("1. Código");
            System.out.println("2. Nome");
            System.out.println("3. Preço");
            System.out.println("4. Quantidade");
            System.out.println("5. Data de validade");
            System.out.println("6. Peso");
            System.out.println("7. Refrigerado");
            System.out.println("8. Informações nutricionais");
            System.out.println("9. Categoria");
            System.out.println("10. Marca");
            System.out.println("11. Contém conservantes");
            System.out.println("12. Instruções de preparo");
            System.out.println("0. Salvar e sair");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Novo Código: ");
                    dto = dto.withCode(scanner.nextLine());
                }
                case 2 -> {
                    System.out.print("Novo Nome: ");
                    dto = dto.withName(scanner.nextLine());
                }
                case 3 -> {
                    System.out.print("Novo Preço: ");
                    dto = dto.withPrice(scanner.nextDouble());
                    scanner.nextLine();
                }
                case 4 -> {
                    System.out.print("Nova Quantidade: ");
                    dto = dto.withAmount(scanner.nextInt());
                    scanner.nextLine();
                }
                case 5 -> {
                    System.out.print("Nova Data de validade (yyyy-mm-dd): ");
                    dto = dto.withExpirationDate(LocalDate.parse(scanner.nextLine()));
                }
                case 6 -> {
                    System.out.print("Novo Peso: ");
                    dto = dto.withWeight(scanner.nextDouble());
                    scanner.nextLine();
                }
                case 7 -> {
                    System.out.print("É refrigerado (true/false): ");
                    dto = dto.withRefrigerated(scanner.nextBoolean());
                    scanner.nextLine();
                }
                case 8 -> {
                    System.out.print("Novas Informações nutricionais: ");
                    dto = dto.withNutritionalInfo(scanner.nextLine());
                }
                case 9 -> {
                    System.out.print("Nova Categoria: ");
                    dto = dto.withCategory(scanner.nextLine());
                }
                case 10 -> {
                    System.out.print("Nova Marca: ");
                    dto = dto.withBrand(scanner.nextLine());
                }
                case 11 -> {
                    System.out.print("Contém conservantes (true/false): ");
                    dto = dto.withContainsPreservatives(scanner.nextBoolean());
                    scanner.nextLine();
                }
                case 12 -> {
                    System.out.print("Novas Instruções de preparo: ");
                    dto = dto.withCookingInstructions(scanner.nextLine());
                }
                case 0 -> {
                    controller.updateProcessedFood(id, dto);
                    System.out.println("Alimento processado atualizado com sucesso!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void updateMeat(Scanner scanner, ProductController controller, UUID id) {
        MeatDTO dto = MeatDTO.toDTO((controller.searchById(id)));

        while (true) {
            System.out.println("\nEscolha o campo para alterar:");
            System.out.println("1. Código");
            System.out.println("2. Nome");
            System.out.println("3. Preço");
            System.out.println("4. Quantidade");
            System.out.println("5. Data de validade");
            System.out.println("6. Peso");
            System.out.println("7. Refrigerado");
            System.out.println("8. Informações nutricionais");
            System.out.println("9. Origem");
            System.out.println("10. Instruções de armazenamento");
            System.out.println("0. Salvar e sair");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> { dto = dto.withCode(scanner.nextLine()); }
                case 2 -> { System.out.print("Novo Nome: "); dto = dto.withName(scanner.nextLine()); }
                case 3 -> { dto = dto.withPrice(scanner.nextDouble()); scanner.nextLine(); }
                case 4 -> { dto = dto.withAmount(scanner.nextInt()); scanner.nextLine(); }
                case 5 -> { dto = dto.withExpirationDate(LocalDate.parse(scanner.nextLine())); }
                case 6 -> { dto = dto.withWeight(scanner.nextDouble()); scanner.nextLine(); }
                case 7 -> { dto = dto.withRefrigerated(scanner.nextBoolean()); scanner.nextLine(); }
                case 8 -> { dto = dto.withNutritionalInfo(scanner.nextLine()); }
                case 9 -> { dto = dto.withOrigin(scanner.nextLine()); }
                case 10 -> { dto = dto.withStorageInstructions(scanner.nextLine()); }
                case 0 -> {
                    controller.updateMeat(id, dto);
                    System.out.println("Carne atualizada com sucesso!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }


    /*
    public static void updateProduct2(Scanner scanner, ProductController controller) {
        System.out.println("Qual tipo de produto você quer atualizar?");

        System.out.println("3. Carne");
        System.out.println("4. Fruta");
        System.out.println("5. Produto de higiene");
        System.out.println("6. Utensílio");

        int option = scanner.nextInt();
        scanner.nextLine();

        UUID id = ProductView.findProduct(scanner, controller);

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
    */
}
