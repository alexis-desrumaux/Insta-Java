package com.alexis.common.Content;

import com.alexis.store.User;

public class Content {
  private String title;
  private long creationTime;
  private User author;
  private String txt;
  private String imagePath;

  @Override
  public String toString() {
    String s = "Content:\n";
    s += "title=" + title + '\n';
    s += "creationTime=" + creationTime + '\n';
    s += "author=" + author.getNickName() + '\n';
    s += "txt=" + txt + '\n';
    s += "imagePath=" + imagePath;
    return s;
  }

  public String getImagePath() {
    return this.imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public String getTxt() {
    return this.txt;
  }

  public void setTxt(String txt) {
    this.txt = txt;
  }

  public User getAuthor() {
    return this.author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public long getCreationTime() {
    return this.creationTime;
  }

  public void setCreationTime(long timestamp) {
    this.creationTime = timestamp;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Content(String title, long creationTime, User author, String txt, String imagePath) {
    this.title = title;
    this.creationTime = creationTime;
    this.author = author;
    this.txt = txt;
    this.imagePath = imagePath;
  }
}
