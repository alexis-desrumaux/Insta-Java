package com.alexis.common.UserSaveFileParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

import com.alexis.store.User;
import com.alexis.store.User.USER_TYPE;

import java.util.List;

public class UserSaveFileParser {
  private ArrayList<String> saveFileRawContent;
  private Map<String, Map<String, String>> saveFileContent;
  private String filePath;
  private boolean isEmpty;

  public static enum SectionKeys {
    USER,
  }

  public static enum UserKeys {
    NickName,
    Password,
    Name,
    Surname,
    Age,
    Email,
    Hobbies,
    PP,
    AccountType,
  }

  public boolean isEmpty() {
    return this.isEmpty;
  }

  public String getPath() {
    return this.filePath;
  }

  public Map<String, String> getSection(SectionKeys key) {
    return this.saveFileContent.get(key.toString());
  }

  private boolean saveUserSection(FileWriter myWriter, User user) {
    try {
      String fileContent = "";
      String hobbies = "";
      fileContent += "#User\n";
      fileContent += user.getNickName() + '\n';
      fileContent += user.getPassword() + '\n';
      fileContent += user.getName() + '\n';
      fileContent += user.getSurname() + '\n';
      fileContent += Integer.toString(user.getAge()) + '\n';
      fileContent += user.getEmail() + '\n';
      for (int i = 0; i != user.getHobbies().size(); i += 1) {
        if (i != 0) {
          hobbies += ' ';
        }
        hobbies += user.getHobbies().get(i);
      }
      fileContent += hobbies + '\n';
      fileContent += user.getPPPath() + '\n';
      USER_TYPE userType = user.getUserType();
      if (userType == USER_TYPE.STANDARD) {
        fileContent += "STANDARD\n";
      } else {
        fileContent += "PREMIUM\n";
      }
      fileContent += "##\n";
      myWriter.write(fileContent);
      System.out.println("Successfully wrote to the file.");
      myWriter.close();
      return true;
    } catch (Exception e) {
      System.out.println("Error. Unable to save");
      e.printStackTrace();
      return false;
    }
  }

  private boolean save_fileExist(User user) {
    try {
      FileWriter myWriter = new FileWriter(this.filePath);
      return this.saveUserSection(myWriter, user);
    } catch (IOException e) {
      System.out.println("Error. Unable to save");
      e.printStackTrace();
      return false;
    }
  }

  public boolean save(User user) {
    File f = new File(this.filePath);
    if (f.exists() && !f.isDirectory()) {
      return this.save_fileExist(user);
    } else {
      try {
        f.getParentFile().mkdirs();
        f.createNewFile();
        return this.save_fileExist(user);
      } catch (Exception e) {
        System.out.println("Error. Unable to save");
        e.printStackTrace();
        return false;
      }
    }
  }

  private void parseUser() {
    int i = 0;
    Map<String, String> content = new HashMap<String, String>();
    for (i = 1; i != saveFileRawContent.size() && saveFileRawContent.get(i).equals("##") != true; i += 1) {
      switch (i) {
        case 1:
          content.put(UserKeys.NickName.toString(), saveFileRawContent.get(i));
          break;
        case 2:
          content.put(UserKeys.Password.toString(), saveFileRawContent.get(i));
          break;
        case 3:
          content.put(UserKeys.Name.toString(), saveFileRawContent.get(i));
          break;
        case 4:
          content.put(UserKeys.Surname.toString(), saveFileRawContent.get(i));
          break;
        case 5:
          content.put(UserKeys.Age.toString(), saveFileRawContent.get(i));
          break;
        case 6:
          content.put(UserKeys.Email.toString(), saveFileRawContent.get(i));
          break;
        case 7:
          content.put(UserKeys.Hobbies.toString(), saveFileRawContent.get(i));
          break;
        case 8:
          content.put(UserKeys.PP.toString(), saveFileRawContent.get(i));
          break;
        case 9:
          content.put(UserKeys.AccountType.toString(), saveFileRawContent.get(i));
          break;
        default:
          break;
      }
    }
    saveFileContent.put(SectionKeys.USER.toString(), content);
    this.isEmpty = false;
  }

  public UserSaveFileParser(String filePath) {
    this.saveFileRawContent = new ArrayList<String>();
    this.saveFileContent = new HashMap<String, Map<String, String>>();
    this.filePath = filePath;
    this.isEmpty = true;

    try {
      File myObj = new File(filePath);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        this.saveFileRawContent.add(data);
        this.parseUser();
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      return;
    }
  }
}
