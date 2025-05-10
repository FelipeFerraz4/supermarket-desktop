package view.swing.employee.product.create;

import controllers.PersonController;
import controllers.ProductController;
import dtos.FruitDTO;
import exceptions.DuplicateEntityException;
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
                // Validação dos campos
                String name = nameField.getText().trim();
                if (name.isEmpty()) throw new IllegalArgumentException("Nome não pode ser vazio.");

                String priceText = priceField.getText().trim();
                if (priceText.isEmpty()) throw new IllegalArgumentException("Preço não pode ser vazio.");
                double price = Double.parseDouble(priceText);
                if (price < 0) throw new IllegalArgumentException("Preço não pode ser negativo.");

                String amountText = amountField.getText().trim();
                if (amountText.isEmpty()) throw new IllegalArgumentException("Quantidade não pode ser vazia.");
                int amount = Integer.parseInt(amountText);
                if (amount < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa.");

                String expirationDateText = expirationDateField.getText().trim();
                if (expirationDateText.isEmpty()) throw new IllegalArgumentException("Data de validade não pode ser vazia.");
                LocalDate expirationDate = LocalDate.parse(expirationDateText);

                String weightText = weightField.getText().trim();
                if (weightText.isEmpty()) throw new IllegalArgumentException("Peso não pode ser vazio.");
                double weight = Double.parseDouble(weightText);
                if (weight < 0) throw new IllegalArgumentException("Peso não pode ser negativo.");

                String nutritionalInfo = nutritionalInfoField.getText().trim();
                if (nutritionalInfo.isEmpty()) throw new IllegalArgumentException("Informações nutricionais não podem ser vazias.");

                String variety = varietyField.getText().trim();
                if (variety.isEmpty()) throw new IllegalArgumentException("Variedade não pode ser vazia.");

                String origin = originField.getText().trim();
                if (origin.isEmpty()) throw new IllegalArgumentException("Origem não pode ser vazia.");

                String packagingType = packagingTypeField.getText().trim();
                if (packagingType.isEmpty()) throw new IllegalArgumentException("Tipo de embalagem não pode ser vazio.");

                boolean refrigerated = refrigeratedBox.isSelected();
                boolean seasonal = seasonalBox.isSelected();

                List<?> products = productController.getProductsByCategory(Fruit.class);
                String cod = String.format("FR%04d", products.size() + 1);

                // Criação do DTO
                FruitDTO fruitDTO = new FruitDTO(cod, name, price, amount, expirationDate, weight, refrigerated,
                        nutritionalInfo, variety, origin, seasonal, packagingType);

                // Registro do produto
                productController.registerFruit(fruitDTO);

                JOptionPane.showMessageDialog(this, "Fruta cadastrada com sucesso!");
                SwingMenu.changeScreen(new CreateFruitPanel(personController, productController, employee));
            } catch (IllegalArgumentException | DuplicateEntityException e) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro inesperado: " + e.getMessage());
            }
        });

        JButton backBtn = AuxComponents.createStyledButton("Voltar", 150, 40,
                () -> SwingMenu.changeScreen(new SelectProductTypePanel(personController, productController, employee)));

        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalButtonPanel(registerBtn, backBtn));
    }
}
