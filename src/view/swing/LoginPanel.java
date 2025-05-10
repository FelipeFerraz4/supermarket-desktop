package view.swing;

import controllers.PersonController;
import controllers.ProductController;
import exceptions.EntityNotFoundException;
import model.people.Person;
import view.swing.client.ClientPanel;
import view.swing.employee.EmployeePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.UUID;

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

        JButton loginButton = AuxComponents.createSafeStyledButton(
                this, "Login", 150, 40,
                () -> {
                    String email = emailField.getText();
                    String password = new String(passwordField.getPassword());

                    try {
                        Person person = personController.login(email, password);

                        if (person != null) {
                            if (person instanceof model.people.Client) {
                                Map<UUID, Double> cart = personController.getClientCart(person.getId());
                                SwingMenu.changeScreen(new ClientPanel(personController, productController, person, cart));
                            } else {
                                SwingMenu.changeScreen(new EmployeePanel(personController, productController, person));
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "E-mail ou senha incorretos.");
                        }

                    } catch (IllegalArgumentException | EntityNotFoundException ex) {
                        JOptionPane.showMessageDialog(this, "Erro durante login: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                },
                "Erro durante o processo de login"
        );

        JButton backButton = AuxComponents.createSafeStyledButton(
                this, "Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new MainMenuPanel(personController, productController)),
                "Erro ao retornar ao menu"
        );

        JPanel buttonPanel = AuxComponents.createHorizontalButtonPanel(backButton, loginButton);
        add(Box.createVerticalStrut(10));
        add(buttonPanel);
    }
}
