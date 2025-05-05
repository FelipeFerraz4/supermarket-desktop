package view.swing.product;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Person;
import model.products.Product;
import model.products.ProductType;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.people.ManagePeoplePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class SearchProductsPanel extends JPanel {

    public SearchProductsPanel(PersonController personController, ProductController productController, Person employee) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Buscar Produtos", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout());

        JComboBox<String> filterCombo = new JComboBox<>(new String[]{"Todos", "Bebida", "Processados", "Carne", "Fruta", "Higiene", "Utensílio"});
        JTextField searchField = new JTextField(20);

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Tipo", "Preço"}, 0);
        JTable productTable = new JTable(tableModel);

        productTable.getColumnModel().getColumn(0).setMinWidth(0);
        productTable.getColumnModel().getColumn(0).setMaxWidth(0);
        productTable.getColumnModel().getColumn(0).setWidth(0);

        JButton searchButton = AuxComponents.createStyledButton("Buscar", 100, 30, () -> {
            String filter = (String) filterCombo.getSelectedItem();
            String query = searchField.getText().trim().toLowerCase();

            List<Product> filtered = productController.getAllProducts().stream()
                    .filter(p -> {
                        if ("Todos".equals(filter)) return true;
                        return p.getTypeProduct().toString().equalsIgnoreCase(filter);
                    })
                    .filter(p -> query.isEmpty() || p.getName().toLowerCase().contains(query))
                    .toList();

            tableModel.setRowCount(0);
            for (Product p : filtered) {
                tableModel.addRow(new Object[]{p.getId(), p.getName(), p.getTypeProduct().toString(), String.format("R$ %.2f", p.getPrice())});
            }
        });

        JButton buttonBack = AuxComponents.createStyledButton("Voltar", 100, 30,
                () -> SwingMenu.changeScreen(new ManageProductsPanel(personController, productController, employee)));

        topPanel.add(new JLabel("Filtro por tipo:"));
        topPanel.add(filterCombo);
        topPanel.add(new JLabel("Buscar por nome:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(buttonBack);

        add(topPanel, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        // Preenche a tabela inicialmente
        tableModel.setRowCount(0);
        for (Product p : productController.getAllProducts()) {
            tableModel.addRow(new Object[]{p.getId(), p.getName(), p.getTypeProduct().toString(), String.format("R$ %.2f", p.getPrice())});
        }

        // Redireciona para a tela de edição apropriada ao selecionar uma linha
        productTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && productTable.getSelectedRow() != -1) {
                UUID id = UUID.fromString(tableModel.getValueAt(productTable.getSelectedRow(), 0).toString());
                Product selected = productController.searchById(id);
                ProductType type = selected.getTypeProduct();

                switch (type) {
                    case DRINK -> SwingMenu.changeScreen(new ManageProductsPanel(personController, productController, employee));
                    case FOOD -> SwingMenu.changeScreen(new ManageProductsPanel(personController, productController, employee));
                    case MEAT -> SwingMenu.changeScreen(new ManageProductsPanel(personController, productController, employee));
                    case FRUIT -> SwingMenu.changeScreen(new ManageProductsPanel(personController, productController, employee));
                    case HYGIENE -> SwingMenu.changeScreen(new ManageProductsPanel(personController, productController, employee));
                    case UTENSIL -> SwingMenu.changeScreen(new ManageProductsPanel(personController, productController, employee));
                }
            }
        });
    }
}
