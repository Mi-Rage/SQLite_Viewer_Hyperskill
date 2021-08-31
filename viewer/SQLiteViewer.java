package viewer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SQLiteViewer extends JFrame {

    final private String TITLE_OF_PROGRAM = "SQLite Viewer";
    final private String BTN_ENTER = "Open";
    final private String BTN_EXEC = "Execute";
    private DataBase dataBase;
    private ArrayList<String> tables;
    private final JComboBox<String> tablesComboBox;
    private final JTextArea queryTextArea;
    private final JButton executeQueryButton;

    public SQLiteViewer() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 800);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        JTextField fileNameTextField = new JTextField();
        fileNameTextField.setName("FileNameTextField");
        fileNameTextField.setBounds(20, 20, getWidth() - 130, 30);
        add(fileNameTextField);

        JButton openFileButton = new JButton(BTN_ENTER);
        openFileButton.setName("OpenFileButton");
        openFileButton.setBounds(getWidth() - 100, 20, 70, 30);
        add(openFileButton);

        tablesComboBox = new JComboBox<>();
        tablesComboBox.setName("TablesComboBox");
        tablesComboBox.setBounds(20, 70, getWidth() - 50, 30);
        add(tablesComboBox);

        queryTextArea = new JTextArea();
        queryTextArea.setName("QueryTextArea");
        queryTextArea.setBounds(20, 120, getWidth() - 170, 60);
        add(queryTextArea);

        executeQueryButton = new JButton(BTN_EXEC);
        executeQueryButton.setName("ExecuteQueryButton");
        executeQueryButton.setBounds(getWidth() - 140, 120, 110, 30);
        add(executeQueryButton);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setName("Table");
        JScrollPane contentTable = new JScrollPane(table);
        contentTable.setBounds(20,200, getWidth() - 50, 300);
        add(contentTable);

        setAllFieldsEnabled(false);

        openFileButton.addActionListener(e -> {
            String fileName = fileNameTextField.getText();

            if (Files.exists(Paths.get(fileName)) && !fileName.equals("")) {
                System.out.println(fileName);
                setAllFieldsEnabled(true);
                dataBase = new DataBase(fileName);
                tables = dataBase.getTableNames();
                tablesComboBox.removeAllItems();
                tables.forEach(tablesComboBox::addItem);
                queryTextArea.removeAll();
            } else {
                setAllFieldsEnabled(false);
                JOptionPane.showMessageDialog(new Frame(), String.format("ERROR! File \"%s\" not found!", fileName));
            }
        });

        tablesComboBox.addActionListener(e -> {
            String item = (String) tablesComboBox.getSelectedItem();
            queryTextArea.removeAll();
            String request = "SELECT * FROM " + item + ";";
            queryTextArea.setText(request);
        });

        executeQueryButton.addActionListener(e -> {
            model.setRowCount(0);

            String request = queryTextArea.getText();
            if (dataBase.getResponse(request)) {
                Object[] columns = dataBase.getColumnNames().toArray();
                model.setColumnIdentifiers(columns);

                ArrayList<ArrayList<String>> data = dataBase.getContentTable();
                for (ArrayList<String> row : data) {
                    model.addRow(row.toArray());
                }
            } else {
                JOptionPane.showMessageDialog(new Frame(), String.format("ERROR! \"%s\" wrong request!", request));
            }
        });

        setVisible(true);
    }

    private void setAllFieldsEnabled(boolean mode) {
        queryTextArea.setEnabled(mode);
        executeQueryButton.setEnabled(mode);
    }
}
