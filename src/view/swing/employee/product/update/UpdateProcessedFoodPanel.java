package view.swing.employee.product.update;

import controllers.PersonController;
import controllers.ProductController;
import dtos.ProcessedFoodDTO;
import exceptions.EntityNotFoundException;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.employee.product.SearchProductsPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.UUID;

public class UpdateProcessedFoodPanel extends JPanel {
    public UpdateProcessedFoodPanel(PersonController personController, ProductController productController, Person employee, UUID id) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ProcessedFoodDTO dto;
        try {
            dto = ProcessedFoodDTO.toDTO(productController.searchById(id));
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "ID fornecido é inválido.");
            return;
        } catch (EntityNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o produto: " + e.getMessage());
            return;
        }

        JLabel titleLabel = new JLabel("Atualizar Alimento Processado");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(40));

        JTextField codeField = new JTextField(dto.cod());
        JTextField nameField = new JTextField(dto.name());
        JTextField priceField = new JTextField(String.valueOf(dto.price()));
        JTextField amountField = new JTextField(String.valueOf(dto.amount()));
        JTextField expirationField = new JTextField(dto.expirationDate().toString());
        JTextField weightField = new JTextField(String.valueOf(dto.weight()));
        JCheckBox refrigeratedBox = new JCheckBox("Refrigerado", dto.refrigerated());
        JTextField nutritionalInfoField = new JTextField(dto.nutritionalInfo());
        JTextField categoryField = new JTextField(dto.category());
        JTextField brandField = new JTextField(dto.brand());
        JCheckBox preservativesBox = new JCheckBox("Contém conservantes", dto.containsPreservatives());
        JTextField cookingInstructionsField = new JTextField(dto.cookingInstructions());

        add(AuxComponents.createHorizontalFields(
                "Código:", 14, codeField, 200, 25,
                "Nome:", 14, nameField, 400, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Preço:", 14, priceField, 200, 25,
                "Quantidade:", 14, amountField, 200, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Validade (AAAA-MM-DD):", 14, expirationField, 200, 25,
                "Peso:", 14, weightField, 200, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Informações Nutricionais:", 14, nutritionalInfoField, 300, 25,
                "Categoria:", 14, categoryField, 200, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Marca:", 14, brandField, 300, 25,
                "Instruções de Preparo:", 14, cookingInstructionsField, 300, 25
        ));
        add(Box.createVerticalStrut(10));
        add(AuxComponents.createHorizontalCheckBoxes(refrigeratedBox, preservativesBox));
        add(Box.createVerticalStrut(20));

        JButton updateButton = AuxComponents.createStyledButton("Atualizar", 150, 40, () -> {
            try {
                ProcessedFoodDTO updatedDto = dto
                        .withCode(codeField.getText())
                        .withName(nameField.getText())
                        .withPrice(Double.parseDouble(priceField.getText()))
                        .withAmount(Integer.parseInt(amountField.getText()))
                        .withExpirationDate(LocalDate.parse(expirationField.getText()))
                        .withWeight(Double.parseDouble(weightField.getText()))
                        .withRefrigerated(refrigeratedBox.isSelected())
                        .withNutritionalInfo(nutritionalInfoField.getText())
                        .withCategory(categoryField.getText())
                        .withBrand(brandField.getText())
                        .withContainsPreservatives(preservativesBox.isSelected())
                        .withCookingInstructions(cookingInstructionsField.getText());

                productController.updateProcessedFood(id, updatedDto);

                JOptionPane.showMessageDialog(null, "Produto processado atualizado com sucesso!");
                SwingMenu.changeScreen(new SearchProductsPanel(personController, productController, employee));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Campos numéricos.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Dados inválidos.");
            } catch (EntityNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        });

        JButton backButton = AuxComponents.createStyledButton("Voltar", 150, 40, () ->
                SwingMenu.changeScreen(new SearchProductsPanel(personController, productController, employee)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);
        add(buttonPanel);
    }
}
