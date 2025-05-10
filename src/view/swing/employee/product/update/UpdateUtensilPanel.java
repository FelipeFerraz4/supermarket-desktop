package view.swing.employee.product.update;

import controllers.PersonController;
import controllers.ProductController;
import dtos.UtensilDTO;
import exceptions.EntityNotFoundException;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.employee.product.SearchProductsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class UpdateUtensilPanel extends JPanel {
    public UpdateUtensilPanel(PersonController personController, ProductController productController, Person employee, UUID id) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        UtensilDTO dto;
        try {
            dto = UtensilDTO.toDTO(productController.searchById(id));
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Erro: ID fornecido é inválido.");
            return;
        } catch (EntityNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro: Utensílio não encontrado.");
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar utensílio: " + e.getMessage());
            return;
        }

        JLabel titleLabel = new JLabel("Atualizar Utensílio");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(40));

        JTextField codeField = new JTextField(dto.cod());
        JTextField nameField = new JTextField(dto.name());
        JTextField priceField = new JTextField(String.valueOf(dto.price()));
        JTextField amountField = new JTextField(String.valueOf(dto.amount()));
        JTextField materialField = new JTextField(dto.material());
        JTextField categoryField = new JTextField(dto.category());
        JCheckBox reusableBox = new JCheckBox("Reutilizável", dto.reusable());
        JTextField sizeField = new JTextField(dto.size());

        add(AuxComponents.createHorizontalFields(
                "Código:", 14, codeField, 200, 25,
                "Nome:", 14, nameField, 400, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Preço:", 14, priceField, 200, 25,
                "Quantidade:", 14, amountField, 200, 25
        ));
        add(AuxComponents.createHorizontalFields(
                "Material:", 14, materialField, 300, 25,
                "Categoria:", 14, categoryField, 300, 25
        ));
        add(Box.createVerticalStrut(10));
        add(AuxComponents.createLabeledField(
                "Tamanho:", 14, sizeField, 300, 25
        ));
        add(Box.createVerticalStrut(10));
        add(AuxComponents.createHorizontalCheckBoxes(reusableBox));
        add(Box.createVerticalStrut(20));

        JButton updateButton = AuxComponents.createStyledButton("Atualizar", 150, 40, () -> {
            try {
                UtensilDTO updatedDto = dto
                        .withCode(codeField.getText())
                        .withName(nameField.getText())
                        .withPrice(Double.parseDouble(priceField.getText()))
                        .withAmount(Integer.parseInt(amountField.getText()))
                        .withMaterial(materialField.getText())
                        .withCategory(categoryField.getText())
                        .withReusable(reusableBox.isSelected())
                        .withSize(sizeField.getText());

                productController.updateUtensil(id, updatedDto);
                JOptionPane.showMessageDialog(null, "Utensílio atualizado com sucesso!");
                SwingMenu.changeScreen(new UpdateUtensilPanel(personController, productController, employee, id));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro: Verifique os campos numéricos.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Erro: Dados inválidos fornecidos.");
            } catch (EntityNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Erro: Utensílio não encontrado durante a atualização.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar utensílio: " + e.getMessage());
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
