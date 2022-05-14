package com.alexis.store;

import java.util.Arrays;
import java.util.List;

import com.alexis.common.UserType.PremiumUser.PremiumUser;
import com.alexis.common.UserType.StandardUser.StandardUser;
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
    return s;
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

  public static User createUserByFile(UserSaveFileParser saveFile) {

    String accountType = saveFile.getSection(SectionKeys.USER).get(UserKeys.AccountType.toString());
    String hobbies = saveFile.getSection(SectionKeys.USER).get(UserKeys.Hobbies.toString());
    List<String> hobbiesList = Arrays.asList(hobbies.split(" "));
    ArrayList<String> hobbiesArrayList = new ArrayList<>();
    for (String h : hobbiesList)
      hobbiesArrayList.add(h);
    String pp = "";
    if (saveFile.getSection(SectionKeys.USER).get(UserKeys.PP.toString()).equals("NONE") == false) {
      pp = saveFile.getSection(SectionKeys.USER).get(UserKeys.PP.toString());
    }
    User user = null;
    if (accountType.equals("STANDARD")) {
      user = new StandardUser(saveFile.getSection(SectionKeys.USER).get(UserKeys.NickName.toString()),
          saveFile.getSection(SectionKeys.USER).get(UserKeys.Password.toString()),
          saveFile.getSection(SectionKeys.USER).get(UserKeys.Name.toString()),
          saveFile.getSection(SectionKeys.USER).get(UserKeys.Surname.toString()),
          Integer.parseInt(saveFile.getSection(SectionKeys.USER).get(UserKeys.Age.toString())),
          saveFile.getSection(SectionKeys.USER).get(UserKeys.Email.toString()), hobbiesArrayList,
          pp);
      
    } else if (accountType.equals("PREMIUM")) {
      user = new PremiumUser(saveFile.getSection(SectionKeys.USER).get(UserKeys.NickName.toString()),
          saveFile.getSection(SectionKeys.USER).get(UserKeys.Password.toString()),
          saveFile.getSection(SectionKeys.USER).get(UserKeys.Name.toString()),
          saveFile.getSection(SectionKeys.USER).get(UserKeys.Surname.toString()),
          Integer.parseInt(saveFile.getSection(SectionKeys.USER).get(UserKeys.Age.toString())),
          saveFile.getSection(SectionKeys.USER).get(UserKeys.Email.toString()), hobbiesArrayList,
          pp);
    }
    /*if (user == null) {
      System.out.println("NULL");
    }*/
    return user;
  }

  public User(String nickName,
      String password,
      String name,
      String surname,
      int age,
      String email,
      ArrayList<String> hobbies,
      String ppPath) {
    this.nickName = nickName;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.age = age;
    this.email = email;
    this.hobbies = hobbies;
    this.ppPath = ppPath;
    this.accountType = USER_TYPE.STANDARD;
  }
}
