package com.alexis.components.profile.ProfileChild.Posts;

import java.awt.*;
import javax.swing.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.File;
import java.sql.Timestamp;
import java.awt.Color;

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
import com.alexis.components.profile.ProfileChild.Posts.PostsScroll.PostsScroll;
import com.alexis.components.profile.ProfileChild.Posts.PostsScroll.PostsScrollProps;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Files;

public class Posts extends com.alexis.common.Component.Component {
  private PostsProps props;
  private LayoutBuilder layoutBuilder;
  private JTextField newContentInput;
  private JTextField newTitleInput;
  private JButton browseBtn;
  private JButton addNewContentBtn;
  private JScrollPane scrollPane;
  private PostsScroll postsScroll;
  private boolean dontUpdateNewContentInput;
  private boolean dontUpdateNewTitleInput;
  private String newContentTxt;
  private String newTitleTxt;
  private String imagePath;
  private String fileName;

  private void activateAddNewContentButton(boolean isActivated) {
    if (isActivated) {
      this.addNewContentBtn.setBackground(Color.BLUE);
      this.addNewContentBtn.setEnabled(true);
    } else {
      this.addNewContentBtn.setBackground(Color.GRAY);
      this.addNewContentBtn.setEnabled(false);
    }
  }

  private void handleOnClickAddNewContent() {
    Timestamp t = new Timestamp(System.currentTimeMillis());
    User user = Store.getInstance().getUser();
    ArrayList<Content> contents = user.getContents();

    try {
      if (this.fileName.equals("NONE") == false) {
        File orginalFile = new File(this.imagePath);
        File newFilePath = new File(Utils.PATH_USERDIR + Store.getInstance().getUser().getNickName() + File.separator
            + "Personal" + File.separator + "images" + File.separator + t.getTime() + '-' + this.fileName);

        if (!newFilePath.exists() && !newFilePath.isDirectory()) {
          newFilePath.getParentFile().mkdirs();
        }
        Files.copy(orginalFile.toPath(), newFilePath.toPath());
        this.imagePath = newFilePath.toPath().toString();
      }
      contents.add(new Content(this.newTitleTxt, t.getTime(), user, this.newContentTxt, this.imagePath));
      user.setContents(contents);
      UserSaveFileParser saveParser = new UserSaveFileParser(Utils.getSaveFilePathByUsername(user.getNickName()));
      saveParser.save(user);
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.newContentTxt = "";
    this.newTitleTxt = "";
    this.imagePath = "NONE";
    this.fileName = "NONE";
    this.dontUpdateNewContentInput = false;
    this.dontUpdateNewTitleInput = false;
    this.setStyleComponents();
  }

  private void handleOnClickBrowseButton() {
    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
    jfc.addChoosableFileFilter(filter);
    jfc.setAcceptAllFileFilterUsed(false);

    int returnValue = jfc.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = jfc.getSelectedFile();
      this.imagePath = selectedFile.getAbsolutePath();
      this.fileName = selectedFile.getName();
      this.setStyleComponents();
    }
  }

  private void handleOnChangeNewContentInput() {
    if (dontUpdateNewContentInput == false) {
      this.newContentTxt = this.newContentInput.getText();
      if (this.newTitleTxt.length() != 0 && this.newContentTxt.length() != 0)
        this.activateAddNewContentButton(true);
      else
        this.activateAddNewContentButton(false);
    }
  }

  private void handleOnChangeNewTitleInput() {
    if (dontUpdateNewTitleInput == false) {
      this.newTitleTxt = this.newTitleInput.getText();
      if (this.newTitleTxt.length() != 0 && this.newContentTxt.length() != 0)
        this.activateAddNewContentButton(true);
      else
        this.activateAddNewContentButton(false);
    }
  }

  private void setStyleScroll() {
    Point p = this.layoutBuilder.next(0, 0);
    this.layoutBuilder.setPosition(new Point(0, p.y));
    Point pos = this.layoutBuilder.next(800, 370);
    this.scrollPane.setBounds((int)pos.getX(), (int)pos.getY(), 800, 370);
    this.scrollPane.setBorder(null);
    this.postsScroll.updateContents();
  }

  private void initScroll() {
    this.postsScroll = new PostsScroll("PostsScroll", this, new PostsScrollProps());
    this.scrollPane = new JScrollPane(this.postsScroll);
    this.add(scrollPane);
  }

