package com.alexis.common.Component;

import javax.swing.JPanel;

import com.alexis.common.Components.*;

public abstract class Component {
  protected String name;
  protected Components parent;
  protected JPanel panel;

  public void setPanel(JPanel j) {
    this.panel = j;
  }

  public JPanel getPanel() {
    return this.panel;
  }

  public Components getParent() {
    return this.parent;
  }

  //public abstract int run(int keyCode);

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public Component(String name, Components parent) {
    this.name = name;
    this.parent = parent;
    this.panel = new JPanel();
  }
}
