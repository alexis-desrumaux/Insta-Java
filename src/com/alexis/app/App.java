/* Pledge of Honor *************************************************************************************
I hereby certify that I have completed this programming project on my own without any help from
anyone else. The effort in the project thus belongs completely to me. I did not search for a
solution, or I did not consult to any program written by others or did not copy any program from
other sources. I read and followed the guidelines provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: Alexis Desrumaux, 0078296
************************************************************************************************************/

package com.alexis.app;

import javax.swing.JFrame;

import com.alexis.common.Utils;
import com.alexis.frames.*;
import com.alexis.store.*;
import com.alexis.db.*;

public class App {
    private JFrame frame;
    private SigninFrame signinFrame;
    private MainFrame mainFrame;

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }

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
        this.mainFrame = new MainFrame();
        this.frame = this.mainFrame.getFrame();
    }

    public App() {
        Store.getInstance();
        Store.getInstance().setApp(this);
        InitDB.initUsers();
        displaySigninFrame();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        new App();
    }
}
