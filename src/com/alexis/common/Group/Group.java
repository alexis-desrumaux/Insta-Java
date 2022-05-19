package com.alexis.common.Group;

import java.util.ArrayList;

import com.alexis.common.Content.Content;
import com.alexis.store.User;

public class Group {
  private String name;
  private String country;
  private User owner;
  private String groupPicturePath;
  private ArrayList<String> hobbies;
  private ArrayList<User> followedBy;
  private ArrayList<Content> contents;

  public ArrayList<Content> getContents() {
    return this.contents;
  }

  public void setContents(ArrayList<Content> contents) {
    this.contents = contents;
  }

  public ArrayList<User> getFollowedBy() {
    return this.followedBy;
  }

  public void setFollowedBy(ArrayList<User> followedBy) {
    this.followedBy = followedBy;
  }

  public ArrayList<String> getHobbies() {
    return this.hobbies;
  }

  public void setHobbies(ArrayList<String> hobbies) {
    this.hobbies = hobbies;
  }

  public String getGroupPicturePath() {
    return this.groupPicturePath;
  }

  public void setGroupPicturePath(String path) {
    this.groupPicturePath = path;
  }

  public User getOwner() {
    return this.owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Group(String name, String country, User owner, ArrayList<String> hobbies, ArrayList<User> followedBy,
      ArrayList<Content> contents) {
    this.name = name;
    this.country = country;
    this.owner = owner;
    this.hobbies = hobbies;
    this.followedBy = followedBy;
    this.contents = contents;
  }

  public Group(String name, String country, User owner, ArrayList<String> hobbies, String groupePicturePath) {
    this.name = name;
    this.country = country;
    this.owner = owner;
    this.hobbies = hobbies;
    this.groupPicturePath = groupePicturePath;
  }
}
