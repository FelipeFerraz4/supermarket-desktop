package view.swing.employee.product.create;

import controllers.PersonController;
import controllers.ProductController;
import dtos.BeverageDTO;
import exceptions.DuplicateEntityException;
import model.people.Person;
import model.products.food.Beverage;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.employee.product.SelectProductTypePanel;

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

        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField expirationDateField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField brandField = new JTextField();
        JTextField volumeField = new JTextField();
        JTextField flavorField = new JTextField();
        JTextField nutritionalInfoField = new JTextField();

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
                // Validação dos campos de entrada
                String name = nameField.getText().trim();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Nome é obrigatório.");
                }

                double price;
                try {
                    price = Double.parseDouble(priceField.getText().trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Preço inválido.");
                }

                int amount;
                try {
                    amount = Integer.parseInt(amountField.getText().trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Quantidade inválida.");
                }

                LocalDate expirationDate;
                try {
                    expirationDate = LocalDate.parse(expirationDateField.getText().trim());
                } catch (Exception e) {
                    throw new IllegalArgumentException("Data de validade inválida.");
                }

                double weight;
                try {
                    weight = Double.parseDouble(weightField.getText().trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Peso inválido.");
                }

                String nutritionalInfo = nutritionalInfoField.getText().trim();
                if (nutritionalInfo.isEmpty()) {
                    throw new IllegalArgumentException("Informações nutricionais são obrigatórias.");
                }

                double volume;
                try {
                    volume = Double.parseDouble(volumeField.getText().trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Volume inválido.");
                }

                String flavor = flavorField.getText().trim();
                if (flavor.isEmpty()) {
                    throw new IllegalArgumentException("Sabor é obrigatório.");
                }

                String brand = brandField.getText().trim();
                if (brand.isEmpty()) {
                    throw new IllegalArgumentException("Marca é obrigatória.");
                }

                boolean refrigerated = refrigeratedBox.isSelected();
                boolean alcoholic = alcoholicBox.isSelected();

                List<?> products = productController.getProductsByCategory(Beverage.class);
                String cod = String.format("BE%04d", products.size() + 1);

                BeverageDTO beverageDTO = new BeverageDTO(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, volume, alcoholic, flavor, brand);
                productController.registerBeverage(beverageDTO);

                JOptionPane.showMessageDialog(this, "Bebida cadastrada com sucesso!");
                SwingMenu.changeScreen(new CreateBeveragePanel(personController, productController, employee));
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            } catch (DuplicateEntityException e) {
                JOptionPane.showMessageDialog(this, "Erro: Bebida já cadastrada.");
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
