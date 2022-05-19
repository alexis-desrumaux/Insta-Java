package com.alexis.store;

import java.util.Arrays;
import java.util.List;

import com.alexis.common.UserType.PremiumUser.PremiumUser;
import com.alexis.common.UserType.StandardUser.StandardUser;
import com.alexis.common.Content.Content;
import com.alexis.common.UserSaveFileParser.UserSaveFileParser;
import com.alexis.common.UserSaveFileParser.UserSaveFileParser.SectionKeys;
import com.alexis.common.UserSaveFileParser.UserSaveFileParser.UserKeys;

import java.util.ArrayList;

public abstract class User {
  protected String nickName;
  protected String password;
  protected String name;
  protected String surname;
  protected int age;
  protected String email;
  protected ArrayList<String> hobbies;
  protected String ppPath;
  protected USER_TYPE accountType;
  protected ArrayList<Content> contents;

  public static enum USER_TYPE {
    STANDARD,
    PREMIUM,
  }

  @Override
  public String toString() {
    String s = "------------------\n";
    s += "nickName=" + this.nickName + '\n';
    s += "password=" + this.password + '\n';
    s += "name=" + this.name + '\n';
    s += "surname=" + this.surname + '\n';
    s += "age=" + this.age + '\n';
    s += "email=" + this.email + '\n';
    s += "hobbies=" + this.hobbies.toString() + '\n';
    s += "ppPath=" + this.ppPath + '\n';
    s += "accountType=" + this.accountType.toString();

    int i = 0;
    if (this.contents.size() != 0)
      s += '\n';
    for (Content c : this.contents) {
      if (i != 0) {
        s += '\n';
      }
      s += c.toString();
      i += 1;
    }
    return s;
  }

  public void setContents(ArrayList<Content> contents) {
    this.contents = contents;
  }

  public ArrayList<Content> getContents() {
    return this.contents;
  }

  public USER_TYPE getUserType() {
    return this.accountType;
  }

  public void setPPPath(String ppPath) {
    this.ppPath = ppPath;
  }

  public String getPPPath() {
    return this.ppPath;
  }

  public void setHobbies(ArrayList<String> hobbies) {
    this.hobbies = hobbies;
  }

  public ArrayList<String> getHobbies() {
    return this.hobbies;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return this.email;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getAge() {
    return this.age;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getSurname() {
    return this.surname;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getNickName() {
    return this.nickName;
  }

  public static ArrayList<Content> createUserByFile_createContents(UserSaveFileParser saveFile, User u) {
    ArrayList<Content> newContents = new ArrayList<Content>();
    for (int i = 0; i != saveFile.getContentsSection().size(); i += 1) {
      System.out.println(saveFile.getContentsSection().get(i).get(UserSaveFileParser.ContentKeys.TITLE.toString()));
      System.out
          .println(saveFile.getContentsSection().get(i).get(UserSaveFileParser.ContentKeys.CREATION_TIME.toString()));
      newContents.add(new Content(
          saveFile.getContentsSection().get(i).get(UserSaveFileParser.ContentKeys.TITLE.toString()),
          Long.parseLong(
              saveFile.getContentsSection().get(i).get(UserSaveFileParser.ContentKeys.CREATION_TIME.toString())),
          u,
          saveFile.getContentsSection().get(i).get(UserSaveFileParser.ContentKeys.TXT.toString()),
          saveFile.getContentsSection().get(i).get(UserSaveFileParser.ContentKeys.IMAGE_PATH.toString())));
    }
    return newContents;
  }

  public static User createUserByFile(UserSaveFileParser saveFile) {

    String accountType = saveFile.getUserSection().get(UserKeys.AccountType.toString());
    String hobbies = saveFile.getUserSection().get(UserKeys.Hobbies.toString());
    List<String> hobbiesList = Arrays.asList(hobbies.split(" "));
    ArrayList<String> hobbiesArrayList = new ArrayList<>();
    for (String h : hobbiesList)
      hobbiesArrayList.add(h);
    String pp = "";
    if (saveFile.getUserSection().get(UserKeys.PP.toString()).equals("NONE") == false) {
      pp = saveFile.getUserSection().get(UserKeys.PP.toString());
    }
    User user = null;
    if (accountType.equals("STANDARD")) {
      user = new StandardUser("", "", "", "", 0, "", new ArrayList<String>(), "", new ArrayList<Content>());
    } else if (accountType.equals("PREMIUM")) {
      user = new PremiumUser("", "", "", "", 0, "", new ArrayList<String>(), "", new ArrayList<Content>());
    } else {
      user = new StandardUser("", "", "", "", 0, "", new ArrayList<String>(), "", new ArrayList<Content>());
    }
    ArrayList<Content> newC = createUserByFile_createContents(saveFile, user);
    user.setNickName(saveFile.getUserSection().get(UserKeys.NickName.toString()));
    user.setPassword(saveFile.getUserSection().get(UserKeys.Password.toString()));
    user.setName(saveFile.getUserSection().get(UserKeys.Name.toString()));
    user.setSurname(saveFile.getUserSection().get(UserKeys.Surname.toString()));
    user.setAge(Integer.parseInt(saveFile.getUserSection().get(UserKeys.Age.toString())));
    user.setEmail(saveFile.getUserSection().get(UserKeys.Email.toString()));
    user.setHobbies(hobbiesArrayList);
    user.setPPPath(pp);
    user.setContents(newC);
    /*
     * if (user == null) {
     * System.out.println("NULL");
     * }
     */
    return user;
  }

  public User(String nickName,
      String password,
      String name,
      String surname,
      int age,
      String email,
      ArrayList<String> hobbies,
      String ppPath, ArrayList<Content> contents) {
    this.nickName = nickName;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.age = age;
    this.email = email;
    this.hobbies = hobbies;
    this.ppPath = ppPath;
    this.accountType = USER_TYPE.STANDARD;
    this.contents = contents;
  }
}
