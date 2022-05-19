package com.alexis.common.UserType.StandardUser;

import java.util.ArrayList;

import com.alexis.common.Content.Content;
import com.alexis.store.User;

public class StandardUser extends User {

  public StandardUser(String nickName,
      String password,
      String name,
      String surname,
      int age,
      String email,
      ArrayList<String> hobbies,
      String ppPath, ArrayList<Content> contents, ArrayList<User> follows) {
    super(nickName, password, name, surname, age, email, hobbies, ppPath, contents, follows);
    this.accountType = USER_TYPE.STANDARD;
  }

}
