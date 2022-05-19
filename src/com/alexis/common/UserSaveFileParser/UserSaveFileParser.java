package com.alexis.common.UserSaveFileParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

import com.alexis.common.Content.Content;
import com.alexis.store.User;
import com.alexis.store.User.USER_TYPE;

import java.util.List;

public class UserSaveFileParser {
  private ArrayList<String> saveFileRawContent;
  private Map<String, String> userSection;
  private ArrayList<Map<String, String>> contentsSection;
  private String filePath;
  private boolean isEmpty;

  public static enum SectionKeys {
    USER,
    CONTENTS,
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

  public static enum ContentKeys {
    TITLE,
    CREATION_TIME,
    AUTHOR,
    TXT,
    IMAGE_PATH,
  }

  public boolean isEmpty() {
    return this.isEmpty;
  }

  public String getPath() {
    return this.filePath;
  }

  /*public Map<String, String> getSectionFrom(SectionKeys key) {
    return this.saveFileContent.get(key.toString());
  }*/

  public ArrayList<Map<String, String>> getContentsSection() {
    return this.contentsSection;
  }

  public Map<String, String> getUserSection() {
    return this.userSection;
  }

  private String saveContentsSection(FileWriter myWriter, User user, String fileContent) {
    ArrayList<Content> contents = user.getContents();

    fileContent += "#Contents\n";
    for (Content c : contents) {
      fileContent += "-Content\n";
      fileContent += c.getTitle() + '\n';
      fileContent += c.getCreationTime() + "\n";
      fileContent += user.getNickName() + '\n';
      fileContent += c.getTxt() + '\n';
      fileContent += c.getImagePath() + '\n';
      fileContent += "--\n";
    }
    fileContent += "##\n";
    return fileContent;
  }

  private String saveUserSection(FileWriter myWriter, User user, String fileContent) {
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
    return fileContent;
  }

  private boolean saveUser(FileWriter myWriter, User user) {
    try {
      String fileContent = "";
      fileContent = this.saveUserSection(myWriter, user, fileContent);
      fileContent = this.saveContentsSection(myWriter, user, fileContent);
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
      if (this.saveUser(myWriter, user) == false)
        return false;
      return true;
      //return this.saveUserSection(myWriter, user);
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

  private int parseContents(int o) {
    int i = o + 2;
    this.contentsSection = new ArrayList<Map<String, String>>();

    System.out.println(saveFileRawContent.get(o));
    if (saveFileRawContent.get(i).equals("##") == true) {
      return i;
    }
    i += 1;
    int j = 0;
    Map<String, String> content = new HashMap<String, String>();
    for (; i != saveFileRawContent.size() && saveFileRawContent.get(i).equals("##") != true; i += 1) {
      switch (j) {
        case 0:
          content.put(ContentKeys.TITLE.toString(), saveFileRawContent.get(i));
          break;
        case 1:
          content.put(ContentKeys.CREATION_TIME.toString(), saveFileRawContent.get(i));
          break;
        case 2:
          content.put(ContentKeys.AUTHOR.toString(), saveFileRawContent.get(i));
          break;
        case 3:
          content.put(ContentKeys.TXT.toString(), saveFileRawContent.get(i));
          break;
        case 4:
          content.put(ContentKeys.IMAGE_PATH.toString(), saveFileRawContent.get(i));
          break;
        default:
          break;
      }
      j += 1;
      if (saveFileRawContent.get(i).equals("--") == true) {
        this.contentsSection.add(content);
        content = new HashMap<String, String>();
        j = -1;
      }
    }
    if (content.isEmpty() == false)
      this.contentsSection.add(content);
    return i;
  }

  private int parseUser() {
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
    this.userSection = content;
    this.isEmpty = false;
    return i;
  }

  public UserSaveFileParser(String filePath) {
    this.saveFileRawContent = new ArrayList<String>();
    this.userSection = new HashMap<String, String>();
    this.filePath = filePath;
    this.isEmpty = true;

    try {
      File myObj = new File(filePath);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        this.saveFileRawContent.add(data);
      }
      this.parseContents(this.parseUser());
      myReader.close();
    } catch (FileNotFoundException e) {
      return;
    }
  }
}
