package com.alexis.store;

import java.io.IOError;
import java.util.ArrayList;

import com.alexis.app.*;
import com.alexis.common.UserType.StandardUser.*;

public class Store {
  private static Store store;
  private App app;
  private User user;
  private OtherUsers otherUsers;

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
    this.user = new StandardUser("", "", "", "", 0, "", new ArrayList<String>(), "");
    this.otherUsers = new OtherUsers();
  }
}
