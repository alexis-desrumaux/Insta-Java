package com.alexis.common.Component;

import com.alexis.common.CustomJPanel.*;
import java.awt.Color;

public abstract class Component extends CustomJPanel {
  protected String name;
  protected Component parent;
  //protected JPanel panel;

  /*public void setPanel(JPanel j) {
    this.panel = j;
  }

  public JPanel getPanel() {
    return this.panel;
  }*/

  public Component getParentComponent() {
    return this.parent;
  }

  //public abstract int run(int keyCode);

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public Component(String name, Component parent, int radius, Color bgColor) {
    super(radius, bgColor);
    this.name = name;
    this.parent = parent;
  }

  public Component(String name, Component parent, int radius) {
    super(radius);
    this.name = name;
    this.parent = parent;
  }

  public Component(String name, Component parent) {
    super(0);
    this.name = name;
    this.parent = parent;
  }
}
