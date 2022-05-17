package com.alexis.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.alexis.common.Utils;
import com.alexis.pages.home.*;
import com.alexis.store.Store;

public class MainFrame {
  
  private JFrame frame;
  private JPanel main;
  private Home home;

  public JFrame getFrame() {
    return this.frame;
  }

  public MainFrame() {
    this.frame = new JFrame("Main - SKOC");
    this.frame.setBounds(300, 100, Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT);
    this.frame.setResizable(false);
    this.frame.setFocusable(true);
    this.frame.requestFocus();
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.home = new Home("Home", null);
    this.main = this.home;
    this.frame.add(this.main);
    this.frame.pack();
    this.frame.setVisible(true);

    /*while (true) {
      if (Store.getInstance().getFrameSelected() == Utils.Frame.MAIN)
        this.frame.dispose();
    }*/
  }
}
