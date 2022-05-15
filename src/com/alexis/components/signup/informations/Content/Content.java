package com.alexis.components.signup.informations.Content;

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
import com.alexis.db.InitDB;
import com.alexis.common.UserSaveFileParser.UserSaveFileParser;

public class Content extends com.alexis.common.Component.Component {
  private JLabel welcome;
  private JLabel message;
  private JLabel hobbiesTitle;
  private JLabel ageTitle;
  private JTextField nameField;
  private JTextField surnameField;
  private JComboBox<String> c1;
  private JComboBox<String> c2;
  private JComboBox<String> cAge;
  private JButton nextBtn;
  private String name;
  private String surname;
  private String hobbie1;
  private String hobbie2;
  private int age;
  private boolean dontUpdateName;
  private boolean dontUpdateSurname;
  private Components components;
  private LayoutBuilder layoutBuilder;

  private void handleOnClickNextButton() {
    ArrayList<String> hobbies = new ArrayList<String>();

    hobbies.add(hobbie1);
    hobbies.add(hobbie2);

    User user = Store.getInstance().getUser();
    user.setAge(this.age);
    user.setName(this.name);
    user.setSurname(this.surname);
    user.setHobbies(hobbies);
    System.out.println(user);
    UserSaveFileParser saveParser = new UserSaveFileParser(Utils.getSaveFilePathByUsername(user.getNickName()));
    saveParser.save(user);
    Store.getInstance().getApp().getSigninFrame().changingToPicture();
  }

  private void activateNextButton(boolean isActivated) {
    if (isActivated) {
      this.nextBtn.setBackground(Color.BLUE);
      this.nextBtn.setEnabled(true);
    } else {
      this.nextBtn.setBackground(Color.GRAY);
      this.nextBtn.setEnabled(false);
    }
  }

  private void handleOnChangeAge() {
    this.age = Integer.parseInt(cAge.getSelectedItem().toString());

    if (this.name.length() != 0 && this.surname.length() != 0 && this.age != -1 && this.hobbie1.length() != 0
        && this.hobbie2.length() != 0) {
      this.activateNextButton(true);
    } else {
      this.activateNextButton(false);
    }
  }

  private void handleOnChangeHobbie2() {
    this.hobbie2 = c2.getSelectedItem().toString();

    if (this.name.length() != 0 && this.surname.length() != 0 && this.age != -1 && this.hobbie1.length() != 0
        && this.hobbie2.length() != 0) {
      this.activateNextButton(true);
    } else {
      this.activateNextButton(false);
    }
  }

  private void handleOnChangeHobbie1() {
    this.hobbie1 = c1.getSelectedItem().toString();

    if (this.name.length() != 0 && this.surname.length() != 0 && this.age != -1 && this.hobbie1.length() != 0
        && this.hobbie2.length() != 0) {
      this.activateNextButton(true);
    } else {
      this.activateNextButton(false);
    }

  }

  private void handleOnChangeSurnameInput() {
    if (dontUpdateSurname == false) {
      this.surname = this.surnameField.getText();
      if (this.name.length() != 0 && this.surname.length() != 0 && this.age != -1 && this.hobbie1.length() != 0
          && this.hobbie2.length() != 0) {
        this.activateNextButton(true);
      } else {
        this.activateNextButton(false);
      }
    }
  }

  private void handleOnChangeNameInput() {
    if (dontUpdateName == false) {
      this.name = this.nameField.getText();
      if (this.name.length() != 0 && this.surname.length() != 0 && this.age != -1 && this.hobbie1.length() != 0
          && this.hobbie2.length() != 0) {
        this.activateNextButton(true);
      } else {
        this.activateNextButton(false);
      }
    }
  }

