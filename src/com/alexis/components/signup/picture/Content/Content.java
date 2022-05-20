package com.alexis.components.signup.picture.Content;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;

import com.alexis.common.Utils;
import com.alexis.common.ComponentProps.ComponentProps;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.common.LayoutHelper.*;
import com.alexis.store.Store;
import com.alexis.common.SimpleDocumentListener.*;
import com.alexis.store.*;
import com.alexis.common.UserSaveFileParser.UserSaveFileParser;
import com.alexis.db.InitDB;

import java.io.File;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Content extends com.alexis.common.Component.Component {
  private ContentProps props;
  private Image pp;
  private Point ppPos;
  private JLabel message;
  private String ppPath;
  private JButton browseBtn;
  private JButton nextBtn;
  private LayoutBuilder layoutBuilder;

  private void reinitPP() {
    try {
      ImageIcon ii = new ImageIcon(this.ppPath);
      this.pp = ii.getImage();
      this.pp = this.pp.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void handleOnClickNextButton() {
    User user = Store.getInstance().getUser();
    user.setPPPath(this.ppPath);
    UserSaveFileParser saveParser = new UserSaveFileParser(Utils.getSaveFilePathByUsername(user.getNickName()));
    saveParser.save(user);
    Store.getInstance().getApp().displayMainFrame();
  }

  private void handleOnClickBrowseButton() {
    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
    jfc.addChoosableFileFilter(filter);
    jfc.setAcceptAllFileFilterUsed(false);

    int returnValue = jfc.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = jfc.getSelectedFile();
      this.ppPath = selectedFile.getAbsolutePath();
      try {
        File orginalFile = new File(this.ppPath);
        File newFilePath = new File(Utils.PATH_USERDIR + Store.getInstance().getUser().getNickName() + File.separator + "pp." + orginalFile.getName().split("\\.(?=[^\\.]+$)")[1]);
        Files.copy(orginalFile.toPath(), newFilePath.toPath());
        this.ppPath = newFilePath.toPath().toString();
        this.reinitPP();
        this.repaint();
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  private void setStyleNextButton() {
    Point pos = layoutBuilder.next(0, 0);
    layoutBuilder.setPosition(new Point(0, pos.y));
    Point location = layoutBuilder.next(450, 50,
        new Margin(60, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, 450, 0).getX(), 0));
    this.nextBtn.setBounds((int) location.getX(), (int) location.getY(), 450, 50);
    this.nextBtn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.nextBtn.setBackground(Color.BLUE);
    this.nextBtn.setForeground(Color.WHITE);
    this.nextBtn.setVisible(true);
  }
  
  private void initNextButton() {
    this.nextBtn = new JButton("Next");
    this.nextBtn.setEnabled(true);

    this.nextBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        handleOnClickNextButton();
      }
    });
    this.nextBtn.setVisible(false);
    this.add(this.nextBtn);
  }

  private void setStyleBrowseButton() {
    Point pos = layoutBuilder.next(0, 0);
    layoutBuilder.setPosition(new Point(0, pos.y));
    Point location = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, 350, 0).getX(), 0));
    this.browseBtn.setBounds((int) location.getX(), (int) location.getY(), 350, 50);
    this.browseBtn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.browseBtn.setBackground(Color.GRAY);
    this.browseBtn.setForeground(Color.WHITE);
    this.browseBtn.setVisible(true);
  }

  private void initBrowseButton() {
    this.browseBtn = new JButton("Browse ...");
    this.browseBtn.setEnabled(true);

    this.browseBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        handleOnClickBrowseButton();
      }
    });
    this.browseBtn.setVisible(false);
    this.add(this.browseBtn);
  }

  private void setStyleMessage() {
    Font f = new Font("BlinkMacSystemFont", Font.PLAIN, 30);
    this.message.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    this.message.setFont(f);
    int width = 350;
    int height = 32;
    Point pos = layoutBuilder.next(width, height,
        new Margin(30, 0, LayoutHelper.getCenter(this.getWidth(), this.getHeight(), width, height).x, 0));
    this.message.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    this.message.setVisible(true);
  }

  private void initMessage() {
    this.message = new JLabel("Select a profile picture");
    this.message.setVisible(false);
    //this.message.setBackground(Color.YELLOW);
    //this.message.setOpaque(true);
    this.add(this.message);
  }

  private void setStylePP() {
    this.ppPos = layoutBuilder.next(200, 200,
          new Margin(20, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, 200, 0).getX(), 0));
  }

  private void initPP() {
    try {
      ImageIcon ii = new ImageIcon(this.ppPath);
      this.pp = ii.getImage();
      this.pp = this.pp.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.setStylePP();
    this.setStyleMessage();
    this.setStyleBrowseButton();
    this.setStyleNextButton();
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.setFocusable(true);
    this.setLayout(null);
    this.setBounds((int) props.position.getX(), (int) props.position.getY(), Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT - 130);
    this.setBackground(Color.WHITE);
    this.setOpaque(true);
    this.ppPath = "src/com/alexis/assets/defaultPP.png";
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    g2D.drawImage(pp, (int) ppPos.getX(), (int) ppPos.getY(), this);
  }

  public void updateProps(ComponentProps props) {
    try {
      if (props.getPropsTypeName().equals(ContentProps.TYPE_NAME)) {
        this.props = (ContentProps)props;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public Content(String name, com.alexis.common.Component.Component parent, ContentProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.initPP();
    this.initMessage();
    this.initBrowseButton();
    this.initNextButton();
    this.setStyleComponents();
  }
}
