package com.alexis.common.UserType.PremiumUser;

import java.util.ArrayList;

import com.alexis.common.Content.Content;
import com.alexis.store.User;

public class PremiumUser extends User {

  public PremiumUser(String nickName,
      String password,
      String name,
      String surname,
      int age,
      String email,
      ArrayList<String> hobbies,
      String ppPath, ArrayList<Content> contents, ArrayList<User> follows) {
    super(nickName, password, name, surname, age, email, hobbies, ppPath, contents, follows);
    this.accountType = USER_TYPE.PREMIUM;
  }
}
