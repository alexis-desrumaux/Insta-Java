package com.alexis.db;

import com.alexis.store.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

import com.alexis.common.Utils;
import com.alexis.common.UserSaveFileParser.UserSaveFileParser;
import com.alexis.common.UserType.StandardUser.*;

public class InitDB {

  public static void addUserToUsernameList(String username) {
    ArrayList<String> users = getRegisteredUsers();

    for (String user : users) {
      if (user.equals(username)) {
        return;
      }
    }
    users.add(username);
    try {
      FileWriter myWriter = new FileWriter(Utils.PATH_USERDIR + "users.txt");

      String fileContent = "";

      for (int i = 0; i != users.size(); i += 1) {
        if (i != 0) {
          fileContent += '\n';
        }
        fileContent += users.get(i);
      }
      myWriter.write(fileContent);
      myWriter.close();
    } catch (IOException e) {
      System.out.println("Error. Unable to add user to db");
      e.printStackTrace();
      System.exit(84);
    }
  }

  public static ArrayList<String> getRegisteredUsers() {
    String basePath = Utils.PATH_USERDIR;
    ArrayList<String> user = new ArrayList<String>();

    try {
      File myObj = new File(basePath + "users.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        user.add(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
      System.exit(84);
    }
    return user;
  }

  public static ArrayList<String> getUsersPath() {
    String basePath = Utils.PATH_USERDIR;
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
