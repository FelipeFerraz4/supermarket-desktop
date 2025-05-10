package view.swing.employee.product.update;

import controllers.PersonController;
import controllers.ProductController;
import dtos.HygieneProductDTO;
import exceptions.EntityNotFoundException;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.employee.product.SearchProductsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class UpdateHygienePanel extends JPanel {
    public UpdateHygienePanel(PersonController personController, ProductController productController, Person employee, UUID id) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        HygieneProductDTO dto;
        try {
            dto = HygieneProductDTO.toDTO(productController.searchById(id));
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "ID inválido fornecido.");
            return;
        } catch (EntityNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado ao buscar o produto de higiene.");
            return;
        }

        JLabel titleLabel = new JLabel("Atualizar Produto de Higiene");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(40));

        JTextField codeField = new JTextField(dto.cod());
        JTextField nameField = new JTextField(dto.name());
        JTextField priceField = new JTextField(String.valueOf(dto.price()));
        JTextField amountField = new JTextField(String.valueOf(dto.amount()));
        JTextField typeField = new JTextField(dto.type());
        JTextField brandField = new JTextField(dto.brand());
        JCheckBox sensitiveBox = new JCheckBox("Para pele sensível", dto.forSensitiveSkin());
        JTextField usageField = new JTextField(dto.usageInstructions());
        JCheckBox toxicBox = new JCheckBox("É tóxico", dto.toxic());
        JTextField scentField = new JTextField(dto.scent());
        JTextField volumeField = new JTextField(String.valueOf(dto.volume()));

        add(AuxComponents.createHorizontalFields(
                "Código:", 14, codeField, 200, 25,
                "Nome:", 14, nameField, 400, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Preço:", 14, priceField, 200, 25,
                "Quantidade:", 14, amountField, 200, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Tipo:", 14, typeField, 300, 25,
                "Marca:", 14, brandField, 300, 25
        ));
        add(Box.createVerticalStrut(10));
        add(AuxComponents.createLabeledField(
                "Volume:", 14, volumeField, 200, 25
        ));
        add(Box.createVerticalStrut(10));
        add(AuxComponents.createHorizontalFields(
                "Modo de uso:", 14, usageField, 400, 25,
                "Fragrância:", 14, scentField, 300, 25
        ));
        add(AuxComponents.createHorizontalCheckBoxes(sensitiveBox, toxicBox));
        add(Box.createVerticalStrut(20));

        JButton updateButton = AuxComponents.createStyledButton("Atualizar", 150, 40, () -> {
            try {
                HygieneProductDTO updatedDto = dto
                        .withCode(codeField.getText())
                        .withName(nameField.getText())
                        .withPrice(Double.parseDouble(priceField.getText()))
                        .withAmount(Integer.parseInt(amountField.getText()))
                        .withType(typeField.getText())
                        .withBrand(brandField.getText())
                        .withSensitive(sensitiveBox.isSelected())
                        .withUsageInstructions(usageField.getText())
                        .withToxic(toxicBox.isSelected())
                        .withScent(scentField.getText())
                        .withVolume(Double.parseDouble(volumeField.getText()));

                productController.updateHygieneProduct(id, updatedDto);
                JOptionPane.showMessageDialog(null, "Produto de higiene atualizado com sucesso!");
                SwingMenu.changeScreen(new UpdateHygienePanel(personController, productController, employee, id));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: Verifique se os campos numéricos estão preenchidos corretamente.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Erro: Dados inválidos fornecidos.");
            } catch (EntityNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Erro: Produto não encontrado.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro inesperado ao atualizar o produto de higiene: " + e.getMessage());
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
