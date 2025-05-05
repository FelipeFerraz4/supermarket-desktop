package view.swing.product.create;

import controllers.PersonController;
import controllers.ProductController;
import dtos.BeverageDTO;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.people.ManagePeoplePanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class CreateBeveragePanel extends JPanel {
    public CreateBeveragePanel(PersonController personController, ProductController productController, Person employee) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Cadastrar Bebidas");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(30));

        // Campos
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField expirationDateField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField brandField = new JTextField(); // Corrigido
        JTextField volumeField = new JTextField();
        JTextField flavorField = new JTextField();
        JTextField nutritionalInfoField = new JTextField(); // Corrigido

        JCheckBox refrigeratedBox = new JCheckBox("Refrigerado?");
        JCheckBox alcoholicBox = new JCheckBox("Alcoólica?");

        add(AuxComponents.createHorizontalFields(
                "Nome:", 14, nameField, 400, 25,
                "Preço:", 14, priceField, 200, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Quantidade:", 14, amountField, 300, 25,
                "Data de validade (AAAA-MM-DD):", 14, expirationDateField, 300, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Peso (Kg):", 14, weightField, 300, 25,
                "Marca:", 14, brandField, 300, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Volume (L):", 14, volumeField, 300, 25,
                "Sabor:", 14, flavorField, 300, 25
        ));
        add(AuxComponents.createLabeledField(
                "Informações Nutricionais:", 14, nutritionalInfoField, 620, 25
        ));

        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalCheckBoxes(refrigeratedBox, alcoholicBox));

        JButton registerBtn = AuxComponents.createStyledButton("Cadastrar", 150, 40, () -> {
            try {
                List<?> products = productController.getProductsByCategory(Class.forName("model.products.subclasses.Beverage"));
                String cod = String.format("BE%04d", products.size() + 1);

                String name = nameField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                int amount = Integer.parseInt(amountField.getText().trim());
                LocalDate expirationDate = LocalDate.parse(expirationDateField.getText().trim());
                double weight = Double.parseDouble(weightField.getText().trim());
                boolean refrigerated = refrigeratedBox.isSelected();
                String nutritionalInfo = nutritionalInfoField.getText().trim();
                double volume = Double.parseDouble(volumeField.getText().trim());
                boolean alcoholic = alcoholicBox.isSelected();
                String flavor = flavorField.getText().trim();
                String brand = brandField.getText().trim();

                BeverageDTO beverageDTO = new BeverageDTO(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, volume, alcoholic, flavor, brand);
                productController.registerBeverage(beverageDTO);

                JOptionPane.showMessageDialog(this, "Bebida cadastrada com sucesso!");
                SwingMenu.changeScreen(new ManagePeoplePanel(personController, productController, employee));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage());
            }
        });

        JButton backBtn = AuxComponents.createStyledButton("Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new ManagePeoplePanel(personController, productController, employee)));

        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalButtonPanel(registerBtn, backBtn));
    }
}
