package com.alexis.common.userType.standardUser;

import java.util.ArrayList;

import com.alexis.store.User;

public class StandardUser extends User {
  public StandardUser(String nickName,
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
