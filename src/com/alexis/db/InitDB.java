package com.alexis.db;

import com.alexis.store.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import com.alexis.common.UserSaveFileParser.UserSaveFileParser;
import com.alexis.common.UserType.StandardUser.*;

public class InitDB {

  private static ArrayList<String> getUsersPath() {
    String basePath = "src/com/alexis/assets/users/";
    ArrayList<String> userPaths = new ArrayList<String>();

    try {
      File myObj = new File(basePath + "users.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        userPaths.add(basePath + data + "/infos.txt");
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
      System.exit(84);
    }
    return userPaths;
  }

  public static void initUsers() {
    ArrayList<String> userPaths = getUsersPath();
    for (String userPath : userPaths) {
      Store.getInstance().getOtherUsers().addUser(User.createUserByFile(new UserSaveFileParser(userPath)));
    }
    System.out.println(Store.getInstance().getOtherUsers());
  }
}
