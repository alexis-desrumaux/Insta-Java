package com.alexis.common.Components;

import java.util.ArrayList;

import com.alexis.common.Component.*;

public class Components {
  private final ArrayList<Component> components = new ArrayList<Component>();
  private Component parent;

  public boolean deleteComponentByName(String name) {
    int index = -1;
    for (int i = 0; i != this.components.size(); i += 1) {
      if (this.components.get(i).getName().equals(name)) {
        index = i;
        break;
      }
    }
    if (index != -1) {
      this.components.remove(index);
      return true;
    }
    return false;
  }

  public Component getParent() {
    return parent;
  }

  public Component getComponentByName(String name) {
    for (Component component : components) {
      if (component.getName().equals(name))
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
