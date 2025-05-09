package view.console.product;

import controllers.ProductController;
import dtos.*;
import exceptions.EntityNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.UUID;

public class UpdateProduct {

    public static void updateProduct(Scanner scanner, ProductController controller) {
        try {
            System.out.println("Qual tipo de produto você quer atualizar?");
            System.out.println("1. Bebida");
            System.out.println("2. Alimento processado");
            System.out.println("3. Carne");
            System.out.println("4. Fruta");
            System.out.println("5. Produto de higiene");
            System.out.println("6. Utensílio");

            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                return;
            }

            UUID id = ProductView.findProduct(scanner, controller);
            if (id == null) {
                System.out.println("Produto não encontrado.");
                return;
            }

            switch (option) {
                case 1 -> updateBeverage(scanner, controller, id);
                case 2 -> updateProcessedFood(scanner, controller, id);
                case 3 -> updateMeat(scanner, controller, id);
                case 4 -> updateFruit(scanner, controller, id);
                case 5 -> updateHygieneProduct(scanner, controller, id);
                case 6 -> updateUtensil(scanner, controller, id);
                default -> System.out.println("Opção inválida.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    private static void updateBeverage(Scanner scanner, ProductController controller, UUID id) {
        try {
            BeverageDTO dto = BeverageDTO.toDTO(controller.searchById(id));

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

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Por favor, digite um número.");
                    continue;
                }

                try {
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
                            dto = dto.withPrice(Double.parseDouble(scanner.nextLine()));
                        }
                        case 4 -> {
                            System.out.print("Nova Quantidade: ");
                            dto = dto.withAmount(Integer.parseInt(scanner.nextLine()));
                        }
                        case 5 -> {
                            System.out.print("Nova Data de validade (yyyy-mm-dd): ");
                            dto = dto.withExpirationDate(LocalDate.parse(scanner.nextLine()));
                        }
                        case 6 -> {
                            System.out.print("Novo Peso: ");
                            dto = dto.withWeight(Double.parseDouble(scanner.nextLine()));
                        }
                        case 7 -> {
                            System.out.print("É refrigerado (true/false): ");
                            dto = dto.withRefrigerated(Boolean.parseBoolean(scanner.nextLine()));
                        }
                        case 8 -> {
                            System.out.print("Novas Informações nutricionais: ");
                            dto = dto.withNutritionalInfo(scanner.nextLine());
                        }
                        case 9 -> {
                            System.out.print("Novo Volume: ");
                            dto = dto.withVolume(Double.parseDouble(scanner.nextLine()));
                        }
                        case 10 -> {
                            System.out.print("É alcoólico (true/false): ");
                            dto = dto.withAlcoholic(Boolean.parseBoolean(scanner.nextLine()));
                        }
                        case 11 -> {
                            System.out.print("Novo Sabor: ");
                            dto = dto.withFlavor(scanner.nextLine());
                        }
                        case 12 -> {
                            System.out.print("Nova Marca: ");
                            dto = dto.withBrand(scanner.nextLine());
                        }
                        case 0 -> {
                            try {
                                controller.updateBeverage(id, dto);
                                System.out.println("Bebida atualizado com sucesso!");
                            } catch (EntityNotFoundException e) {
                                System.out.println("Erro: bebida não encontrado no momento da atualização.");
                            }
                            return;
                        }
                        default -> System.out.println("Opção inválida!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro de formato numérico! Verifique o valor inserido.");
                } catch (DateTimeParseException e) {
                    System.out.println("Erro ao ler data! Use o formato yyyy-mm-dd.");
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar ou atualizar a bebida: " + e.getMessage());
        }
    }

    public static void updateProcessedFood(Scanner scanner, ProductController controller, UUID id) {
        try {
            ProcessedFoodDTO dto = ProcessedFoodDTO.toDTO(controller.searchById(id));

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

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Por favor, digite um número.");
                    continue;
                }

                try {
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
                            dto = dto.withPrice(Double.parseDouble(scanner.nextLine()));
                        }
                        case 4 -> {
                            System.out.print("Nova Quantidade: ");
                            dto = dto.withAmount(Integer.parseInt(scanner.nextLine()));
                        }
                        case 5 -> {
                            System.out.print("Nova Data de validade (yyyy-mm-dd): ");
                            dto = dto.withExpirationDate(LocalDate.parse(scanner.nextLine()));
                        }
                        case 6 -> {
                            System.out.print("Novo Peso: ");
                            dto = dto.withWeight(Double.parseDouble(scanner.nextLine()));
                        }
                        case 7 -> {
                            System.out.print("É refrigerado (true/false): ");
                            dto = dto.withRefrigerated(Boolean.parseBoolean(scanner.nextLine()));
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
                            dto = dto.withContainsPreservatives(Boolean.parseBoolean(scanner.nextLine()));
                        }
                        case 12 -> {
                            System.out.print("Novas Instruções de preparo: ");
                            dto = dto.withCookingInstructions(scanner.nextLine());
                        }
                        case 0 -> {
                            try {
                                controller.updateProcessedFood(id, dto);
                                System.out.println("Alimento processado atualizado com sucesso!");
                            } catch (EntityNotFoundException e) {
                                System.out.println("Erro: alimento processado não encontrado no momento da atualização.");
                            }
                            return;
                        }
                        default -> System.out.println("Opção inválida!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro de formato numérico! Verifique o valor inserido.");
                } catch (DateTimeParseException e) {
                    System.out.println("Erro ao ler data! Use o formato yyyy-mm-dd.");
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar ou atualizar o alimento processado: " + e.getMessage());
        }
    }

