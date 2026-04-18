package oop2project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("تطبيق وجهتي ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        createMenuBar();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
       
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // set Layout for panel

        
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("logo.png"); 
        imageLabel.setIcon(icon);
        panel.add(imageLabel, BorderLayout.CENTER);

        
        JLabel welcomeLabel = new JLabel("مرحبا بكم في تطبيق وجهتي", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(47, 140, 163));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        
        JButton nextButton = new JButton("التالي");
        nextButton.setBackground(new Color(69, 191, 212));
        nextButton.setFont(new Font("Arial", Font.BOLD, 20));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(new NextButtonActionListener(this));
        panel.add(nextButton, BorderLayout.SOUTH);

        
        add(panel);
    }

    private void createMenuBar() {
        
        Color textColor = new Color(47, 140, 163);
        Font menuFont = new Font("Arial", Font.PLAIN, 16);

       
        JMenuBar menuBar = new JMenuBar();
        menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        
        JMenu fileMenu = new JMenu("ملف");
        fileMenu.setForeground(textColor);
        fileMenu.setFont(menuFont); 
        fileMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        JMenuItem exitItem = new JMenuItem("إغلاق");
        exitItem.setForeground(textColor);
        exitItem.setFont(menuFont); 
        exitItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        exitItem.addActionListener(new ExitMenuActionListener());
        fileMenu.add(exitItem);

        
        JMenu aboutMenu = new JMenu("حول");
        aboutMenu.setForeground(textColor);
        aboutMenu.setFont(menuFont); 
        aboutMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        JMenuItem aboutUsItem = new JMenuItem("عن التطبيق");
        aboutUsItem.setForeground(textColor);
        aboutUsItem.setFont(menuFont); 
        aboutUsItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        aboutUsItem.addActionListener(new AboutUsMenuActionListener());

        JMenuItem contactUsItem = new JMenuItem("اتصل بنا");
        contactUsItem.setForeground(textColor);
        contactUsItem.setFont(menuFont); 
        contactUsItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        contactUsItem.addActionListener(new ContactUsMenuActionListener());

        aboutMenu.add(aboutUsItem);
        aboutMenu.add(contactUsItem);

        
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        setJMenuBar(menuBar);
    }

   
    private class NextButtonActionListener implements ActionListener {
        private final MainWindow mainWindow;

        public NextButtonActionListener(MainWindow mainWindow) {
            this.mainWindow = mainWindow;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            OptionWindow optionWindow = new OptionWindow(mainWindow);
            optionWindow.setVisible(true);
            mainWindow.setVisible(false);
        }
    }

 
    private class ExitMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }


    private class AboutUsMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JOptionPane.showMessageDialog(MainWindow.this, "هذا اصدار تجريبي لتطبيق وجهتي.");
        }
    }

    
    private class ContactUsMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JOptionPane.showMessageDialog(MainWindow.this, "تواصل معنا على: support@wuajhati.com.");
        }
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
