package view.swing.product.create;

import controllers.PersonController;
import controllers.ProductController;
import dtos.UtensilDTO;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.product.ManageProductsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CreateUtensilPanel extends JPanel {
    public CreateUtensilPanel(PersonController personController, ProductController productController, Person employee) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Cadastrar Utensílio");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(30));

        // Campos
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField materialField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField sizeField = new JTextField();
        JCheckBox reusableBox = new JCheckBox("É reutilizável?");

        add(AuxComponents.createHorizontalFields(
                "Nome:", 14, nameField, 400, 25,
                "Preço:", 14, priceField, 200, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Quantidade:", 14, amountField, 300, 25,
                "Material:", 14, materialField, 300, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Categoria:", 14, categoryField, 300, 25,
                "Tamanho:", 14, sizeField, 300, 25
        ));
        add(Box.createVerticalStrut(10));
        add(AuxComponents.createHorizontalCheckBoxes(reusableBox));

        JButton registerBtn = AuxComponents.createStyledButton("Cadastrar", 150, 40, () -> {
            try {
                List<?> products = productController.getProductsByCategory(Class.forName("model.products.subclasses.Utensil"));
                String cod = String.format("UT%04d", products.size() + 1);

                String name = nameField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                int amount = Integer.parseInt(amountField.getText().trim());
                String material = materialField.getText().trim();
                String category = categoryField.getText().trim();
                boolean isReusable = reusableBox.isSelected();
                String size = sizeField.getText().trim();

                UtensilDTO utensilDTO = new UtensilDTO(cod, name, price, amount, material, category, isReusable, size);
                productController.registerUtensil(utensilDTO);

                JOptionPane.showMessageDialog(this, "Utensílio cadastrado com sucesso!");
                SwingMenu.changeScreen(new CreateUtensilPanel(personController, productController, employee));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage());
            }
        });

        JButton backBtn = AuxComponents.createStyledButton("Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new ManageProductsPanel(personController, productController, employee)));

        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalButtonPanel(registerBtn, backBtn));
    }
}
