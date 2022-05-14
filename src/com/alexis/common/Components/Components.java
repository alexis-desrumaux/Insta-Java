package com.alexis.common.Components;

import java.util.ArrayList;

import com.alexis.common.Component.*;

public class Components {
  private final ArrayList<Component> components = new ArrayList<Component>();
  private Component parent;

  public Component getParent() {
    return parent;
  }

  public Component getComponentByName(String name) {
    for (Component component : components) {
      if (component.getName() == name)
        return component;
    }
    return null;
  }

  public final ArrayList<Component> grabComponents() {
    return this.components;
  }

  public Components(Component parent) {
    this.parent = parent;
  }

}
