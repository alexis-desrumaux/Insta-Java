package com.alexis.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.alexis.common.Utils;
import com.alexis.pages.home.*;
import com.alexis.pages.profile.Profile;
import com.alexis.store.Store;

public class MainFrame {
  
  private JFrame frame;
  private JPanel main;

  public JFrame getFrame() {
    return this.frame;
  }

  public void changingToProfile() {
    this.main = new Profile("Profile", null);
    this.frame.setTitle(Store.getInstance().getUser().getNickName() + " - SKOC");
    this.frame.getContentPane().removeAll(); // or .remove(previousPanel);
    this.frame.getContentPane().add(this.main);
    this.frame.revalidate(); // in- and validate in one !!
    this.frame.pack(); //
    System.out.println("Profile !");
  }

  public MainFrame() {
    this.frame = new JFrame("Main - SKOC");
    this.frame.setBounds(300, 100, Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT);
    this.frame.setResizable(false);
    this.frame.setFocusable(true);
    this.frame.requestFocus();
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.main = new Home("Home", null);
    this.frame.add(this.main);
    this.frame.pack();
    this.frame.setVisible(true);
  }
}
