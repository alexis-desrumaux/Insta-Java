package com.alexis.store;

import java.util.ArrayList;

import com.alexis.common.Group.Group;

public class Groups {
  private ArrayList<Group> groups;

  public Group findGroupByName(String name) {
    for (Group u : groups) {
      if (u.getName().equals(name))
        return u;
    }
    return null;
  }

  public boolean addGroup(Group newGroup) {
    if (newGroup != null) {
      System.out.println(newGroup.getName());
      if (findGroupByName(newGroup.getName()) == null) {
        this.groups.add(newGroup);
        return true;
      }
      return false;
    }
    return false;
  }

  public Groups() {
    this.groups = new ArrayList<Group>();
  }
}
