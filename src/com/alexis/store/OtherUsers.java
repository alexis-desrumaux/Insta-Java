package com.alexis.store;

import java.util.ArrayList;

public class OtherUsers {
  private ArrayList<User> otherUsers;

  @Override
  public String toString() {
    String s = "";
    int i = 0;

    for (User u : this.otherUsers) {
      if (i != 0)
        s += '\n';
      s += u;
      i += 1;
    }
    return s;
  }

  public boolean addUser(User newUser) {
    if (newUser != null) {
      System.out.println(newUser.getNickName());
      if (findUserByUsername(newUser.getNickName()) == null) {
        this.otherUsers.add(newUser);
        return true;
      }
      return false;
    }
    return false;
  }

  public User findUserByUsername(String username) {
    for (User u : otherUsers) {
      if (u.getNickName() == username)
        return u;
    }
    return null;
  }

  public OtherUsers() {
    this.otherUsers = new ArrayList<User>();
  }
}
