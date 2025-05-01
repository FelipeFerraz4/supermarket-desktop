package view.swing.people;

import controllers.PersonController;
import controllers.ProductController;
import model.people.Client;
import model.people.Employee;
import model.people.Person;
import view.swing.AuxComponents;
import view.swing.SwingMenu;
import view.swing.people.client.UpdateClientPanel;
import view.swing.people.employee.ManageEmployeePanel;
import view.swing.people.employee.UpdateEmployeePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class SearchPersonPanel extends JPanel {

    public SearchPersonPanel(PersonController personController, ProductController productController, Person employee) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Gerenciar Pessoas", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Filtros e busca
        JPanel topPanel = new JPanel(new FlowLayout());

        JComboBox<String> filterCombo = new JComboBox<>(new String[]{"Todos", "Funcionários", "Clientes"});
        JTextField searchField = new JTextField(20);

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Nome", "E-mail", "Telefone", "Tipo"}, 0);
        JTable peopleTable = new JTable(tableModel);

        JButton searchButton = AuxComponents.createStyledButton("Buscar", 100, 30, () -> {
            String filter = (String) filterCombo.getSelectedItem();
            String query = searchField.getText().trim().toLowerCase();

            List<Person> filtered = personController.listAll().stream()
                    .filter(p -> {
                        if ("Funcionários".equals(filter)) return p instanceof Employee;
                        if ("Clientes".equals(filter)) return p instanceof Client;
                        return true;
                    })
                    .filter(p -> {
                        if (query.isEmpty()) return true;
                        return p.getName().toLowerCase().contains(query) ||
                                p.getEmail().toLowerCase().contains(query);
                    })
                    .toList();

            tableModel.setRowCount(0);
            for (Person p : filtered) {
                String type = (p instanceof Employee) ? "Funcionário" : "Cliente";
                tableModel.addRow(new Object[]{p.getId(), p.getName(), p.getEmail(), type});
            }
        });

        JButton buttonBack = AuxComponents.createStyledButton("Voltar", 100, 30,
                () -> SwingMenu.changeScreen(new ManageEmployeePanel(personController, productController, employee)));

        topPanel.add(new JLabel("Filtro:"));
        topPanel.add(filterCombo);
        topPanel.add(new JLabel("Buscar por nome ou e-mail:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(buttonBack);

        add(topPanel, BorderLayout.SOUTH);

        // Tabela
        JScrollPane scrollPane = new JScrollPane(peopleTable);
        add(scrollPane, BorderLayout.CENTER);

        peopleTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && peopleTable.getSelectedRow() != -1) {
                UUID id = UUID.fromString(tableModel.getValueAt(peopleTable.getSelectedRow(), 0).toString());
                Person selected = personController.findById(id);
                if (selected instanceof Employee emp) {
                    SwingMenu.changeScreen(new UpdateEmployeePanel(personController, productController, emp));
                } else if (selected instanceof Client client) {
                    SwingMenu.changeScreen(new UpdateClientPanel(personController, productController, client));
                }
            }
        });


        // Carrega todos inicialmente
        tableModel.setRowCount(0);
        for (Person p : personController.listAll()) {
            String type = (p instanceof Employee) ? "Funcionário" : "Cliente";
            tableModel.addRow(new Object[]{p.getName(), p.getEmail(), p.getPhone(), type});
        }
    }
}
