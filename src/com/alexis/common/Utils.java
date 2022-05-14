package com.alexis.common;

public class Utils {
  public final static int SCREEN_WIDTH = 1200;
  public final static int SCREEN_HEIGHT = 800;
  public final static String PATH_USERDIR = "src/com/alexis/assets/users/";

  public static String getSaveFilePathByUsername(String username) {
    return PATH_USERDIR + username + "/infos.txt";
  }
}
