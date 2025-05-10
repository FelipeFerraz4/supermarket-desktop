package view.swing.employee.product.create;

import controllers.PersonController;
import controllers.ProductController;
import dtos.HygieneProductDTO;
import exceptions.DuplicateEntityException;
import model.people.Person;
import model.products.HygieneProduct;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.employee.product.SelectProductTypePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CreateHygieneProductPanel extends JPanel {
    public CreateHygieneProductPanel(PersonController personController, ProductController productController, Person employee) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Cadastrar Produto de Higiene");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(30));

        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField brandField = new JTextField();
        JTextField usageInstructionsField = new JTextField();
        JTextField scentField = new JTextField();
        JTextField volumeField = new JTextField();

        JCheckBox sensitiveSkinBox = new JCheckBox("Para pele sensível?");
        JCheckBox toxicBox = new JCheckBox("É tóxico?");

        add(AuxComponents.createHorizontalFields(
                "Nome:", 14, nameField, 400, 25,
                "Preço:", 14, priceField, 200, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Quantidade:", 14, amountField, 300, 25,
                "Tipo:", 14, typeField, 300, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Marca:", 14, brandField, 300, 25,
                "Fragrância:", 14, scentField, 300, 25
        ));
        add(AuxComponents.createLabeledField(
                "Instruções de Uso:", 14, usageInstructionsField, 620, 25
        ));
        add(Box.createVerticalStrut(10));
        add(AuxComponents.createLabeledField(
                "Volume (ml):", 14, volumeField, 200, 25
        ));

        add(Box.createVerticalStrut(20));
        add(AuxComponents.createHorizontalCheckBoxes(sensitiveSkinBox, toxicBox));

        JButton registerBtn = AuxComponents.createStyledButton("Cadastrar", 150, 40, () -> {
            try {
                // Validações de campos
                if (nameField.getText().trim().isEmpty() || priceField.getText().trim().isEmpty() ||
                        amountField.getText().trim().isEmpty() || typeField.getText().trim().isEmpty() ||
                        brandField.getText().trim().isEmpty() || usageInstructionsField.getText().trim().isEmpty() ||
                        volumeField.getText().trim().isEmpty()) {
                    throw new IllegalArgumentException("Todos os campos são obrigatórios.");
                }

                double price = Double.parseDouble(priceField.getText().trim());
                int amount = Integer.parseInt(amountField.getText().trim());
                double volume = Double.parseDouble(volumeField.getText().trim());

                // Validações de valores positivos
                if (price <= 0 || amount <= 0 || volume <= 0) {
                    throw new IllegalArgumentException("Preço, quantidade e volume devem ser maiores que zero.");
                }

                // Gerar código
                List<?> products = productController.getProductsByCategory(HygieneProduct.class);
                String cod = String.format("HY%04d", products.size() + 1);

                String name = nameField.getText().trim();
                String type = typeField.getText().trim();
                String brand = brandField.getText().trim();
                boolean forSensitiveSkin = sensitiveSkinBox.isSelected();
                String usageInstructions = usageInstructionsField.getText().trim();
                boolean toxic = toxicBox.isSelected();
                String scent = scentField.getText().trim();

                // Criar o DTO do produto de higiene
                HygieneProductDTO hygieneDTO = new HygieneProductDTO(
                        cod, name, price, amount, type, brand, forSensitiveSkin, usageInstructions, toxic, scent, volume
                );

                productController.registerHygieneProduct(hygieneDTO);

                JOptionPane.showMessageDialog(this, "Produto de Higiene cadastrado com sucesso!");
                SwingMenu.changeScreen(new CreateHygieneProductPanel(personController, productController, employee));
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Erro de validação: " + e.getMessage());
            } catch (DuplicateEntityException e) {
                JOptionPane.showMessageDialog(this, "Produto já cadastrado");
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
