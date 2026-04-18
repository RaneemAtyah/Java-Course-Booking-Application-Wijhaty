package oop2project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BookWindow extends JFrame {

    private final JTextField courseField;
    private final JTextField nameField;
    private final JTextField emailField;
    private final JTextField phoneField;

    public BookWindow(String selectedCourse, JFrame callingWindow) {

        setTitle("نافذة الحجز");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

       
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(mainPanel, BorderLayout.CENTER);

        
        JButton backButton = new JButton("عودة");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(47, 140, 163));
        backButton.addActionListener(new BackButtonListener(this, callingWindow));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        topPanel.add(backButton, BorderLayout.EAST);
        topPanel.setBackground(Color.WHITE);
        add(topPanel, BorderLayout.NORTH);

        
        JPanel contactPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        contactPanel.setBackground(Color.WHITE);
        contactPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contactPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        
        contactPanel.add(createLabel("الدورة المختارة:"));
        courseField = new JTextField(selectedCourse);
        courseField.setEditable(false);
        courseField.setHorizontalAlignment(SwingConstants.RIGHT);
        contactPanel.add(courseField);

        contactPanel.add(createLabel("الاسم:"));
        nameField = new JTextField();
        nameField.setHorizontalAlignment(SwingConstants.RIGHT);
        contactPanel.add(nameField);

        contactPanel.add(createLabel("البريد الإلكتروني:"));
        emailField = new JTextField();
        emailField.setHorizontalAlignment(SwingConstants.RIGHT);
        contactPanel.add(emailField);

        contactPanel.add(createLabel("رقم الهاتف:"));
        phoneField = new JTextField();
        phoneField.setHorizontalAlignment(SwingConstants.RIGHT);
        contactPanel.add(phoneField);

        mainPanel.add(contactPanel, BorderLayout.CENTER);

     
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        JButton confirmButton = new JButton("تأكيد");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setBackground(Color.WHITE);
        confirmButton.setForeground(new Color(47, 140, 163));
        confirmButton.addActionListener(new ConfirmButtonListener(this));

        JButton cancelButton = new JButton("إلغاء");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setForeground(new Color(47, 140, 163));
        cancelButton.addActionListener(new CancelButtonListener());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(47, 140, 163));
        return label;
    }

    private boolean validateInput(String email, String phone) {
        
        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this, "البريد الإلكتروني يجب أن يحتوي على '@'.", "خطأ", JOptionPane.ERROR_MESSAGE);
            return false;
        }

      
        if (!phone.startsWith("0") || phone.length() < 10) {
            JOptionPane.showMessageDialog(this, "رقم الهاتف يجب أن يبدأ بالصفر ويحتوي على 10 أرقام على الأقل.", "خطأ", JOptionPane.ERROR_MESSAGE);
            return false;
        }

      
        try {
            Long.parseLong(phone);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "رقم الهاتف يجب أن يحتوي فقط على أرقام.", "خطأ", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void saveBookingInfo(String course, String name, String email, String phone) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("booking_info.txt", true))) {
            writer.write("الدورة المختارة: " + course + "\n");
            writer.write("الاسم: " + name + "\n");
            writer.write("البريد الإلكتروني: " + email + "\n");
            writer.write("رقم الهاتف: " + phone + "\n");
            writer.write("----------\n");

            JOptionPane.showMessageDialog(this, "تم حفظ معلومات الحجز بنجاح!");
            dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "خطأ في حفظ معلومات الحجز.", "خطأ", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private static class BackButtonListener implements ActionListener {
        private final JFrame currentWindow;
        private final JFrame callingWindow;

        public BackButtonListener(JFrame currentWindow, JFrame callingWindow) {
            this.currentWindow = currentWindow;
            this.callingWindow = callingWindow;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            currentWindow.dispose();
            callingWindow.setVisible(true);
        }
    }

    
    private class ConfirmButtonListener implements ActionListener {
        private final BookWindow bookWindow ;

        private ConfirmButtonListener(BookWindow bookWindow) {
            this.bookWindow = bookWindow;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String course = courseField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            
            if (validateInput(email, phone)) {
                saveBookingInfo(course, name, email, phone);
                dispose();
                new BookingInfoDisplay(bookWindow);
            }
        }
    }

    
    private static class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
