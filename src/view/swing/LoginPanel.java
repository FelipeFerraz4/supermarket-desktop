package view.swing;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel(PersonController personController, ProductController productController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Login no Sistema");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(80));

        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        add(AuxComponents.createLabeledField("E-mail:", 15, emailField, 400, 30));
        add(Box.createVerticalStrut(10));
        add(AuxComponents.createLabeledField("Senha:", 15, passwordField, 400, 30));
        add(Box.createVerticalStrut(10));

        JButton loginButton = AuxComponents.createStyledButton(
                "Login", 150, 40,
                () -> {
                    String email = emailField.getText();
                    String password = new String(passwordField.getPassword());
                    Person person = personController.login(email, password);
                    if (person != null) {
//                        JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
                        if (person instanceof model.people.Client) {
                            SwingMenu.changeScreen(new MainMenuPanel(personController, productController));
                        } else {
                            SwingMenu.changeScreen(new EmployeePanel(personController, productController, person));
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "E-mail ou senha incorretos.");
                    }
                }
        );

        JButton backButton = AuxComponents.createStyledButton(
                "Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new MainMenuPanel(personController, productController))
        );

        JPanel buttonPanel = AuxComponents.createHorizontalButtonPanel(backButton, loginButton);
        add(Box.createVerticalStrut(10));
        add(buttonPanel);
    }
}
