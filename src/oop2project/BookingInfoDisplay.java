package oop2project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingInfoDisplay extends JFrame {
    private final JFrame bookWindow;

    public BookingInfoDisplay(JFrame bookWindow) {
        this.bookWindow = bookWindow;

        setTitle("عرض معلومات الحجز");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        getContentPane().setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton("عودة");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(47, 140, 163));
        backButton.addActionListener(new BackButtonListener());

        JLabel bookingLabel = new JLabel("معلومات حجزك:");
        bookingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        bookingLabel.setForeground(new Color(47, 140, 163));

        topPanel.add(bookingLabel);
        topPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);

        JTextArea bookingTextArea = new JTextArea();
        bookingTextArea.setEditable(false);
        bookingTextArea.setFont(new Font("Times New Roman", Font.BOLD, 16));
        bookingTextArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        bookingTextArea.setBackground(Color.WHITE);

        try (Scanner scanner = new Scanner(new File("booking_info.txt"))) {
            ArrayList<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            for (int i = Math.max(0, lines.size() - 5); i < lines.size(); i++) {
                bookingTextArea.append(lines.get(i) + "\n");
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "ملف معلومات الحجز غير موجود.", "خطأ", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(bookingTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.WHITE);

        JLabel thankYouLabel = new JLabel("شكرا لك");
        thankYouLabel.setFont(new Font("Arial", Font.BOLD, 16));
        thankYouLabel.setForeground(new Color(47, 140, 163));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(thankYouLabel, BorderLayout.NORTH);

        JButton exitButton = new JButton("خروج");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setBackground(new Color(69, 191, 212));
        exitButton.setForeground(Color.WHITE);

        exitButton.addActionListener(new ExitButtonListener());
        buttonPanel.add(exitButton, BorderLayout.SOUTH);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            bookWindow.setVisible(true);
        }
    }

    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
