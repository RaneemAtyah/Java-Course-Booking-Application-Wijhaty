package oop2project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionWindow extends JFrame {
    private JFrame mainWindow;

   
    private JRadioButton ai; 
    private JRadioButton ds; 
    private JButton nextButton; 
    private JButton backButton; 
    private static final Color TEXT_COLOR = new Color(47, 140, 163);  
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 18);
    private static final Font FONT = new Font("Arial", Font.BOLD, 16);
    

    public OptionWindow(JFrame mainWindow) {
        this.mainWindow = mainWindow;
        setBackground(Color.WHITE);
        setTitle("نافذة التخصصات");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); 
        getContentPane().setBackground(Color.WHITE);
        initComponents();
    }

    private void initComponents() {
      
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));  
        panel1.setBackground(Color.WHITE);
        backButton = createButton("عودة");
        panel1.add(backButton);

        
        JPanel panel2 = new JPanel(new GridLayout(3, 1, 5, 5));  
        panel2.setBackground(Color.WHITE);
        panel2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " التخصصات"));

        JLabel label1 = new JLabel("اختر أحد التخصصات", SwingConstants.CENTER);
        label1.setFont(TITLE_FONT);
        label1.setForeground(TEXT_COLOR);  

        ai = createRadioButton("الذكاء الاصطناعي");  
        ds = createRadioButton("علوم البيانات");  

        ButtonGroup group = new ButtonGroup();
        group.add(ai);
        group.add(ds);

        panel2.add(label1);
        panel2.add(ai);
        panel2.add(ds);

        
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));  
        panel3.setBackground(Color.WHITE);
        nextButton = createButton("التالي");
        panel3.add(nextButton);

        
        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);


        nextButton.addActionListener(new NextButtonListener(this));
        backButton.addActionListener(new BackButtonListener());
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(FONT);
        button.setBackground(Color.WHITE);
        button.setForeground(TEXT_COLOR);  
        return button;
    }

    private JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(FONT);
        radioButton.setBackground(Color.WHITE);
        radioButton.setForeground(TEXT_COLOR);  
        radioButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        return radioButton;
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
          
            setVisible(false);
            mainWindow.setVisible(true);
        }
    }

    private class NextButtonListener implements ActionListener {
        private final OptionWindow optionWindow;

        public NextButtonListener(OptionWindow optionWindow) {
            this.optionWindow = optionWindow;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (ai.isSelected()) {
                AICoursesWindow aiCoursesWindow = new AICoursesWindow(optionWindow);
                aiCoursesWindow.setVisible(true);
                optionWindow.setVisible(false);
            } else if (ds.isSelected()) {
                DSCoursesWindow dsCoursesWindow = new DSCoursesWindow(optionWindow);
                dsCoursesWindow.setVisible(true);
                optionWindow.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(optionWindow, "يرجى اختيار تخصص قبل المتابعة.", "تحذير", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
