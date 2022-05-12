package com.alexis.common.userType.premiumUser;

import java.util.ArrayList;

import com.alexis.store.User;

public class PremiumUser extends User {
  public PremiumUser(String nickName,
      String password,
      String name,
      String surname,
      int age,
      String email,
      ArrayList<String> hobbies,
      String ppPath) {
    super(nickName, password, name, surname, age, email, hobbies, ppPath);
  }
}
