package com.alexis.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.alexis.pages.signin.*;
import com.alexis.pages.signup.Signup;
import com.alexis.pages.signup.informations.*;
import com.alexis.pages.signup.picture.Picture;
import com.alexis.store.Store;
import com.alexis.common.Utils;
import com.alexis.common.Component.*;

public class SigninFrame {
  
  private JFrame frame;
  private JPanel main;
  private Component component;

  public void changingToPicture() {
    if (this.component.getName() == "Informations") {
      this.component = new Picture("Picture", null);
      this.main = this.component.getPanel();
      this.frame.setTitle("Picture - SKOC");
      this.frame.getContentPane().removeAll();
      this.frame.getContentPane().add(this.main);
      this.frame.revalidate();
      this.frame.pack(); // 
      System.out.println("Picture !");
    }
  }

  public void changingToInformations() {
    if (this.component.getName() == "Signup") {
      this.component = new Informations("Informations", null);
      this.main = this.component.getPanel();
      this.frame.setTitle("Informations - SKOC");
      this.frame.getContentPane().removeAll();
      this.frame.getContentPane().add(this.main);
      this.frame.revalidate();
      this.frame.pack(); // 
      System.out.println("Informations !");
    }
  }

  public void changingToSignin() {
    if (this.component.getName() == "Signup") {
      this.component = new Signin("Signin", null);
      this.main = this.component.getPanel();
      this.frame.setTitle("Signin - SKOC");
      this.frame.getContentPane().removeAll();
      this.frame.getContentPane().add(this.main);
      this.frame.revalidate();
      this.frame.pack(); // 
      System.out.println("Signin !");
    }
  }

  public void changingToSignup() {
    if (this.component.getName() == "Signin") {
      this.component = new Signup("Signup", null);
      this.main = this.component.getPanel();
      this.frame.setTitle("Signup - SKOC");
      this.frame.getContentPane().removeAll(); //or .remove(previousPanel);
      this.frame.getContentPane().add(this.main);
      this.frame.revalidate(); // in- and validate in one !! 
      this.frame.pack(); // 
      System.out.println("Signup !");
    }
  }

  public JFrame getFrame() {
    return this.frame;
  }

  public SigninFrame() {
    this.frame = new JFrame("Signin - SKOC");
    this.frame.setBounds(300, 100, Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT);
    this.frame.setResizable(false);
    this.frame.setFocusable(true);
    this.frame.requestFocus();
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.component = new Signin("Signin", null);
    this.main = this.component.getPanel();
    this.frame.add(this.main);
    this.frame.pack();
    this.frame.setVisible(true);
  }
}
