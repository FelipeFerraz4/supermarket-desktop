package view.swing;

import controllers.PersonController;
import controllers.ProductController;
import view.swing.client.ClientPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;

public class CreateClientPanel extends JPanel {
    public CreateClientPanel(PersonController personController, ProductController productController) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Cadastro de Cliente");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(80));

        // Campos
        JTextField nameField = new JTextField(20);
        JTextField cpfField = new JTextField(20);
        JTextField birthDateField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        add(AuxComponents.createHorizontalFields(
                "Nome:",15,  nameField, 400, 30,
                "CPF:", 15, cpfField, 200, 30
        ));

        add(Box.createVerticalStrut(10));

        add(AuxComponents.createHorizontalFields(
                "Data de Nascimento (AAAA-MM-DD):", 15, birthDateField, 400, 30,
                "Telefone:", 15, phoneField, 200, 30
        ));

        add(Box.createVerticalStrut(10));

        add(AuxComponents.createHorizontalFields(
                "E-mail:", 15, emailField, 400, 30,
                "Senha:", 15, passwordField, 200, 30
        ));

        JButton registerButton = AuxComponents.createStyledButton(
                "Cadastrar", 150, 40,
                () -> {
                    try {
                        String name = nameField.getText();
                        String cpf = cpfField.getText();
                        LocalDate birthDate = LocalDate.parse(birthDateField.getText());
                        String phone = phoneField.getText();
                        String email = emailField.getText();
                        String password = new String(passwordField.getPassword());
                        LocalDate accountCreationDate = LocalDate.now();
                        LocalDate dateLastPurchase = LocalDate.now();

                        personController.registerClient(name, cpf, birthDate, email, password, phone, accountCreationDate, dateLastPurchase);

                        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");

                        SwingMenu.changeScreen(new ClientPanel(personController, productController, personController.findByName(name), new HashMap<>()));
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
        );

        JButton backButton = AuxComponents.createStyledButton(
                "Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new MainMenuPanel(personController, productController))
        );

        JPanel buttonPanel = AuxComponents.createHorizontalButtonPanel(backButton, registerButton);
        add(buttonPanel);
    }
}
