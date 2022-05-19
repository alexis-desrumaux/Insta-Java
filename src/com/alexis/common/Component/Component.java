package com.alexis.common.Component;

import com.alexis.common.CustomJPanel.*;
import java.awt.Color;
import com.alexis.common.ComponentProps.*;

public abstract class Component extends CustomJPanel {
  protected String name;
  protected Component parent;

  public Component getParentComponent() {
    return this.parent;
  }

  public abstract void updateProps(ComponentProps props);

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public Component(String name, Component parent, int radius, Color bgColor, int borderSize) {
    super(radius, bgColor, borderSize);
    this.name = name;
    this.parent = parent;
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
    super(true);
    this.name = name;
    this.parent = parent;
  }
}
