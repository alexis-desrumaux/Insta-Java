package com.alexis.pages.signup;

import java.awt.*;

import com.alexis.common.Utils;
import com.alexis.components.signup.SignupBox.*;

public class Signup extends com.alexis.common.Component.Component {

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, 50, 700, Color.RED);
    g2D.setPaint(gradient);
    g2D.fillRect(0, 0, getWidth(), getHeight());
  }

  public Signup(String name, com.alexis.common.Component.Component parent) {
    super(name, parent);
    this.setPreferredSize(new Dimension(Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT));
    this.setFocusable(true);
    this.setLayout(null);
    SignupBox signupBox = new SignupBox("SignupBox", this,
        new Props(new Point((Utils.SCREEN_WIDTH / 2) - (700 / 2), (Utils.SCREEN_HEIGHT / 2) - (600 / 2))));
    this.add(signupBox);
  }
}
