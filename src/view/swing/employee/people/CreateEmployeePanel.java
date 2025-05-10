package view.swing.employee.people;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.employee.EmployeePanel;
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

        JTextField nameField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField birthField = new JTextField();
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
                String name = nameField.getText().trim();
                String cpf = cpfField.getText().trim();
                String phone = phoneField.getText().trim();
                LocalDate birthDate = LocalDate.parse(birthField.getText().trim());
                String position = positionField.getText().trim();
                double salary = Double.parseDouble(salaryField.getText().trim());
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                LocalDate hireDate = LocalDate.now();

                personController.registerEmployee(name, cpf, birthDate, email, password, phone, position, salary, hireDate);
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                SwingMenu.changeScreen(new CreateEmployeePanel(personController, productController, employee));

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Erro de validação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (exceptions.DuplicateEntityException ex) {
                JOptionPane.showMessageDialog(this, "Erro: já existe um funcionário com essas informações. " + ex.getMessage(), "Erro de Duplicação", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro inesperado ao cadastrar funcionário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton buttonBack = AuxComponents.createStyledButton("Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new EmployeePanel(personController, productController, employee)));

        add(AuxComponents.createHorizontalButtonPanel(buttonBack, buttonRegister));
    }
}
