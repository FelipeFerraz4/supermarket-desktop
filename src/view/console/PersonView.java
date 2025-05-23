package view.console;

import controllers.PersonController;
import exceptions.DuplicateEntityException;
import exceptions.EntityNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.UUID;

public class PersonView {

    public static void menu(Scanner scanner, PersonController controller) {
        int option = -1;
        do {
            try {
                System.out.println("\n=== MENU DE PESSOAS ===");
                System.out.println("1. Cadastrar pessoa");
                System.out.println("2. Listar pessoas");
                System.out.println("3. Atualizar pessoa");
                System.out.println("4. Buscar pessoa");
                System.out.println("5. Inativar pessoa");
                System.out.println("0. Voltar");
                System.out.print("Escolha a opção: ");

                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1 -> selectPerson(scanner, controller,"Cadastrar");
                    case 2 -> selectPerson(scanner, controller, "Listar");
                    case 3 -> selectPerson(scanner, controller,"Atualizar");
                    case 4 -> findPerson(scanner, controller);
                    case 5 -> deletePerson(scanner, controller);
                    case 0 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        } while (option != 0);
    }

    private static void selectPerson(Scanner scanner, PersonController controller, String operation) {
        try {
            System.out.println(operation + ":");
            System.out.println("1 - Funcionário");
            System.out.println("2 - Cliente");
            System.out.print("Escolha uma opção: ");
            String choice = scanner.nextLine();

            if (operation.equalsIgnoreCase("cadastrar")) {
                switch (choice) {
                    case "1" -> registerEmployee(scanner, controller);
                    case "2" -> registerClient(scanner, controller);
                    default -> System.out.println("Opção inválida.");
                }
            } else if (operation.equalsIgnoreCase("listar")) {
                switch (choice) {
                    case "1" -> {
                        System.out.println("\n--- Lista de Funcionários ---");
                        controller.listEmployees().forEach(person -> {
                            String status = person.getStatus() ? "" : "Funcionário inativo: ";
                            System.out.println(status + person);
                        });
                    }
                    case "2" -> {
                        System.out.println("\n--- Lista de Clientes ---");
                        controller.listClients().forEach(person -> {
                            String status = person.getStatus() ? "" : "Funcionário inativo: ";
                            System.out.println(status + person);
                        });
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } else {
                switch (choice) {
                    case "1" -> updateEmployee(scanner, controller);
                    case "2" -> updateClient(scanner, controller);
                    default -> System.out.println("Opção inválida.");
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void registerEmployee(Scanner scanner, PersonController controller) {
        try {
            System.out.print("Nome: ");
            String name = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Telefone: ");
            String phone = scanner.nextLine();

            System.out.print("Data de Nascimento (AAAA-MM-DD): ");
            String birthDateInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(birthDateInput, formatter);

            System.out.print("Cargo: ");
            String position = scanner.nextLine();

            System.out.print("Salário: ");
            double salary = Double.parseDouble(scanner.nextLine());

            System.out.print("E-mail: ");
            String email = scanner.nextLine();

            System.out.print("Senha: ");
            String password = scanner.nextLine();

            LocalDate hireDate = LocalDate.now();

            controller.registerEmployee(name, cpf, birthDate, email, password, phone, position, salary, hireDate);
            System.out.println("Funcionário cadastrado com sucesso!");

        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use o formato AAAA-MM-DD.");
        } catch (NumberFormatException e) {
            System.out.println("Valor numérico inválido para o salário.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento: " + e.getMessage());
        } catch (DuplicateEntityException e) {
            System.out.println("Funcionário já cadastrado");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void registerClient(Scanner scanner, PersonController controller) {
        try {
            System.out.print("Nome: ");
            String name = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("Data de nascimento (AAAA-MM-DD): ");
            String birthDateInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(birthDateInput, formatter);

            System.out.print("Telefone: ");
            String phone = scanner.nextLine();

            System.out.print("E-mail: ");
            String email = scanner.nextLine();

            System.out.print("Senha: ");
            String password = scanner.nextLine();

            LocalDate accountCreationDate = LocalDate.now();
            LocalDate dateLastPurchase = LocalDate.now();

            controller.registerClient(name, cpf, birthDate, email, password, phone, accountCreationDate, dateLastPurchase);
            System.out.println("Cliente cadastrado com sucesso!");

        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use o formato AAAA-MM-DD.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento: " + e.getMessage());
        } catch (DuplicateEntityException e) {
            System.out.println("Cliente já cadastrado");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void updateEmployee(Scanner scanner, PersonController controller) {
        try {
            UUID id = findPerson(scanner, controller);
            if (id == null) return;

            System.out.print("Novo telefone: ");
            String phone = scanner.nextLine();

            System.out.print("Novo cargo: ");
            String position = scanner.nextLine();

            System.out.print("Novo salário: ");
            String salaryinput = scanner.nextLine();
            double salary = Double.parseDouble(salaryinput.isEmpty() ? "0" : scanner.nextLine());

            System.out.print("Novo e-mail: ");
            String email = scanner.nextLine();

            System.out.print("Novo password: ");
            String password = scanner.nextLine();

            controller.updateEmployee(id, phone, position, salary, email, password);
            System.out.println("Funcionário atualizado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Salário inválido. Digite um número válido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Funcionário não encontrado");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void updateClient(Scanner scanner, PersonController controller) {
        try {
            UUID id = findPerson(scanner, controller);
            if (id == null) return;

            System.out.print("Novo telefone: ");
            String phone = scanner.nextLine();

            System.out.print("Novo e-mail: ");
            String email = scanner.nextLine();

            System.out.print("Novo password: ");
            String password = scanner.nextLine();

            controller.updateClient(id, phone, email, password);
            System.out.println("Cliente atualizado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Cliente não encontrado: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static UUID findPerson(Scanner scanner, PersonController controller) {
        try {
            System.out.println("Buscar pessoa por:");
            System.out.println("1 - Nome");
            System.out.println("2 - Email");
            System.out.println("3 - CPF");
            System.out.print("Escolha uma opção: ");
            String option = scanner.nextLine();

            UUID id = null;

            switch (option) {
                case "1" -> {
                    System.out.print("Nome da pessoa: ");
                    String name = scanner.nextLine();
                    var person = controller.findByName(name);
                    if (person != null) id = person.getId();
                }
                case "2" -> {
                    System.out.print("Email da pessoa: ");
                    String email = scanner.nextLine();
                    var person = controller.findByEmail(email);
                    if (person != null) id = person.getId();
                }
                case "3" -> {
                    System.out.print("CPF da pessoa: ");
                    String cpf = scanner.nextLine();
                    var person = controller.findByCPF(cpf);
                    if (person != null) id = person.getId();
                }
                default -> System.out.println("Opção inválida.");
            }

            if (id == null) {
                throw new EntityNotFoundException("Pessoa não encontrada.");
            }

            System.out.println("Pessoa encontrada: \n" + controller.findById(id).toString());

            return id;

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Pessoa não encontrada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
        return null;
    }

    private static void deletePerson(Scanner scanner, PersonController controller) {
        try {
            UUID id = findPerson(scanner, controller);
            if (id == null) return;

            controller.deletePerson(id);
            System.out.println("Pessoa inativada com sucesso.");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println("Pessoa não encontrada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

}
