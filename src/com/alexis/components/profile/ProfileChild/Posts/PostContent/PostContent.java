package com.alexis.components.profile.ProfileChild.Posts.PostContent;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.awt.Color;
import java.awt.image.BufferedImage;

import com.alexis.common.LayoutHelper.*;
import com.alexis.common.Utils;
import com.alexis.common.ComponentProps.ComponentProps;
import com.alexis.common.Content.Content;
import com.alexis.common.LayoutBuilder.*;
import com.alexis.store.Store;
import com.alexis.store.User;
import com.alexis.common.SimpleDocumentListener.*;
import com.alexis.common.UserSaveFileParser.UserSaveFileParser;
import com.alexis.components._global.Notification.Notification;
import com.alexis.components.profile.ProfileChild.Posts.PostContent.PostContentProps;
import com.alexis.components.profile.ProfileChild.Posts.PostsScroll.PostsScroll;
import com.alexis.components.profile.ProfileChild.Posts.PostsScroll.PostsScrollProps;

import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Files;

public class PostContent extends com.alexis.common.Component.Component {
  private PostContentProps props;
  private LayoutBuilder layoutBuilder;
  private Image pp;
  private Point ppPos;
  private JLabel name;
  private Rectangle nameRec;
  private JLabel title;
  private Rectangle titleRec;
  private JLabel username;
  private Rectangle usernameRec;
  private JLabel time;
  private Rectangle timeRec;
  private Image image;
  private Rectangle imageRec;
  private Point imagePos;
  private JLabel txt;
  private Rectangle txtRec;

  public PostContentProps getProps() {
    return this.props;
  }

