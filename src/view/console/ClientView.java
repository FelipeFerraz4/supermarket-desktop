package view.console;

import controllers.PersonController;
import controllers.ProductController;
import exceptions.DuplicateEntityException;
import exceptions.EntityNotFoundException;
import model.people.Person;
import model.products.Product;
import model.products.Utensil;
import model.products.food.Beverage;
import model.products.food.Fruit;
import model.products.food.Meat;
import model.products.HygieneProduct;
import model.products.food.ProcessedFood;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ClientView {

    public static void menu(Scanner scanner, PersonController personController, ProductController productController, Person person) {
        if (person != null) {
            System.out.println("Bem-vindo(a), " + person.getName() + "!");
        } else {
            System.out.println("Bem-vindo(a) ao nosso sistema de compras!");
        }
        Map<UUID, Double> cart = person != null ? personController.getClientCart(person.getId()) : new HashMap<>();

        int option = -1;
        do {
            try {
                System.out.println("\n--- MENU DE CLIENTE ---");
                System.out.println("1. Adicionar produto ao carrinho");
                System.out.println("2. Ver carrinho");
                System.out.println("3. Remover item do carrinho");
                System.out.println("4. Finalizar compra");
                System.out.println("0. Voltar");
                System.out.print("Escolha: ");

                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1 -> addProductByType(scanner, personController, productController, cart, person);
                    case 2 -> viewCart(cart, productController);
                    case 3 -> removeFromCart(cart, productController, personController, person);
                    case 4 -> finalizePurchase(cart, personController, productController, person);
                    case 0 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            } catch (RuntimeException e) {
                System.out.println("Erro de execução: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (option != 0);
    }

    private static void addProductByType(Scanner scanner, PersonController personController, ProductController productController, Map<UUID, Double> cart, Person person) {
        while (true) {
            try {
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

                List<Product> filteredProducts;

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
                        if (person != null) {
                            personController.addItemToClientCart(person.getId(), product.getId(), weight);
                        }
                    } else {
                        System.out.print("Digite a quantidade: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        cart.put(product.getId(), (double) quantity);
                        if (person != null) {
                            personController.addItemToClientCart(person.getId(), product.getId(), quantity);
                        }
                    }

                    System.out.println("Produto adicionado ao carrinho!");

                } catch (NumberFormatException e) {
                        System.out.println("Erro: Entrada inválida! Por favor, insira um número válido.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: Argumento inválido. Verifique o código ou peso/quantidade.");
                } catch (EntityNotFoundException e) {
                    System.out.println("Produto não encontrado");
                } catch (Exception e) {
                    System.out.println("Erro inesperado: " + e.getMessage());
                }
            } catch (Exception e) {
                System.out.println("Erro inesperado ao processar a escolha de tipo de produto: " + e.getMessage());
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
        try {
            for (Map.Entry<UUID, Double> entry : cart.entrySet()) {
                UUID id = entry.getKey();
                double quantityOrWeight = entry.getValue();

                Product product = productController.searchById(id);

                if (product == null) {
                    System.out.println("Produto com ID " + id + " não encontrado.");
                    continue;
                }

                if (product instanceof Meat) {
                    System.out.printf("%s -> %s - %.2f kg - R$ %.2f\n",
                            product.getCod(),
                            product.getName(),
                            quantityOrWeight,
                            product.getPrice() * quantityOrWeight);
                } else {
                    System.out.printf("%s -> %s - %d unidades - R$ %.2f\n",
                            product.getCod(),
                            product.getName(),
                            (int) quantityOrWeight,
                            product.getPrice() * quantityOrWeight);
                }

                total += product.getPrice() * quantityOrWeight;
            }

            DecimalFormat df = new DecimalFormat("R$ ###,##0.00");
            System.out.printf("Total: %s\n", df.format(total));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String date = LocalDateTime.now().format(formatter);
            System.out.println("Carrinho visualizado em: " + date);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento inválido ao processar o carrinho.");
        } catch (DuplicateEntityException e) {
            System.out.println("Erro: Entidade duplicada detectada.");
        } catch (EntityNotFoundException e) {
            System.out.println("Erro: Produto ou entidade não encontrada.");
        } catch (RuntimeException e) {
            System.out.println("Erro de execução ao processar o carrinho: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void removeFromCart(Map<UUID, Double> cart, ProductController productController, PersonController personController, Person person) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("\n--- Remover Produto do Carrinho ---");

            if (cart.isEmpty()) {
                System.out.println("Carrinho vazio. Nada para remover.");
                return;
            }

            viewCart(cart, productController);

            System.out.print("\nDigite o código do produto que deseja remover: ");
            String cod = scanner.nextLine();

            // Verifica se o produto existe pelo código
            Product productToRemove = productController.searchByCod(cod);

            if (productToRemove == null) {
                System.out.println("Produto não encontrado.");
                return;
            }

            // Verifica se o produto está no carrinho
            if (cart.containsKey(productToRemove.getId())) {
                cart.remove(productToRemove.getId());

                // Atualiza o carrinho no controlador da pessoa, se necessário
                if (person != null) {
                    personController.updateClientCart(person.getId(), cart);
                }

                System.out.println("Produto removido do carrinho!");
            } else {
                System.out.println("Este produto não está no seu carrinho.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento inválido ao tentar remover o produto.");
        } catch (EntityNotFoundException e) {
            System.out.println("Erro: Produto ou entidade não encontrada ao tentar remover o produto.");
        } catch (RuntimeException e) {
            System.out.println("Erro de execução ao tentar remover o produto: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void finalizePurchase(Map<UUID, Double> cart, PersonController personController, ProductController productController, Person person) {
        try {
            System.out.println("\n--- Finalizando Compra ---");

            viewCart(cart, productController);

            System.out.println("Pagamento Efetuado");
            System.out.println("Compra finalizada com sucesso!");

            cart.clear();

            if (person != null) {
                personController.updateClientCart(person.getId(), cart);
                System.out.println("Obrigado por comprar conosco, " + person.getName() + "!");
            } else {
                System.out.println("Obrigado por comprar conosco!");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento inválido ao finalizar a compra.");
        } catch (EntityNotFoundException e) {
            System.out.println("Erro: Entidade ou produto não encontrado ao tentar finalizar a compra.");
        } catch (RuntimeException e) {
            System.out.println("Erro de execução ao finalizar a compra: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

}