    private static void updateMeat(Scanner scanner, ProductController controller, UUID id) {
        try {
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

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número.");
                    continue;
                }

                try {
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
                            dto = dto.withPrice(Double.parseDouble(scanner.nextLine()));
                        }
                        case 4 -> {
                            System.out.print("Nova Quantidade: ");
                            dto = dto.withAmount(Integer.parseInt(scanner.nextLine()));
                        }
                        case 5 -> {
                            System.out.print("Nova Data de validade (yyyy-mm-dd): ");
                            dto = dto.withExpirationDate(LocalDate.parse(scanner.nextLine()));
                        }
                        case 6 -> {
                            System.out.print("Novo Peso: ");
                            dto = dto.withWeight(Double.parseDouble(scanner.nextLine()));
                        }
                        case 7 -> {
                            System.out.print("É refrigerado (true/false): ");
                            dto = dto.withRefrigerated(Boolean.parseBoolean(scanner.nextLine()));
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
                            try {
                                controller.updateMeat(id, dto);
                                System.out.println("Carne atualizado com sucesso!");
                            } catch (EntityNotFoundException e) {
                                System.out.println("Erro: carne não encontrado no momento da atualização.");
                            }
                            return;
                        }
                        default -> System.out.println("Opção inválida!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro de formato numérico! Verifique o valor inserido.");
                } catch (DateTimeParseException e) {
                    System.out.println("Erro ao ler data! Use o formato yyyy-mm-dd.");
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar ou atualizar a carne: " + e.getMessage());
        }
    }

    private static void updateFruit(Scanner scanner, ProductController controller, UUID id) {
        try {
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

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número.");
                    continue;
                }

                try {
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
                            dto = dto.withPrice(Double.parseDouble(scanner.nextLine()));
                        }
                        case 4 -> {
                            System.out.print("Nova Quantidade: ");
                            dto = dto.withAmount(Integer.parseInt(scanner.nextLine()));
                        }
                        case 5 -> {
                            System.out.print("Nova Data de validade (yyyy-mm-dd): ");
                            dto = dto.withExpirationDate(LocalDate.parse(scanner.nextLine()));
                        }
                        case 6 -> {
                            System.out.print("Novo Peso: ");
                            dto = dto.withWeight(Double.parseDouble(scanner.nextLine()));
                        }
                        case 7 -> {
                            System.out.print("É refrigerado (true/false): ");
                            dto = dto.withRefrigerated(Boolean.parseBoolean(scanner.nextLine()));
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
                            dto = dto.withSeasonal(Boolean.parseBoolean(scanner.nextLine()));
                        }
                        case 12 -> {
                            System.out.print("Novo Tipo de embalagem: ");
                            dto = dto.withPackagingType(scanner.nextLine());
                        }
                        case 0 -> {
                            try {
                                controller.updateFruit(id, dto);
                                System.out.println("Fruta atualizado com sucesso!");
                            } catch (EntityNotFoundException e) {
                                System.out.println("Erro: fruta não encontrado no momento da atualização.");
                            }
                            return;
                        }
                        default -> System.out.println("Opção inválida!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro de formato numérico! Verifique o valor inserido.");
                } catch (DateTimeParseException e) {
                    System.out.println("Erro ao ler data! Use o formato yyyy-mm-dd.");
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar ou atualizar a fruta: " + e.getMessage());
        }
    }

    private static void updateHygieneProduct(Scanner scanner, ProductController controller, UUID id) {
        try {
            HygieneProductDTO dto = HygieneProductDTO.toDTO(controller.searchById(id));

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

                String input = scanner.nextLine();

                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Digite um número entre 0 e 11.");
                    continue;
                }

                try {
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
                            double price = Double.parseDouble(scanner.nextLine());
                            if (price < 0) throw new IllegalArgumentException("O preço não pode ser negativo.");
                            dto = dto.withPrice(price);
                        }
                        case 4 -> {
                            System.out.print("Nova Quantidade: ");
                            int amount = Integer.parseInt(scanner.nextLine());
                            if (amount < 0) throw new IllegalArgumentException("A quantidade não pode ser negativa.");
                            dto = dto.withAmount(amount);
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
                            String boolInput = scanner.nextLine();
                            if (!boolInput.equalsIgnoreCase("true") && !boolInput.equalsIgnoreCase("false")) {
                                System.out.println("Entrada inválida! Digite 'true' ou 'false'.");
                                continue;
                            }
                            dto = dto.withSensitive(Boolean.parseBoolean(boolInput));
                        }
                        case 8 -> {
                            System.out.print("Novo Modo de uso: ");
                            dto = dto.withUsageInstructions(scanner.nextLine());
                        }
                        case 9 -> {
                            System.out.print("É tóxico (true/false): ");
                            String boolInput = scanner.nextLine();
                            if (!boolInput.equalsIgnoreCase("true") && !boolInput.equalsIgnoreCase("false")) {
                                System.out.println("Entrada inválida! Digite 'true' ou 'false'.");
                                continue;
                            }
                            dto = dto.withToxic(Boolean.parseBoolean(boolInput));
                        }
                        case 10 -> {
                            System.out.print("Nova Fragrância: ");
                            dto = dto.withScent(scanner.nextLine());
                        }
                        case 11 -> {
                            System.out.print("Novo Volume: ");
                            double volume = Double.parseDouble(scanner.nextLine());
                            if (volume < 0) throw new IllegalArgumentException("O volume não pode ser negativo.");
                            dto = dto.withVolume(volume);
                        }
                        case 0 -> {
                            try {
                                controller.updateHygieneProduct(id, dto);
                                System.out.println("Produto de higiene atualizado com sucesso!");
                            } catch (EntityNotFoundException e) {
                                System.out.println("Erro: produto não encontrado no momento da atualização.");
                            }
                            return;
                        }
                        default -> System.out.println("Opção inválida! Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro de formato numérico: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro de validação: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Erro inesperado: " + e.getMessage());
                }
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Erro: produto de higiene não encontrado.");
        } catch (Exception e) {
            System.out.println("Erro ao carregar produto de higiene: " + e.getMessage());
        }
    }

    private static void updateUtensil(Scanner scanner, ProductController controller, UUID id) {
        try {
            UtensilDTO utensilDTO = UtensilDTO.toDTO(controller.searchById(id));

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

                String input = scanner.nextLine();

                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Digite um número entre 0 e 8.");
                    continue;
                }

                try {
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
                            String priceInput = scanner.nextLine();
                            double price = Double.parseDouble(priceInput);
                            if (price < 0) throw new IllegalArgumentException("O preço não pode ser negativo.");
                            utensilDTO = utensilDTO.withPrice(price);
                        }
                        case 4 -> {
                            System.out.print("Nova Quantidade: ");
                            String amountInput = scanner.nextLine();
                            int amount = Integer.parseInt(amountInput);
                            if (amount < 0) throw new IllegalArgumentException("A quantidade não pode ser negativa.");
                            utensilDTO = utensilDTO.withAmount(amount);
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
                            String boolInput = scanner.nextLine();
                            if (!boolInput.equalsIgnoreCase("true") && !boolInput.equalsIgnoreCase("false")) {
                                System.out.println("Entrada inválida! Digite 'true' ou 'false'.");
                                continue;
                            }
                            utensilDTO = utensilDTO.withReusable(Boolean.parseBoolean(boolInput));
                        }
                        case 8 -> {
                            System.out.print("Novo Tamanho: ");
                            utensilDTO = utensilDTO.withSize(scanner.nextLine());
                        }
                        case 0 -> {
                            try {
                                controller.updateUtensil(id, utensilDTO);
                                System.out.println("Utensílio atualizado com sucesso!");
                            } catch (EntityNotFoundException e) {
                                System.out.println("Erro: utensílio não encontrado no momento da atualização.");
                            }
                            return;
                        }
                        default -> System.out.println("Opção inválida! Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro de formato numérico: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro de validação: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Erro inesperado: " + e.getMessage());
                }
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Erro: utensílio não encontrado.");
        } catch (Exception e) {
            System.out.println("Erro ao carregar utensílio: " + e.getMessage());
        }
    }
}
