package ru.dartinc;

import ru.dartinc.forms.MainForm;
import ru.dartinc.service.AppRussifier;

import javax.swing.*;

public class MainApp {
    private MainForm mainWindow= MainForm.getInstance();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }
}
