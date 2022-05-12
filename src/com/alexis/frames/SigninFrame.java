package com.alexis.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.alexis.pages.signin.*;
import com.alexis.store.Store;
import com.alexis.common.Utils;

public class SigninFrame {
  
  private JFrame frame;
  private JPanel main;
  private Signin signin;

  public JFrame getFrame() {
    return this.frame;
  }

  public SigninFrame() {
    this.frame = new JFrame("Signin - SKOC");
    this.frame.setBounds(300, 100, Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT);
    this.frame.setResizable(false);
    this.frame.setFocusable(true);
    this.frame.requestFocus();
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.signin = new Signin("Signin", null);
    this.main = this.signin.getPanel();
    this.frame.add(this.main);
    this.frame.pack();
    this.frame.setVisible(true);
  }
}