  private void initNextButton() {
    this.nextBtn = new JButton("Next");
    Point pos = layoutBuilder.next(0, 0);
    layoutBuilder.setPosition(new Point(0, pos.y));
    Point location = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, 350, 0).getX(), 0));
    this.nextBtn.setBounds((int) location.getX(), (int) location.getY(), 350, 50);
    this.nextBtn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.nextBtn.setBackground(Color.GRAY);
    this.nextBtn.setForeground(Color.WHITE);
    this.nextBtn.setEnabled(false);

    this.nextBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        handleOnClickNextButton();
      }
    });

    this.panel.add(this.nextBtn);
  }

  private void initAgeSelector() {
    ArrayList<String> ageList = new ArrayList<String>();

    for (int i = 18; i != 31; i += 1) {
      ageList.add(Integer.toString(i));
    }
    String[] options = ageList.toArray(new String[0]);
    this.cAge = new JComboBox<String>(options);
    int width = 200;
    int height = 50;
    layoutBuilder.changeAlign(LayoutBuilder.VERTICAL_ALIGN);
    Point pos = layoutBuilder.next(width, height,
        new Margin(30, 0, 20, 0));
    this.cAge.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    this.cAge.setBackground(Color.WHITE);
    this.cAge.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.cAge.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        // if the state combobox is changed
        if (e.getSource() == cAge) {
          handleOnChangeAge();
        }
      }
    });
    this.panel.add(this.cAge);
  }

  private void initAgeTitle() {
    this.ageTitle = new JLabel("Age:");
    Font f = new Font("BlinkMacSystemFont", Font.PLAIN, 20);
    this.ageTitle.setFont(f);
    int width = 100;
    int height = 25;
    layoutBuilder.changeAlign(LayoutBuilder.HORIZONTAL_ALIGN);
    Point pos = layoutBuilder.next(0, 0);
    layoutBuilder.setPosition(new Point(0, pos.y));
    pos = layoutBuilder.next(width, height,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.panel.getWidth(), 0, 320, 0).getX(), 0));
    this.ageTitle.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    // this.hobbiesTitle.setBackground(Color.YELLOW);
    // this.hobbiesTitle.setOpaque(true);
    this.panel.add(this.ageTitle);
  }

  private void initHobbies() {
    String[] options = { "Cars", "Music", "Memes", "VideoGames" };

    this.c1 = new JComboBox<String>(options);
    this.c2 = new JComboBox<String>(options);
    int width = 200;
    int height = 50;
    layoutBuilder.changeAlign(LayoutBuilder.HORIZONTAL_ALIGN);
    Point pos = layoutBuilder.next(width, height,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.panel.getWidth(), 0, 420, 0).getX(), 0));
    this.c1.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    layoutBuilder.changeAlign(LayoutBuilder.VERTICAL_ALIGN);
    Point pos2 = layoutBuilder.next(width, height,
        new Margin(40, 0, 20, 0));
    this.c2.setBounds((int) pos2.getX(), (int) pos2.getY(), width, height);
    this.c1.setBackground(Color.WHITE);
    this.c2.setBackground(Color.WHITE);
    this.c1.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.c2.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.c1.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        // if the state combobox is changed
        if (e.getSource() == c1) {
          handleOnChangeHobbie1();
        }
      }
    });
    this.c2.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        // if the state combobox is changed
        if (e.getSource() == c2) {
          handleOnChangeHobbie2();
        }
      }
    });
    this.panel.add(c1);
    this.panel.add(c2);
  }

  private void initHobbiesLabel() {
    this.hobbiesTitle = new JLabel("Hobbies:");
    Font f = new Font("BlinkMacSystemFont", Font.PLAIN, 20);
    this.hobbiesTitle.setFont(f);
    int width = 100;
    int height = 20;
    Point pos = layoutBuilder.next(width, height,
        new Margin(30, 0, LayoutHelper.getCenter(this.panel.getWidth(), this.panel.getHeight(), width, height).x, 0));
    this.hobbiesTitle.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    // this.hobbiesTitle.setBackground(Color.YELLOW);
    // this.hobbiesTitle.setOpaque(true);
    this.panel.add(this.hobbiesTitle);
  }

  private void initSurname() {
    this.surnameField = new JTextField();
    int width = 400;
    int height = 50;
    Point pos = layoutBuilder.next(width, height,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, width, 0).getX(), 0));
    this.surnameField.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    this.surnameField.setText("Surname");
    this.surnameField.setForeground(new Color(171, 171, 171));
    this.surnameField.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.surnameField.setBorder(BorderFactory.createCompoundBorder(
        this.surnameField.getBorder(),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));

    this.surnameField.addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent fe) {
        dontUpdateSurname = false;
        surnameField.setText(surname);
        surnameField.setForeground(Color.BLACK);
      }

      public void focusLost(FocusEvent fe) {
        if (surname.length() == 0) {
          dontUpdateSurname = true;
          surnameField.setText("Surname");
          surnameField.setForeground(new Color(171, 171, 171));
        }
      }
    });
    this.surnameField.getDocument().addDocumentListener(new SimpleDocumentListener() {
      @Override
      public void update(DocumentEvent e) {
        handleOnChangeSurnameInput();
      }
    });

    this.panel.add(this.surnameField);
  }

  private void initName() {
    this.nameField = new JTextField();
    int width = 400;
    int height = 50;
    Point pos = layoutBuilder.next(width, height,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.panel.getBounds().width, 0, width, 0).getX(), 0));
    this.nameField.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    this.nameField.setText("Name");
    this.nameField.setForeground(new Color(171, 171, 171));
    this.nameField.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.nameField.setBorder(BorderFactory.createCompoundBorder(
        this.nameField.getBorder(),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));

    this.nameField.addFocusListener(new FocusListener() {
      public void focusGained(FocusEvent fe) {
        dontUpdateName = false;
        nameField.setText(name);
        nameField.setForeground(Color.BLACK);
      }

      public void focusLost(FocusEvent fe) {
        if (name.length() == 0) {
          dontUpdateName = true;
          nameField.setText("Name");
          nameField.setForeground(new Color(171, 171, 171));
        }
      }
    });
    this.nameField.getDocument().addDocumentListener(new SimpleDocumentListener() {
      @Override
      public void update(DocumentEvent e) {
        handleOnChangeNameInput();
      }
    });

    this.panel.add(this.nameField);
  }

  private void initMessage() {
    this.message = new JLabel("Please fill the informations below");
    Font f = new Font("BlinkMacSystemFont", Font.PLAIN, 20);
    this.message.setFont(f);
    int width = 335;
    int height = 20;
    Point pos = layoutBuilder.next(width, height,
        new Margin(30, 0, LayoutHelper.getCenter(this.panel.getWidth(), this.panel.getHeight(), width, height).x, 0));
    this.message.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    // this.message.setBackground(Color.YELLOW);
    // this.message.setOpaque(true);
    this.panel.add(this.message);
  }

  private void initWelcome() {
    this.welcome = new JLabel(
        "Welcome " + Store.getInstance().getUser().getNickName());
    Font f = new Font("BlinkMacSystemFont", Font.PLAIN, 30);
    this.welcome.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    this.welcome.setBounds(0, 0, 0, 30);
    layoutBuilder.next(0, 30, new Margin(30, 0, 0, 0));
    this.panel.add(this.welcome);
  }

  private void initClassAttributes(Point pos) {
    this.components = new Components(this);
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.name = "";
    this.surname = "";
    this.age = 18;
    this.hobbie1 = "Cars";
    this.hobbie2 = "Cars";
    this.dontUpdateName = false;
    this.dontUpdateSurname = false;
    this.panel = new ContentPanel();
    this.panel.setBounds((int) pos.getX(), (int) pos.getY(), Utils.SCREEN_WIDTH, Utils.SCREEN_HEIGHT - 130);
    this.panel.setFocusable(true);
    this.panel.setLayout(null);
    this.panel.setBackground(Color.WHITE);
    this.panel.setOpaque(true);
  }

  public Content(String name, Components parent, Point pos) {
    super(name, parent);
    this.initClassAttributes(pos);
    this.initWelcome();
    this.initMessage();
    this.initName();
    this.initSurname();
    this.initHobbiesLabel();
    this.initHobbies();
    this.initAgeTitle();
    this.initAgeSelector();
    this.initNextButton();
  }

  public class ContentPanel extends JPanel {

    private void setPositionWelcome(Graphics g) {
      int width = g.getFontMetrics(welcome.getFont()).stringWidth(welcome.getText());
      int height = welcome.getBounds().height;
      LayoutBuilder b = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
      Point pos = b.next(width, height,
          new Margin(30, 0, LayoutHelper.getCenter(getWidth(), getHeight(), width, height).x, 0));

      welcome.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.setPositionWelcome(g);
    }
  }
}
