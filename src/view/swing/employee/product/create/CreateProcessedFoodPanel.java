package view.swing.employee.product.create;

import controllers.PersonController;
import controllers.ProductController;
import dtos.ProcessedFoodDTO;
import exceptions.DuplicateEntityException;
import model.people.Person;
import model.products.food.ProcessedFood;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.employee.product.SelectProductTypePanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class CreateProcessedFoodPanel extends JPanel {
    public CreateProcessedFoodPanel(PersonController personController, ProductController productController, Person employee) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Cadastrar Alimentos Processados");
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
        JTextField categoryField = new JTextField();
        JTextField brandField = new JTextField();
        JTextField cookingInstructionsField = new JTextField();

        JCheckBox refrigeratedBox = new JCheckBox("Refrigerado?");
        JCheckBox preservativesBox = new JCheckBox("Contém conservantes?");

        add(AuxComponents.createHorizontalFields("Nome:", 14, nameField, 400, 25,
                "Preço:", 14, priceField, 200, 25));
        add(AuxComponents.createHorizontalFields("Quantidade:", 14, amountField, 300, 25,
                "Data de validade (AAAA-MM-DD):", 14, expirationDateField, 300, 25));
        add(AuxComponents.createHorizontalFields("Peso (Kg):", 14, weightField, 300, 25,
                "Marca:", 14, brandField, 300, 25));
        add(AuxComponents.createHorizontalFields("Categoria:", 14, categoryField, 300, 25,
                "Instruções de Preparo:", 14, cookingInstructionsField, 300, 25));
        add(AuxComponents.createLabeledField("Informações Nutricionais:", 14, nutritionalInfoField, 620, 25));

        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalCheckBoxes(refrigeratedBox, preservativesBox));

        JButton registerBtn = AuxComponents.createStyledButton("Cadastrar", 150, 40, () -> {
            try {
                List<?> products = productController.getProductsByCategory(ProcessedFood.class);
                String cod = String.format("PR%04d", products.size() + 1);

                String name = nameField.getText().trim();
                double price;
                int amount;
                LocalDate expirationDate;
                double weight;
                String nutritionalInfo = nutritionalInfoField.getText().trim();
                String category = categoryField.getText().trim();
                String brand = brandField.getText().trim();
                String cookingInstructions = cookingInstructionsField.getText().trim();
                boolean refrigerated = refrigeratedBox.isSelected();
                boolean containsPreservatives = preservativesBox.isSelected();

                try {
                    price = Double.parseDouble(priceField.getText().trim());
                    amount = Integer.parseInt(amountField.getText().trim());
                    weight = Double.parseDouble(weightField.getText().trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos para preço, quantidade e peso.");
                    return;
                }

                try {
                    expirationDate = LocalDate.parse(expirationDateField.getText().trim());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Por favor, insira uma data válida para a validade (AAAA-MM-DD).");
                    return;
                }

                // Cria o DTO do alimento processado
                ProcessedFoodDTO dto = new ProcessedFoodDTO(cod, name, price, amount, expirationDate, weight, refrigerated,
                        nutritionalInfo, category, brand, containsPreservatives, cookingInstructions);

                productController.registerProcessedFood(dto);

                JOptionPane.showMessageDialog(this, "Alimento processado cadastrado com sucesso!");
                SwingMenu.changeScreen(new CreateProcessedFoodPanel(personController, productController, employee));
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
            } catch (DuplicateEntityException e) {
                JOptionPane.showMessageDialog(this, "Erro: Produto duplicado. Já existe um produto com as mesmas informações.");
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
