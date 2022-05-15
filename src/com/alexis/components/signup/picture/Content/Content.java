package com.alexis.components.signup.picture.Content;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;

import com.alexis.common.Utils;
import com.alexis.common.Components.Components;
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
  private Image pp;
  private Point ppPos;
  private JLabel message;
  private String ppPath;
  private JButton browseBtn;
  private JButton nextBtn;
  private Components components;
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
    System.out.println(user);
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
        this.panel.repaint();
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  private void initNextButton() {
    this.nextBtn = new JButton("Next");
    Point pos = layoutBuilder.next(0, 0);
    layoutBuilder.setPosition(new Point(0, pos.y));
    Point location = layoutBuilder.next(450, 50,
        new Margin(60, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 450, 0).getX(), 0));
    this.nextBtn.setBounds((int) location.getX(), (int) location.getY(), 450, 50);
    this.nextBtn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.nextBtn.setBackground(Color.BLUE);
    this.nextBtn.setForeground(Color.WHITE);
    this.nextBtn.setEnabled(true);

    this.nextBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        handleOnClickNextButton();
      }
    });
    this.panel.add(this.nextBtn);
  }

  private void initBrowseButton() {
    this.browseBtn = new JButton("Browse ...");
    Point pos = layoutBuilder.next(0, 0);
    layoutBuilder.setPosition(new Point(0, pos.y));
    Point location = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 350, 0).getX(), 0));
    this.browseBtn.setBounds((int) location.getX(), (int) location.getY(), 350, 50);
    this.browseBtn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.browseBtn.setBackground(Color.GRAY);
    this.browseBtn.setForeground(Color.WHITE);
    this.browseBtn.setEnabled(true);

    this.browseBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        handleOnClickBrowseButton();
      }
    });
    this.panel.add(this.browseBtn);
  }

  private void initMessage() {
    this.message = new JLabel("Select a profile picture");
    Font f = new Font("BlinkMacSystemFont", Font.PLAIN, 30);
    this.message.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    this.message.setFont(f);
    int width = 350;
    int height = 32;
    Point pos = layoutBuilder.next(width, height,
        new Margin(30, 0, LayoutHelper.getCenter(this.panel.getWidth(), this.panel.getHeight(), width, height).x, 0));
    this.message.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    //this.message.setBackground(Color.YELLOW);
    //this.message.setOpaque(true);
    this.panel.add(this.message);
  }

  private void initPP() {
    try {
      ImageIcon ii = new ImageIcon(this.ppPath);
      this.pp = ii.getImage();
      this.pp = this.pp.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
      this.ppPos = layoutBuilder.next(200, 200,
          new Margin(20, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 200, 0).getX(), 0));

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void initClassAttributes(Point pos) {
    this.components = new Components(this);
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.panel = new ContentPanel();
    this.panel.setBounds((int) pos.getX(), (int) pos.getY(), Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT - 130);
    this.panel.setFocusable(true);
    this.panel.setLayout(null);
    this.panel.setBackground(Color.WHITE);
    this.panel.setOpaque(true);
    this.ppPath = "src/com/alexis/assets/defaultPP.png";
  }

  public Content(String name, Components parent, Point pos) {
    super(name, parent);
    this.initClassAttributes(pos);
    this.initPP();
    this.initMessage();
    this.initBrowseButton();
    this.initNextButton();
  }

  public class ContentPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2D = (Graphics2D) g;
      // GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, 900, 130,
      // Color.RED);
      // g2D.setPaint(gradient);
      // g2D.fillRect(0, 0, getWidth(), getHeight());
      g2D.drawImage(pp, (int) ppPos.getX(), (int) ppPos.getY(), this);
    }
  }
}
