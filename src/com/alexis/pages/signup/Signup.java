package com.alexis.pages.signup;

import javax.swing.JPanel;
import java.awt.*;

import com.alexis.common.Utils;
import com.alexis.common.Components.Components;
import com.alexis.components.signup.SignupBox.*;

public class Signup extends com.alexis.common.Component.Component {
  private Components components;
  private boolean isLogged;

  public Signup(String name, Components parent) {
    super(name, parent);
    this.components = new Components(this);
    this.isLogged = false;
    this.panel = new SignupPanel();
    this.panel.setPreferredSize(new Dimension(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT));
    this.panel.setFocusable(true);
    this.panel.setLayout(null);
    this.components.grabComponents().add(new SignupBox("SignupBox", this.components));
    for (com.alexis.common.Component.Component c : this.components.grabComponents()) {
      this.panel.add(c.getPanel());
    }
  }

  public class SignupPanel extends JPanel {
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
