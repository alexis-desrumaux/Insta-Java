package com.alexis.pages.signin;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import com.alexis.components.signin.LoginBox.LoginBox;
import com.alexis.store.Store;
import com.alexis.common.Utils;
import com.alexis.common.Component.*;
import com.alexis.common.Components.Components;
import com.alexis.frames.*;

public class Signin extends Component {
  private Components components;
  private boolean isLogged;

  public Signin(String name, Components parent) {
    super(name, parent);
    this.components = new Components(this);
    this.isLogged = false;
    this.panel = new SigninPanel();
    this.panel.setPreferredSize(new Dimension(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT));
    this.panel.setFocusable(true);
    this.panel.setLayout(null);
    //GridBagConstraints gbc = new GridBagConstraints();

    //gbc.gridx = 0;
    //gbc.gridy = 0;

    this.components.grabComponents().add(new LoginBox("LoginBox", this.components));
    
    for (Component c : this.components.grabComponents()) {
      this.panel.add(c.getPanel());
    }

    /*
     * JButton btn = new JButton("Je suis un boutton !");
     * this.panel.add(btn);
     * JButton btn2 = new JButton("Je suis un deuxième boutton !");
     * btn2.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * Store.getInstance().getApp().displayMainFrame();
     * }
     * });
     * this.panel.add(btn2);
     */
  }

  public class SigninPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2D = (Graphics2D)g;
      GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, 50, 700, Color.RED);
      g2D.setPaint(gradient);
      g2D.fillRect(0, 0, getWidth(), getHeight());
    }
  }
}
