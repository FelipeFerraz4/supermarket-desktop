package view.swing.employee.product.create;

import controllers.PersonController;
import controllers.ProductController;
import dtos.FruitDTO;
import model.people.Person;
import model.products.food.Fruit;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.employee.product.SelectProductTypePanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class CreateFruitPanel extends JPanel {
    public CreateFruitPanel(PersonController personController, ProductController productController, Person employee) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Cadastrar Fruta");
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
        JTextField nutritionalInfoField = new JTextField();
        JTextField varietyField = new JTextField();
        JTextField originField = new JTextField();
        JTextField packagingTypeField = new JTextField();

        JCheckBox refrigeratedBox = new JCheckBox("Refrigerado?");
        JCheckBox seasonalBox = new JCheckBox("É sazonal?");

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
                "Variedade:", 14, varietyField, 300, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Origem:", 14, originField, 300, 25,
                "Tipo de Embalagem:", 14, packagingTypeField, 300, 25
        ));
        add(AuxComponents.createLabeledField(
                "Informações Nutricionais:", 14, nutritionalInfoField, 620, 25
        ));

        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalCheckBoxes(refrigeratedBox, seasonalBox));

        JButton registerBtn = AuxComponents.createStyledButton("Cadastrar", 150, 40, () -> {
            try {
                List<?> products = productController.getProductsByCategory(Fruit.class);
                String cod = String.format("FR%04d", products.size() + 1);

                String name = nameField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                int amount = Integer.parseInt(amountField.getText().trim());
                LocalDate expirationDate = LocalDate.parse(expirationDateField.getText().trim());
                double weight = Double.parseDouble(weightField.getText().trim());
                boolean refrigerated = refrigeratedBox.isSelected();
                String nutritionalInfo = nutritionalInfoField.getText().trim();
                String variety = varietyField.getText().trim();
                String origin = originField.getText().trim();
                boolean seasonal = seasonalBox.isSelected();
                String packagingType = packagingTypeField.getText().trim();

                FruitDTO fruitDTO = new FruitDTO(cod, name, price, amount, expirationDate, weight, refrigerated,
                        nutritionalInfo, variety, origin, seasonal, packagingType);
                productController.registerFruit(fruitDTO);

                JOptionPane.showMessageDialog(this, "Fruta cadastrada com sucesso!");
                SwingMenu.changeScreen(new CreateMeatPanel(personController, productController, employee));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage());
            }
        });

        JButton backBtn = AuxComponents.createStyledButton("Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new SelectProductTypePanel(personController, productController, employee)));

        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalButtonPanel(registerBtn, backBtn));
    }
}
