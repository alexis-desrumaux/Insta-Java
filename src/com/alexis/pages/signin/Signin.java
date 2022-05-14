package com.alexis.pages.signin;

import javax.swing.JPanel;
import java.awt.*;

import com.alexis.components.signin.LoginBox.LoginBox;
import com.alexis.store.Store;
import com.alexis.common.Utils;
import com.alexis.common.Components.Components;
import com.alexis.components._global.Notification.*;

public class Signin extends com.alexis.common.Component.Component {
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
    this.components.grabComponents().add(new LoginBox("LoginBox", this.components));
    for (com.alexis.common.Component.Component c : this.components.grabComponents()) {
      this.panel.add(c.getPanel());
    }
  }

  public class SigninPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2D = (Graphics2D) g;
      GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, 50, 700, Color.RED);
      g2D.setPaint(gradient);
      g2D.fillRect(0, 0, getWidth(), getHeight());
    }
  }
}
