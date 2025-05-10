package view.swing.employee.people.client;

import controllers.PersonController;
import controllers.ProductController;
import exceptions.DuplicateEntityException;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.employee.EmployeePanel;
import view.swing.SwingMenu;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class CreateClientAdminPanel extends JPanel {
    public CreateClientAdminPanel(PersonController personController, ProductController productController, Person person) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Cadastrar Cliente");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(40));

        // Campos
        JTextField nameField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField birthField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        add(AuxComponents.createLabeledField("Nome:", 14, nameField, 460, 30));
        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalFields("CPF:", 14, cpfField, 220, 30, "Telefone:", 14, phoneField, 220, 30));
        add(AuxComponents.createHorizontalFields("Nascimento (AAAA-MM-DD):", 14, birthField, 220, 30, "E-mail:", 14, emailField, 220, 30));
        add(AuxComponents.createLabeledField("Senha:", 14, passwordField, 220, 30));
        add(Box.createVerticalStrut(20));

        JButton buttonRegister = AuxComponents.createStyledButton("Cadastrar", 150, 40, () -> {
            try {
                String name = nameField.getText();
                String cpf = cpfField.getText();
                LocalDate birthDate = LocalDate.parse(birthField.getText());
                String phone = phoneField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                LocalDate accountCreationDate = LocalDate.now();
                LocalDate dateLastPurchase = LocalDate.now();

                personController.registerClient(name, cpf, birthDate, email, password, phone, accountCreationDate, dateLastPurchase);
                JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");

                SwingMenu.changeScreen(new CreateClientAdminPanel(personController, productController, person));

            } catch (DuplicateEntityException e) {
                JOptionPane.showMessageDialog(this, "Erro: Já existe um cliente com esses dados.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Erro: Dados inválidos, " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro inesperado ao cadastrar cliente:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton buttonBack = AuxComponents.createStyledButton("Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new EmployeePanel(personController, productController, person)));

        add(AuxComponents.createHorizontalButtonPanel(buttonBack, buttonRegister));
    }
}
