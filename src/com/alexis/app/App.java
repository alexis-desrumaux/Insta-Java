package com.alexis.app;

import javax.swing.JFrame;

import com.alexis.common.Utils;
import com.alexis.frames.*;
import com.alexis.store.*;

public class App {
    private JFrame frame;
    private SigninFrame signinFrame;

    public SigninFrame getSigninFrame() {
        return this.signinFrame;
    }

    public void displaySigninFrame() {
        if (this.frame != null) {
            this.frame.dispose();
        }
        this.signinFrame = new SigninFrame();
        this.frame = this.signinFrame.getFrame();
    }

    public void displayMainFrame() {
        if (this.frame != null) {
            this.frame.dispose();
        }
        this.frame = new MainFrame().getFrame();
    }

    public App() {
        Store.getInstance();
        Store.getInstance().setApp(this);
        displaySigninFrame();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        new App();
    }
}