  private void setStyleTxt() {
    this.txt.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, this.txtRec.height));
    Font f = this.txt.getFont();
    this.txt.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    this.layoutBuilder.changeAlign(LayoutBuilder.HORIZONTAL_ALIGN);
    Point tPos = layoutBuilder.next(txtRec.width, txtRec.height,
        new Margin(75, 0, 75, 0));
    this.txt.setBounds((int) tPos.getX(), (int) tPos.getY(), this.txtRec.width,
        this.txtRec.height);
    this.txt.setForeground(Color.BLACK);
    this.txt.setOpaque(false);
    this.txt.setVisible(true);
  }

  private void initTxt() {
    this.txt = new JLabel(this.props.content.getTxt());
    this.txtRec = new Rectangle(0, 0, 0, 12);
    this.txt.setVisible(false);
    this.add(this.txt);
  }

  private void setStyleImage() {
    if (this.props.content.getImagePath().equals("NONE") == false) {
      Point p = this.layoutBuilder.next(0, 0);
      this.layoutBuilder.setPosition(new Point(0, p.y));
      this.layoutBuilder.changeAlign(LayoutBuilder.HORIZONTAL_ALIGN);
      this.imagePos = layoutBuilder.next(this.imageRec.width, 75, new Margin(50, 0, 15, 0));
    }
  }

  private void initImage() {
    if (this.props.content.getImagePath().equals("NONE") == false) {
      try {
        ImageIcon ii = new ImageIcon(this.props.content.getImagePath());
        this.image = ii.getImage();
        final BufferedImage bi = ImageIO.read(new File(this.props.content.getImagePath()));
        this.imageRec = new Rectangle(0, 0, (bi.getWidth() * 75) / bi.getHeight(), 0);
        this.image = this.image.getScaledInstance(imageRec.width, 75, Image.SCALE_SMOOTH);
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }

  private void setStyleTime() {
    this.time.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, this.timeRec.height));
    Font f = this.time.getFont();
    this.time.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    Point tPos = layoutBuilder.next(timeRec.width, timeRec.height,
        new Margin(10, 0, 20, 0));
    this.layoutBuilder.changeAlign(LayoutBuilder.VERTICAL_ALIGN);
    this.time.setBounds((int) tPos.getX(), (int) tPos.getY(), this.timeRec.width,
        this.timeRec.height);
    this.time.setForeground(Color.BLACK);
    this.time.setOpaque(false);
    this.time.setVisible(true);
  }

  private void initTime() {
    Timestamp t = new Timestamp(this.props.content.getCreationTime());
    this.time = new JLabel(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(t));
    this.timeRec = new Rectangle(0, 0, 0, 9);
    this.time.setVisible(false);
    this.add(this.time);
  }

  private void setStyleUsername() {
    Point p = this.layoutBuilder.next(0, 0);
    this.layoutBuilder.setPosition(new Point(0, p.y));
    this.username.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, this.usernameRec.height));
    Font f = this.username.getFont();
    this.username.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    this.layoutBuilder.changeAlign(LayoutBuilder.HORIZONTAL_ALIGN);
    Point tPos = layoutBuilder.next(usernameRec.width, usernameRec.height,
        new Margin(10, 0, 75, 0));
    this.username.setBounds((int) tPos.getX(), (int) tPos.getY(), this.usernameRec.width,
        this.usernameRec.height);
    this.username.setForeground(Color.BLACK);
    this.username.setOpaque(false);
    this.username.setVisible(true);
  }

  private void initUsername() {
    this.username = new JLabel(this.props.content.getAuthor().getNickName());
    this.usernameRec = new Rectangle(0, 0, 0, 9);
    this.username.setVisible(false);
    this.add(this.username);
  }

  private void setStyleTitle() {
    this.title.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, this.titleRec.height));
    Font f = this.title.getFont();
    this.title.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    this.layoutBuilder.changeAlign(LayoutBuilder.VERTICAL_ALIGN);
    Point titlePos = layoutBuilder.next(titleRec.width, titleRec.height,
        new Margin(20, 0, getWidth() - this.layoutBuilder.next(0, 0).x - this.titleRec.width - 30, 0));
    this.title.setBounds((int) titlePos.getX(), (int) titlePos.getY(), this.titleRec.width,
        this.titleRec.height);
    this.title.setForeground(Color.BLACK);
    this.title.setOpaque(false);
    this.title.setVisible(true);
  }

  private void initTitle() {
    this.title = new JLabel(this.props.content.getTitle());
    this.titleRec = new Rectangle(0, 0, 0, 13);
    this.title.setVisible(false);
    this.add(this.title);
  }

  private void setStyleName() {
    this.name.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, this.nameRec.height));
    Font f = this.name.getFont();
    this.name.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    Point titlePos = layoutBuilder.next(nameRec.width, nameRec.height,
        new Margin(20, 0, 20, 0));
    this.name.setBounds((int) titlePos.getX(), (int) titlePos.getY(), this.nameRec.width,
        this.nameRec.height);
    this.name.setForeground(Color.BLACK);
    this.name.setOpaque(false);
    this.name.setVisible(true);
  }

  private void initName() {
    this.name = new JLabel(
        this.props.content.getAuthor().getName() + " " + this.props.content.getAuthor().getSurname());
    this.nameRec = new Rectangle(0, 0, 0, 13);
    this.name.setVisible(false);
    this.add(this.name);
  }

  private void setStylePP() {
    this.ppPos = layoutBuilder.next(40, 40, new Margin(15, 0, 15, 0));
  }

  private void initPP() {
    try {
      ImageIcon ii = new ImageIcon(Store.getInstance().getUser().getPPPath());
      this.pp = ii.getImage();
      this.pp = this.pp.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(true);
    this.setFocusable(true);
    this.setBounds(this.props.position.x, this.props.position.y, 700, 200);
    this.setBackground(Color.WHITE);
    this.setVisible(true);
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.HORIZONTAL_ALIGN);
    this.setStylePanel();
    this.setStylePP();
    this.setStyleName();
    this.setStyleTitle();
    this.setStyleUsername();
    this.setStyleTime();
    this.setStyleImage();
    this.setStyleTxt();
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.HORIZONTAL_ALIGN);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;

    this.nameRec.width = g.getFontMetrics(this.name.getFont()).stringWidth(this.name.getText());
    this.titleRec.width = g.getFontMetrics(this.title.getFont()).stringWidth(this.title.getText());
    this.usernameRec.width = g.getFontMetrics(this.username.getFont()).stringWidth(this.username.getText());
    this.timeRec.width = g.getFontMetrics(this.time.getFont()).stringWidth(this.time.getText());
    this.txtRec.width = g.getFontMetrics(this.txt.getFont()).stringWidth(this.txt.getText());
    this.setStyleComponents();
    g2D.drawImage(this.pp, (int) this.ppPos.getX(), (int) this.ppPos.getY(), this);

    if (this.props.content.getImagePath().equals("NONE") == false)
      g2D.drawImage(this.image, (int) this.imagePos.getX(), (int) this.imagePos.getY(), this);
  }

  @Override
  public void updateProps(ComponentProps newProps) {
    try {
      if (newProps.getPropsTypeName().equals(PostContentProps.TYPE_NAME)) {
        this.props = (PostContentProps) newProps;
        this.setStyleComponents();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public PostContent(String name, com.alexis.common.Component.Component parent, PostContentProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.initPP();
    this.initName();
    this.initTitle();
    this.initUsername();
    this.initTime();
    this.initImage();
    this.initTxt();
    this.setStyleComponents();
  }
}
