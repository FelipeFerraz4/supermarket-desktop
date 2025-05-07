package view.swing.employee.people.client;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Client;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.employee.people.SearchPersonPanel;

import javax.swing.*;
import java.awt.*;

public class UpdateClientPanel extends JPanel {
    public UpdateClientPanel(PersonController personController, ProductController productController, Person personDeafault, Person person) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        if (!(person instanceof Client client)) {
            add(new JLabel("Erro: pessoa selecionada não é um cliente."));
            return;
        }

        JLabel titleLabel = new JLabel("Atualizar Cliente");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(40));

        // Campos de entrada
        JTextField phoneField = new JTextField(client.getPhone());
        JTextField emailField = new JTextField(client.getEmail());
        JPasswordField passwordField = new JPasswordField();

        add(AuxComponents.createLabeledField("Telefone", 16, phoneField, 300, 30));
        add(Box.createVerticalStrut(10));
        add(AuxComponents.createLabeledField("Email", 16, emailField, 300, 30));
        add(Box.createVerticalStrut(10));
        add(AuxComponents.createLabeledField("Nova Senha", 16, passwordField, 300, 30));
        add(Box.createVerticalStrut(30));

        // Botão salvar
        JButton saveButton = AuxComponents.createStyledButton("Salvar", 150, 40, () -> {
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            personController.updateClient(client.getId(), phone, email, password);
            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            SwingMenu.changeScreen(new UpdateClientPanel(personController, productController, personDeafault, person));
        });

        // Botão voltar
        JButton backButton = AuxComponents.createStyledButton("Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new SearchPersonPanel(personController, productController, personDeafault)));

        add(AuxComponents.createHorizontalButtonPanel(backButton, saveButton));
    }
}