  private void setStyleAddNewContentButton() {
    this.layoutBuilder.changeAlign(LayoutBuilder.VERTICAL_ALIGN);
    Point location = layoutBuilder.next(100, 30,
        new Margin(20, 0, 20, 0));
    this.addNewContentBtn.setBounds((int) location.getX(), (int) location.getY(), 100, 30);
    this.addNewContentBtn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 14));
    this.addNewContentBtn.setBackground(Color.GRAY);
    this.addNewContentBtn.setForeground(Color.WHITE);
    this.addNewContentBtn.setVisible(true);
  }

  private void initAddNewContentButton() {
    this.addNewContentBtn = new JButton("Post");
    this.addNewContentBtn.setEnabled(false);

    this.addNewContentBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        handleOnClickAddNewContent();
      }
    });
    this.addNewContentBtn.setVisible(false);
    this.add(this.addNewContentBtn);
  }

  private void setStyleBrowseButton() {
    Point location = layoutBuilder.next(100, 30,
        new Margin(20, 0, 20, 0));
    this.browseBtn.setBounds((int) location.getX(), (int) location.getY(), 100, 30);
    this.browseBtn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 14));

    if (this.imagePath.equals("NONE")) {
      this.browseBtn.setBackground(Color.GRAY);
      this.browseBtn.setText("Browse ...");
    } else {
      this.browseBtn.setBackground(Color.BLUE);
      this.browseBtn.setText(this.fileName);
    }

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

  private void setStyleNewContentInput() {
    Point nField = layoutBuilder.next(350, 30,
        new Margin(20, 0, 20, 0));
    this.newContentInput.setBounds((int) nField.getX(), (int) nField.getY(), 350, 30);
    this.newContentInput.setForeground(new Color(171, 171, 171));
    this.newContentInput.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 14));
    if (this.newContentTxt.length() != 0) {
      this.newContentInput.setText(this.newContentTxt);
      this.newContentInput.setForeground(Color.BLACK);
    }
    this.newContentInput.setMargin(new Insets(0, 5, 0, 0));
    this.newContentInput.setVisible(true);
  }

  private void initNewContentInput() {
    this.newContentInput = new JTextField("Write something");

    this.newContentInput.addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent fe) {
        dontUpdateNewContentInput = false;
        newContentInput.setText(newContentTxt);
        newContentInput.setForeground(Color.BLACK);
      }

      public void focusLost(FocusEvent fe) {
        if (newContentTxt.length() == 0) {
          dontUpdateNewContentInput = true;
          newContentInput.setText("Write something");
          newContentInput.setForeground(new Color(171, 171, 171));
        }
      }
    });
    this.newContentInput.getDocument().addDocumentListener(new SimpleDocumentListener() {
      @Override
      public void update(DocumentEvent e) {
        handleOnChangeNewContentInput();
      }
    });
    this.newContentInput.setVisible(false);
    this.add(this.newContentInput);
  }

  private void setStyleNewTitleInput() {
    Point nField = layoutBuilder.next(100, 30,
        new Margin(20, 0, 20, 0));
    this.newTitleInput.setBounds((int) nField.getX(), (int) nField.getY(), 100, 30);
    this.newTitleInput.setForeground(new Color(171, 171, 171));
    this.newTitleInput.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 14));
    if (this.newTitleTxt.length() != 0) {
      this.newTitleInput.setText(this.newTitleTxt);
      this.newTitleInput.setForeground(Color.BLACK);
    }
    this.newTitleInput.setMargin(new Insets(0, 5, 0, 0));
    this.newTitleInput.setVisible(true);
  }

  private void initNewTitleInput() {
    this.newTitleInput = new JTextField("Title");

    this.newTitleInput.addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent fe) {
        dontUpdateNewTitleInput = false;
        newTitleInput.setText(newTitleTxt);
        newTitleInput.setForeground(Color.BLACK);
      }

      public void focusLost(FocusEvent fe) {
        if (newTitleTxt.length() == 0) {
          dontUpdateNewTitleInput = true;
          newTitleInput.setText("Title");
          newTitleInput.setForeground(new Color(171, 171, 171));
        }
      }
    });
    this.newTitleInput.getDocument().addDocumentListener(new SimpleDocumentListener() {
      @Override
      public void update(DocumentEvent e) {
        handleOnChangeNewTitleInput();
      }
    });
    this.newTitleInput.setVisible(false);
    this.add(this.newTitleInput);
  }

  private void setStylePanel() {
    this.setLayout(null);
    this.setOpaque(true);
    this.setFocusable(true);
    this.setBounds(this.props.position.x, this.props.position.y, 800, 420);
    this.setBackground(new Color(241, 243, 245));
    this.setVisible(true);
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.HORIZONTAL_ALIGN);
    this.setStylePanel();
    this.setStyleNewTitleInput();
    this.setStyleNewContentInput();
    this.setStyleBrowseButton();
    this.setStyleAddNewContentButton();
    this.setStyleScroll();
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.HORIZONTAL_ALIGN);
    this.newContentTxt = "";
    this.newTitleTxt = "";
    this.imagePath = "NONE";
    this.fileName = "NONE";
    this.dontUpdateNewContentInput = false;
    this.dontUpdateNewTitleInput = false;
  }

  public void updateProps(ComponentProps newProps) {
    try {
      if (newProps.getPropsTypeName().equals(PostsProps.TYPE_NAME)) {
        this.props = (PostsProps) newProps;
        this.setStyleComponents();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Posts(String name, com.alexis.common.Component.Component parent, PostsProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.initNewTitleInput();
    this.initNewContentInput();
    this.initBrowseButton();
    this.initAddNewContentButton();
    this.initScroll();
    this.setStyleComponents();
  }
}
