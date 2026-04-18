package oop2project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AICoursesWindow extends JFrame {

    private final JFrame optionWindow;
    private JRadioButton beginnerCourse1, beginnerCourse2, beginnerCourse3;
    private JRadioButton advancedCourse1, advancedCourse2, advancedCourse3;
    private JButton backButton, bookButton;
    Color color1= new Color(69, 191, 212);
    Color color2 = new Color(47, 140, 163);
    Font font = new Font("Arial", Font.BOLD, 16);
    
    public AICoursesWindow(JFrame optionWindow) {
        this.optionWindow = optionWindow;

        setTitle("دورات الذكاء الاصطناعي");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
        initComponents();
    }

    private void initComponents() {
        
        // Top Panel 
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // Back Button 
        backButton = new JButton("عودة");
        backButton.setFont(font);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(color2);
        backButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        // "قائمة الدورات" 
        JLabel coursesLabel = new JLabel("قائمة دورات الذكاء الاصطناعي", SwingConstants.CENTER);
        coursesLabel.setFont(new Font("Arial", Font.BOLD, 24));
        coursesLabel.setForeground(color2);

        
        topPanel.add(backButton, BorderLayout.EAST);
        topPanel.add(coursesLabel, BorderLayout.CENTER);
        topPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        topPanel.setBackground(Color.WHITE);

        add(topPanel, BorderLayout.NORTH);

        // Beginner Courses Panel
        JPanel beginnerPanel = new JPanel();
        beginnerPanel.setLayout(new GridLayout(3, 1));
        beginnerPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        beginnerPanel.setBackground(Color.WHITE);
        beginnerPanel.setBorder(BorderFactory.createTitledBorder("دورات للمبتدئين"));

        // Create beginner courses
        beginnerCourse1 = createRadioButton("برمجة بايثون");
        beginnerCourse2 = createRadioButton("مقدمة في الذكاء الاصطناعي");
        beginnerCourse3 = createRadioButton("مقدمة في تعلم الآلة");
        

        beginnerPanel.add(beginnerCourse1);
        beginnerPanel.add(beginnerCourse2);
        beginnerPanel.add(beginnerCourse3);

        // Advanced Courses Panel
        JPanel advancedPanel = new JPanel();
        advancedPanel.setLayout(new GridLayout(3, 1));
        advancedPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        advancedPanel.setBackground(Color.WHITE);
        advancedPanel.setBorder(BorderFactory.createTitledBorder("دورات متقدمة"));

        // Create advanced courses 
        advancedCourse1 = createRadioButton("معالجة اللغات الطبيعية");
        advancedCourse2 = createRadioButton("التعلم العميق");
        advancedCourse3 = createRadioButton("الرؤية الحاسوبية");

        // Grouping the advanced courses
        ButtonGroup group = new ButtonGroup();
        group.add(beginnerCourse1);
        group.add(beginnerCourse2);
        group.add(beginnerCourse3);
        group.add(advancedCourse1);
        group.add(advancedCourse2);
        group.add(advancedCourse3);

        advancedPanel.add(advancedCourse1);
        advancedPanel.add(advancedCourse2);
        advancedPanel.add(advancedCourse3);

        // Main Panel for organizing the beginner and advanced panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(beginnerPanel);
        mainPanel.add(advancedPanel);

        add(mainPanel, BorderLayout.CENTER);

        // Book Button
        bookButton = new JButton("حجز");
        bookButton.setFont(font);
        bookButton.setBackground(color1);
        bookButton.setForeground(Color.WHITE);
        add(bookButton, BorderLayout.SOUTH);

        // Action Listeners
        backButton.addActionListener(new BackButtonListener());
        bookButton.addActionListener(new BookButtonListener(this));
    }

    
    private JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(font);
        radioButton.setForeground(color2);
        radioButton.setBackground(Color.WHITE);
        radioButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        return radioButton;
    }

   
    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            optionWindow.setVisible(true);
        }
    }

    
    private class BookButtonListener implements ActionListener {
        private final AICoursesWindow aiCoursesWindow;

        private BookButtonListener(AICoursesWindow aiCoursesWindow) {
            this.aiCoursesWindow = aiCoursesWindow;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedCourse = getSelectedCourse();
            if (selectedCourse != null) {
                BookWindow bookWindow = new BookWindow(selectedCourse, AICoursesWindow.this);
                bookWindow.setVisible(true);
                aiCoursesWindow.setVisible(false); 
            } else {
                JOptionPane.showMessageDialog(aiCoursesWindow, "أختر دورة قبل المتابعة", "خطأ", JOptionPane.ERROR_MESSAGE);
            }
        }

        
        private String getSelectedCourse() {
            if (beginnerCourse1.isSelected()) return "برمجة بايثون";
            if (beginnerCourse2.isSelected()) return "مقدمة في الذكاء الاصطناعي";
            if (beginnerCourse3.isSelected()) return "مقدمة في تعلم الآلة";
            if (advancedCourse1.isSelected()) return "معالجة اللغات الطبيعية";
            if (advancedCourse2.isSelected()) return "التعلم العميق";
            if (advancedCourse3.isSelected()) return "الرؤية الحاسوبية";
            return null; // If no course selected
        }
    }
}
