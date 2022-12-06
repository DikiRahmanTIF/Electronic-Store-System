package com.kellima.pemdal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setContentPane(new MEOnlineStore().getRoot());
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(640,480);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }
}
