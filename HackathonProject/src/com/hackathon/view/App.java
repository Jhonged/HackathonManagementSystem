package com.hackathon.view;

import com.hackathon.controller.Controller;
import com.hackathon.model.TeamList;

public class App {
    public static void main(String[] args) {
        MainGUI gui = new MainGUI();
        TeamList list = new TeamList();
        new Controller(gui, list);
    }
}
