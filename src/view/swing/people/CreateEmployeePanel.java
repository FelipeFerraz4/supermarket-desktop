package view.swing.people;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class CreateEmployeePanel extends JPanel {
    public CreateEmployeePanel(PersonController personController, ProductController productController, Person employee) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Cadastrar Funcionário");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(40));

        // Campos
        JTextField nameField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField birthField = new JTextField(); // formato: AAAA-MM-DD
        JTextField positionField = new JTextField();
        JTextField salaryField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        add(AuxComponents.createLabeledField("Nome:", 14, nameField, 460, 30));
        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalFields("CPF:", 14, cpfField, 220, 30, "Telefone:", 14, phoneField, 220, 30));
        add(AuxComponents.createHorizontalFields("Nascimento (AAAA-MM-DD):", 14, birthField, 220, 30, "Cargo:", 14, positionField, 220, 30));
        add(AuxComponents.createHorizontalFields("Salário:", 14, salaryField, 220, 30, "E-mail:", 14, emailField, 220, 30));
        add(AuxComponents.createLabeledField("Senha:", 14, passwordField, 220, 30));
        add(Box.createVerticalStrut(20));

        JButton buttonRegister = AuxComponents.createStyledButton("Cadastrar", 150, 40, () -> {
            try {
                String name = nameField.getText();
                String cpf = cpfField.getText();
                String phone = phoneField.getText();
                LocalDate birthDate = LocalDate.parse(birthField.getText());
                String position = positionField.getText();
                double salary = Double.parseDouble(salaryField.getText());
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                LocalDate hireDate = LocalDate.now();

                personController.registerEmployee(name, cpf, birthDate, email, password, phone, position, salary, hireDate);
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");

                SwingMenu.changeScreen(new ManagePeoplePanel(personController, productController, employee));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar funcionário: " + e.getMessage());
            }
        });

        JButton buttonBack = AuxComponents.createStyledButton("Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new ManagePeoplePanel(personController, productController, employee)));

        add(AuxComponents.createHorizontalButtonPanel(buttonBack, buttonRegister));
    }
}
