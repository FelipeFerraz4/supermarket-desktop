package view.swing.people.employee;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Employee;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;

import javax.swing.*;
import java.awt.*;

public class UpdateEmployeePanel extends JPanel {
    public UpdateEmployeePanel(PersonController personController, ProductController productController, Person employee) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Atualizar Funcionário");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(40));

        Employee emp = (Employee) employee;

        JTextField phoneField = new JTextField(emp.getPhone());
        JTextField positionField = new JTextField(emp.getPosition());
        JTextField salaryField = new JTextField(String.valueOf(emp.getSalary()));
        JTextField emailField = new JTextField(emp.getEmail());
        JPasswordField passwordField = new JPasswordField(); // senha nova

        add(AuxComponents.createHorizontalFields("Telefone:", 14, phoneField, 220, 30, "Cargo:", 14, positionField, 220, 30));
        add(AuxComponents.createHorizontalFields("Salário:", 14, salaryField, 220, 30, "E-mail:", 14, emailField, 220, 30));
        add(AuxComponents.createLabeledField("Nova Senha:", 14, passwordField, 220, 30));
        add(Box.createVerticalStrut(20));

        JButton buttonUpdate = AuxComponents.createStyledButton("Atualizar", 150, 40, () -> {
            try {
                String phone = phoneField.getText();
                String position = positionField.getText();
                double salary = Double.parseDouble(salaryField.getText());
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                personController.updateEmployee(emp.getId(), phone, position, salary, email, password);
                JOptionPane.showMessageDialog(this, "Funcionário atualizado com sucesso!");

                SwingMenu.changeScreen(new ManageEmployeePanel(personController, productController, emp));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar funcionário: " + e.getMessage());
            }
        });

        JButton buttonBack = AuxComponents.createStyledButton("Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new ManageEmployeePanel(personController, productController, emp)));

        add(AuxComponents.createHorizontalButtonPanel(buttonBack, buttonUpdate));
    }
}
