package com.hackathon.controller;

import com.hackathon.model.*;
import com.hackathon.util.Manager; // if you used Manager class
import com.hackathon.view.MainGUI;

import javax.swing.*;
import java.awt.event.*;

/**
 * Complete Controller for the GUI. Replace your existing Controller.java with this file.
 */
public class Controller {

    private MainGUI gui;
    private TeamList teamList;

    public Controller(MainGUI gui, TeamList list) {
        this.gui = gui;
        this.teamList = list;

        // attach listeners
        this.gui.btnAdd.addActionListener(new AddTeamListener());
        this.gui.btnUpdate.addActionListener(new UpdateListener());
        this.gui.btnSave.addActionListener(new SaveListener());
        this.gui.btnLoad.addActionListener(new LoadListener());
        this.gui.btnReport.addActionListener(new ReportListener());
        this.gui.btnDelete.addActionListener(new DeleteListener());
        this.gui.btnClear.addActionListener(new ClearFormListener());
    }

    // ----- Add Team -----
    class AddTeamListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = gui.txtTeamName.getText().trim();
                String uni = gui.txtUniversity.getText().trim();

                if (name.isEmpty() || uni.isEmpty()) {
                    JOptionPane.showMessageDialog(gui, "Team name and university are required.", "Input error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // duplicate check (by name)
                if (teamList.searchTeam(name) != null) {
                    JOptionPane.showMessageDialog(gui, "Team name already exists. Choose another name.", "Duplicate", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double s1 = Double.parseDouble(gui.txtScore1.getText().trim());
                double s2 = Double.parseDouble(gui.txtScore2.getText().trim());
                double s3 = Double.parseDouble(gui.txtScore3.getText().trim());
                double[] scores = new double[]{s1, s2, s3};

                String category = (String) gui.categoryDropdown.getSelectedItem();
                Team t;
                if (category != null && category.equalsIgnoreCase("Cyber Security")) {
                    t = new CyberSecurityTeam(name, uni, scores);
                } else {
                    t = new AITeam(name, uni, scores);
                }

                teamList.addTeam(t);
                gui.outputArea.append("Team '" + name + "' registered (" + t.getCategory() + ").\n");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gui, "Please enter valid numeric scores.", "Input error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(gui, "Error adding team: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // ----- Update Team (replace entire Team object matching team name) -----
    class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Use the current team name field to find the team to update
                String oldName = gui.txtTeamName.getText().trim();
                if (oldName.isEmpty()) {
                    JOptionPane.showMessageDialog(gui, "Enter the team name to update (existing name).", "Input required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Team existing = teamList.searchTeam(oldName);
                if (existing == null) {
                    JOptionPane.showMessageDialog(gui, "Team not found: " + oldName, "Not found", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Read new values (can be same as old)
                String newName = gui.txtTeamName.getText().trim();
                String newUni = gui.txtUniversity.getText().trim();
                double s1 = Double.parseDouble(gui.txtScore1.getText().trim());
                double s2 = Double.parseDouble(gui.txtScore2.getText().trim());
                double s3 = Double.parseDouble(gui.txtScore3.getText().trim());
                double[] newScores = new double[]{s1, s2, s3};
                String newCategory = (String) gui.categoryDropdown.getSelectedItem();

                Team updated;
                if (newCategory != null && newCategory.equalsIgnoreCase("Cyber Security")) {
                    updated = new CyberSecurityTeam(newName, newUni, newScores);
                } else {
                    updated = new AITeam(newName, newUni, newScores);
                }

                boolean ok = teamList.updateTeam(oldName, updated);
                if (ok) {
                    JOptionPane.showMessageDialog(gui, "Team updated successfully.", "Updated", JOptionPane.INFORMATION_MESSAGE);
                    gui.outputArea.append("Team '" + oldName + "' updated -> '" + newName + "'.\n");
                } else {
                    JOptionPane.showMessageDialog(gui, "Update failed for team: " + oldName, "Failed", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gui, "Please enter valid numeric scores.", "Input error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(gui, "Error updating team: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // ----- Save CSV -----
    class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Manager.saveToCSV expects TeamList; it handles file write & exceptions
                Manager.saveToCSV(teamList, "data/teams.csv");
                JOptionPane.showMessageDialog(gui, "Saved to data/teams.csv", "Saved", JOptionPane.INFORMATION_MESSAGE);
                gui.outputArea.append("Saved teams to CSV.\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(gui, "Failed to save: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // ----- Load CSV -----
    class LoadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                TeamList loaded = Manager.loadFromCSV("data/teams.csv");
                // Replace current list contents
                // Clear internal list and copy
                teamList.getAllTeams().clear();
                teamList.getAllTeams().addAll(loaded.getAllTeams());
                JOptionPane.showMessageDialog(gui, "Loaded from data/teams.csv", "Loaded", JOptionPane.INFORMATION_MESSAGE);
                gui.outputArea.append("Loaded teams from CSV. Total: " + teamList.getAllTeams().size() + "\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(gui, "Failed to load: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // ----- Show Report -----
    class ReportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                gui.outputArea.append("\n===== NATIONAL HACKATHON REPORT =====\n\n");

                double[] weights = {0.4, 0.4, 0.2};  // default weights

                for (Team t : teamList.getAllTeams()) {

                    double avg = t.calculateWeightedAverage(weights);

                    gui.outputArea.append(
                        "Team Name: " + t.getTeamName() + "\n" +
                        "University: " + t.getUniversityName() + "\n" +
                        "Category: " + t.getCategory() + "\n" +
                        "Scores: " + t.getScores()[0] + " | " + t.getScores()[1] + " | " + t.getScores()[2] + "\n" +
                        "Weighted Average: " + String.format("%.2f", avg) + "\n" +
                        "---------------------------------------\n\n"
                    );
                }

            } catch (Exception ex) {
                gui.outputArea.append("Error generating report: " + ex.getMessage() + "\n");
            }
        }
    }
    
    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = gui.txtTeamName.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(gui, 
                    "Please enter the team name to delete.",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            boolean deleted = teamList.deleteTeam(name);

            if (deleted) {
                JOptionPane.showMessageDialog(gui, 
                    "Team '" + name + "' has been deleted.",
                    "Deleted",
                    JOptionPane.INFORMATION_MESSAGE
                );
                gui.outputArea.append("Deleted team: " + name + "\n");
            } else {
                JOptionPane.showMessageDialog(gui, 
                    "Team not found: " + name,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    class ClearFormListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.txtTeamName.setText("");
            gui.txtUniversity.setText("");
            gui.txtScore1.setText("");
            gui.txtScore2.setText("");
            gui.txtScore3.setText("");
            gui.categoryDropdown.setSelectedIndex(0);

            JOptionPane.showMessageDialog(gui, 
                "Form cleared!",
                "Cleared",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
