package ru.filesearcher.view;

import ru.filesearcher.controller.Searcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by ruslaanko on 01.05.17.
 */
public class SearchFileWindow extends JFrame {

    public static void init() {
        SearchFileWindow window = new SearchFileWindow();
        window.setTitle("File Searcher");
        window.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();


        int windowWidth = 450;
        int windowHeight = 250;

        window.setBounds((width - windowWidth) / 2, (height - windowHeight) / 2, windowWidth, windowHeight);
        window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel jPanel1 = new JPanel(new BorderLayout());
        JPanel jPanel2 = new JPanel(new BorderLayout());

        final JTextField fileNameTextField = new JTextField();
        fileNameTextField.setToolTipText("Введите имя файла");


        final JTextArea outputTextArea = new JTextArea(
                "Введите имя файла в строке поиска и нажмите кнопку \"Найти\"",15,45
        );
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        final JButton searchButton = new JButton("Найти");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Searcher searcher = new Searcher();
                String searchRequest = fileNameTextField.getText();
                fileNameTextField.setText("");
                String searchResult = searcher.search(searchRequest);
                outputTextArea.setText(searchResult);
            }
        });


        jPanel1.add(scrollPane);
        jPanel2.add(searchButton, BorderLayout.EAST);
        jPanel2.add(fileNameTextField, BorderLayout.CENTER);

        window.getContentPane().add(jPanel1, BorderLayout.CENTER);
        window.getContentPane().add(jPanel2, BorderLayout.SOUTH);

        window.pack();
        fileNameTextField.requestFocusInWindow();
        window.setVisible(true);


    }
}
