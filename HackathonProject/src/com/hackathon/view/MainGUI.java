package com.hackathon.view;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
    // GUI Components
    public JTextField txtTeamName, txtUniversity;
    public JTextField txtScore1, txtScore2, txtScore3;
    public JComboBox<String> categoryDropdown;
    public JButton btnAdd, btnSave, btnLoad, btnReport, btnUpdate, btnDelete, btnClear;
    public JTextArea outputArea;

    public MainGUI() {
        // Window title
        super("National Hackathon System");

        // Layout
        setLayout(new BorderLayout());

        // ====== TOP PANEL (Form fields) ======
        JPanel form = new JPanel(new GridLayout(6, 2, 5, 5));

        form.add(new JLabel("Team Name:"));
        txtTeamName = new JTextField();
        form.add(txtTeamName);

        form.add(new JLabel("University:"));
        txtUniversity = new JTextField();
        form.add(txtUniversity);

        form.add(new JLabel("Score 1:"));
        txtScore1 = new JTextField();
        form.add(txtScore1);

        form.add(new JLabel("Score 2:"));
        txtScore2 = new JTextField();
        form.add(txtScore2);

        form.add(new JLabel("Score 3:"));
        txtScore3 = new JTextField();
        form.add(txtScore3);

        form.add(new JLabel("Category:"));
        categoryDropdown = new JComboBox<>(new String[]{"Cyber Security", "Artificial Intelligence"});
        form.add(categoryDropdown);

        add(form, BorderLayout.NORTH);

        // ====== CENTER PANEL (Output text area) ======
        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // ====== BOTTOM PANEL (Buttons) ======
        JPanel buttons = new JPanel();

        btnAdd = new JButton("Add Team");
        btnUpdate = new JButton("Update Team");
        btnSave = new JButton("Save CSV");
        btnLoad = new JButton("Load CSV");
        btnReport = new JButton("Show Report");
        btnDelete = new JButton("Delete Team");
        btnClear = new JButton("Clear Form");
        
        buttons.add(btnAdd);
        buttons.add(btnUpdate);
        buttons.add(btnSave);
        buttons.add(btnLoad);
        buttons.add(btnReport);
        buttons.add(btnDelete);
        buttons.add(btnClear);
        
        add(buttons, BorderLayout.SOUTH);

        // Window settings
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
