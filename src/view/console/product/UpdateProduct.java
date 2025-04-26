package view.console.product;

import controllers.ProductController;
import dtos.*;

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
                    System.out.print("Nova Origem: ");
                    dto = dto.withOrigin(scanner.nextLine());
                }
                case 10 -> {
                    System.out.print("Novas Instruções de armazenamento: ");
                    dto = dto.withStorageInstructions(scanner.nextLine());
                }
                case 0 -> {
                    controller.updateMeat(id, dto);
                    System.out.println("Carne atualizada com sucesso!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void updateFruit(Scanner scanner, ProductController controller, UUID id) {
        FruitDTO dto = FruitDTO.toDTO((controller.searchById(id)));

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
            System.out.println("9. Variedade");
            System.out.println("10. Origem");
            System.out.println("11. É sazonal");
            System.out.println("12. Tipo de embalagem");
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
                    System.out.print("Nova Variedade: ");
                    dto = dto.withVariety(scanner.nextLine());
                }
                case 10 -> {
                    System.out.print("Nova Origem: ");
                    dto = dto.withOrigin(scanner.nextLine());
                }
                case 11 -> {
                    System.out.print("É sazonal (true/false): ");
                    dto = dto.withSeasonal(scanner.nextBoolean());
                    scanner.nextLine();
                }
                case 12 -> {
                    System.out.print("Novo Tipo de embalagem: ");
                    dto = dto.withPackagingType(scanner.nextLine());
                }
                case 0 -> {
                    controller.updateFruit(id, dto);
                    System.out.println("Fruta atualizada com sucesso!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void updateHygieneProduct(Scanner scanner, ProductController controller, UUID id) {
        HygieneProductDTO dto = HygieneProductDTO.toDTO((controller.searchById(id)));
        while (true) {
            System.out.println("\nEscolha o campo para alterar:");
            System.out.println("1. Código");
            System.out.println("2. Nome");
            System.out.println("3. Preço");
            System.out.println("4. Quantidade");
            System.out.println("5. Tipo");
            System.out.println("6. Marca");
            System.out.println("7. Para pele sensível");
            System.out.println("8. Modo de uso");
            System.out.println("9. É tóxico");
            System.out.println("10. Fragrância");
            System.out.println("11. Volume");
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
                    System.out.print("Novo Tipo: ");
                    dto = dto.withType(scanner.nextLine());
                }
                case 6 -> {
                    System.out.print("Nova Marca: ");
                    dto = dto.withBrand(scanner.nextLine());
                }
                case 7 -> {
                    System.out.print("Para pele sensível (true/false): ");
                    dto = dto.withSensitive(scanner.nextBoolean());
                    scanner.nextLine();
                }
                case 8 -> {
                    System.out.print("Novo Modo de uso: ");
                    dto = dto.withUsageInstructions(scanner.nextLine());
                }
                case 9 -> {
                    System.out.print("É tóxico (true/false): ");
                    dto = dto.withToxic(scanner.nextBoolean());
                    scanner.nextLine();
                }
                case 10 -> {
                    System.out.print("Nova Fragrância: ");
                    dto = dto.withScent(scanner.nextLine());
                }
                case 11 -> {
                    System.out.print("Novo Volume: ");
                    dto = dto.withVolume(scanner.nextDouble());
                    scanner.nextLine();
                }
                case 0 -> {
                    controller.updateHygieneProduct(id, dto);
                    System.out.println("Produto de higiene atualizado com sucesso!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void updateUtensil(Scanner scanner, ProductController controller, UUID id){
        UtensilDTO utensilDTO = UtensilDTO.toDTO((controller.searchById(id)));

        while (true) {
            System.out.println("\nEscolha o campo para alterar:");
            System.out.println("1. Código");
            System.out.println("2. Nome");
            System.out.println("3. Preço");
            System.out.println("4. Quantidade");
            System.out.println("5. Material");
            System.out.println("6. Categoria");
            System.out.println("7. Reutilizável");
            System.out.println("8. Tamanho");
            System.out.println("0. Salvar e sair");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Novo Código: ");
                    utensilDTO = utensilDTO.withCode(scanner.nextLine());
                }
                case 2 -> {
                    System.out.print("Novo Nome: ");
                    utensilDTO = utensilDTO.withName(scanner.nextLine());
                }
                case 3 -> {
                    System.out.print("Novo Preço: ");
                    utensilDTO = utensilDTO.withPrice(scanner.nextDouble());
                    scanner.nextLine();
                }
                case 4 -> {
                    System.out.print("Nova Quantidade: ");
                    utensilDTO = utensilDTO.withAmount(scanner.nextInt());
                    scanner.nextLine();
                }
                case 5 -> {
                    System.out.print("Novo Material: ");
                    utensilDTO = utensilDTO.withMaterial(scanner.nextLine());
                }
                case 6 -> {
                    System.out.print("Nova Categoria: ");
                    utensilDTO = utensilDTO.withCategory(scanner.nextLine());
                }
                case 7 -> {
                    System.out.print("É reutilizável (true/false): ");
                    utensilDTO = utensilDTO.withReusable(scanner.nextBoolean());
                    scanner.nextLine();
                }
                case 8 -> {
                    System.out.print("Novo Tamanho: ");
                    utensilDTO = utensilDTO.withSize(scanner.nextLine());
                }
                case 0 -> {
                    controller.updateUtensil(id, utensilDTO);
                    System.out.println("Utensílio atualizado com sucesso!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
