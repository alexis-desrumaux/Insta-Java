package com.alexis.common.component;

import javax.swing.JPanel;

import com.alexis.common.components.*;

public abstract class Component {
  public String name;
  public Components parent;

  public abstract JPanel getPanel();

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
  }
}
