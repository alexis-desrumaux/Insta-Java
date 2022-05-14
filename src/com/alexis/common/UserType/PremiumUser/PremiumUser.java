package com.alexis.common.UserType.PremiumUser;

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
    this.accountType = USER_TYPE.PREMIUM;
  }
}
