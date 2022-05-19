package com.alexis.store;

import java.util.ArrayList;

import com.alexis.app.*;
import com.alexis.common.Content.Content;
import com.alexis.common.UserType.StandardUser.*;

public class Store {
  private static Store store;
  private App app;
  private User user;
  private OtherUsers otherUsers;
  private Groups groups;

  public Groups getGroups() {
    return this.groups;
  }

  public void setGroups(Groups groups) {
    this.groups = groups;
  }

  public void setOtherUsers(OtherUsers otherUsers) {
    this.otherUsers = otherUsers;
  }

  public OtherUsers getOtherUsers() {
    return this.otherUsers;
  }

  public App getApp() {
    return this.app;
  }

  public void setApp(App app) {
    this.app = app;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return this.user;
  }

  public static Store getInstance() {
    if (store != null)
      return store;
    store = new Store();
    return store;
  }

  public Store() {
    this.user = new StandardUser("", "", "", "", 0, "", new ArrayList<String>(), "", new ArrayList<Content>(), new ArrayList<User>());
    this.otherUsers = new OtherUsers();
    this.groups = new Groups();
  }
}
