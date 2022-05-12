package com.alexis.store;

import java.util.ArrayList;

public class User {
  private String nickName;
  private String password;
  private String name;
  private String surname;
  private int age;
  private String email;
  private ArrayList<String> hobbies;
  private String ppPath;

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
  }
}
