package viewer;

import javax.swing.*;

public class SQLiteViewer extends JFrame {

    final String TITLE_OF_PROGRAM = "SQLite Viewer";
    final String BTN_ENTER = "Enter";

    public SQLiteViewer() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 800);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        JTextField fileNameTextField = new JTextField();
        fileNameTextField.setName("FileNameTextField");
        fileNameTextField.setBounds(20,20, getWidth() - 130,30);
        add(fileNameTextField);

        fileNameTextField.setName("FileNameTextField");
        JButton openFileButton = new JButton(BTN_ENTER);
        openFileButton.setName("OpenFileButton");
        openFileButton.setBounds(getWidth() - 100, 20, 70, 30);
        add(openFileButton);
        setVisible(true);
    }
}
