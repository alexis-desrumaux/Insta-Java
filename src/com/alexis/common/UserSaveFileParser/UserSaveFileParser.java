package com.alexis.common.UserSaveFileParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class UserSaveFileParser {
  private ArrayList<String> saveFileRawContent;
  private Map<String, Map<String, String>> saveFileContent;

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

  public Map<String, String> getSection(SectionKeys key) {
    return this.saveFileContent.get(key.toString());
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
    //System.out.println(saveFileContent);
  }

  public UserSaveFileParser(String filePath) {
    this.saveFileRawContent = new ArrayList<String>();
    this.saveFileContent = new HashMap<String, Map<String, String>>();

    try {
      File myObj = new File(filePath);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        this.saveFileRawContent.add(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred. File not found or corrupted");
      e.printStackTrace();
      System.exit(84);
    }
    this.parseUser();
  }
}
