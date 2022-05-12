package com.alexis.store;

import java.util.ArrayList;

import com.alexis.app.*;

public class Store {
  private static Store store;
  private App app;
  private User user;

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
    this.user = new User("", "", "", "", 0, "", new ArrayList<String>(), "");
  }
}
