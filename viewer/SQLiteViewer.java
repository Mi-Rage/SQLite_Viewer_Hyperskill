package viewer;

import javax.swing.*;
import java.util.ArrayList;

public class SQLiteViewer extends JFrame {

    final String TITLE_OF_PROGRAM = "SQLite Viewer";
    final String BTN_ENTER = "Open";
    final String BTN_EXEC = "Execute";
    DataBase dataBase;

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

        JComboBox<String> tablesComboBox = new JComboBox<>();
        tablesComboBox.setName("TablesComboBox");
        tablesComboBox.setBounds(20, 70, getWidth() - 50, 30);
        add(tablesComboBox);

        JTextArea queryTextArea = new JTextArea();
        queryTextArea.setName("QueryTextArea");
        queryTextArea.setBounds(20, 120, getWidth() - 170, 60);
        add(queryTextArea);

        JButton executeQueryButton = new JButton(BTN_EXEC);
        executeQueryButton.setName("ExecuteQueryButton");
        executeQueryButton.setBounds(getWidth() - 140, 120, 110, 30);
        add(executeQueryButton);

        openFileButton.addActionListener(e -> {
            String fileName = fileNameTextField.getText();
            if (!fileName.equals("")) {
                System.out.println(fileName);
                dataBase = new DataBase(fileName);
                ArrayList<String> tables = dataBase.getTableNames();
                tablesComboBox.removeAllItems();
                tables.forEach(tablesComboBox::addItem);
                queryTextArea.removeAll();
            }
        });

        tablesComboBox.addActionListener(e -> {
            String item = (String) tablesComboBox.getSelectedItem();
            queryTextArea.removeAll();
            queryTextArea.setText("SELECT * FROM " + item + ";");
        });

        setVisible(true);
    }
}
