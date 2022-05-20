package com.alexis.components.signup.informations.Content;

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

public class Content extends com.alexis.common.Component.Component {
  private ContentProps props;
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

  private void setStyleNextButton() {
    Point pos = layoutBuilder.next(0, 0);
    layoutBuilder.setPosition(new Point(0, pos.y));
    Point location = layoutBuilder.next(350, 50,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, 350, 0).getX(), 0));
    this.nextBtn.setBounds((int) location.getX(), (int) location.getY(), 350, 50);
    this.nextBtn.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.nextBtn.setBackground(Color.GRAY);
    this.nextBtn.setForeground(Color.WHITE);
    this.nextBtn.setVisible(true);
  }

  private void initNextButton() {
    this.nextBtn = new JButton("Next");
    this.nextBtn.setEnabled(false);

    this.nextBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        handleOnClickNextButton();
      }
    });
    this.nextBtn.setVisible(false);
    this.add(this.nextBtn);
  }

  private void setStyleAgeSelector() {
    int width = 200;
    int height = 50;
    layoutBuilder.changeAlign(LayoutBuilder.VERTICAL_ALIGN);
    Point pos = layoutBuilder.next(width, height,
        new Margin(30, 0, 20, 0));
    this.cAge.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    this.cAge.setBackground(Color.WHITE);
    this.cAge.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.cAge.setVisible(true);
  }

  private void initAgeSelector() {
    ArrayList<String> ageList = new ArrayList<String>();

    for (int i = 18; i != 31; i += 1) {
      ageList.add(Integer.toString(i));
    }
    String[] options = ageList.toArray(new String[0]);
    this.cAge = new JComboBox<String>(options);
    this.cAge.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        // if the state combobox is changed
        if (e.getSource() == cAge) {
          handleOnChangeAge();
        }
      }
    });
    this.cAge.setVisible(false);
    this.add(this.cAge);
  }

  private void setStyleAgeTitle() {
    Font f = new Font("BlinkMacSystemFont", Font.PLAIN, 20);
    this.ageTitle.setFont(f);
    int width = 100;
    int height = 25;
    layoutBuilder.changeAlign(LayoutBuilder.HORIZONTAL_ALIGN);
    Point pos = layoutBuilder.next(0, 0);
    layoutBuilder.setPosition(new Point(0, pos.y));
    pos = layoutBuilder.next(width, height,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.getWidth(), 0, 320, 0).getX(), 0));
    this.ageTitle.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    this.ageTitle.setVisible(true);
  }

  private void initAgeTitle() {
    this.ageTitle = new JLabel("Age:");
    // this.hobbiesTitle.setBackground(Color.YELLOW);
    // this.hobbiesTitle.setOpaque(true);
    this.ageTitle.setVisible(false);
    this.add(this.ageTitle);
  }

  private void setStyleHobbies() {
    int width = 200;
    int height = 50;
    layoutBuilder.changeAlign(LayoutBuilder.HORIZONTAL_ALIGN);
    Point pos = layoutBuilder.next(width, height,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.getWidth(), 0, 420, 0).getX(), 0));
    this.c1.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    layoutBuilder.changeAlign(LayoutBuilder.VERTICAL_ALIGN);
    Point pos2 = layoutBuilder.next(width, height,
        new Margin(40, 0, 20, 0));
    this.c2.setBounds((int) pos2.getX(), (int) pos2.getY(), width, height);
    this.c1.setBackground(Color.WHITE);
    this.c2.setBackground(Color.WHITE);
    this.c1.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.c2.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.c1.setVisible(true);
    this.c2.setVisible(true);
  }

  private void initHobbies() {
    String[] options = { "Cars", "Music", "Memes", "VideoGames" };

    this.c1 = new JComboBox<String>(options);
    this.c2 = new JComboBox<String>(options);
    this.c2.setSelectedIndex(2);
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
    this.c1.setVisible(false);
    this.c2.setVisible(false);
    this.add(c1);
    this.add(c2);
  }

  private void setStyleHobbiesLabel() {
    Font f = new Font("BlinkMacSystemFont", Font.PLAIN, 20);
    this.hobbiesTitle.setFont(f);
    int width = 100;
    int height = 20;
    Point pos = layoutBuilder.next(width, height,
        new Margin(30, 0, LayoutHelper.getCenter(this.getWidth(), this.getHeight(), width, height).x, 0));
    this.hobbiesTitle.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    this.hobbiesTitle.setVisible(true);
  }

  private void initHobbiesLabel() {
    this.hobbiesTitle = new JLabel("Hobbies:");
    this.hobbiesTitle.setVisible(false);
    // this.hobbiesTitle.setBackground(Color.YELLOW);
    // this.hobbiesTitle.setOpaque(true);
    this.add(this.hobbiesTitle);
  }

  private void setStyleSurname() {
    int width = 400;
    int height = 50;
    Point pos = layoutBuilder.next(width, height,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, width, 0).getX(), 0));
    this.surnameField.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    this.surnameField.setForeground(new Color(171, 171, 171));
    this.surnameField.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.surnameField.setBorder(BorderFactory.createCompoundBorder(
        this.surnameField.getBorder(),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    this.surnameField.setVisible(true);
  }

  private void initSurname() {
    this.surnameField = new JTextField("Surname");

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
    this.surnameField.setVisible(false);
    this.add(this.surnameField);
  }

  private void setStyleName() {
    int width = 400;
    int height = 50;
    Point pos = layoutBuilder.next(width, height,
        new Margin(40, 0, (int) LayoutHelper.getCenter(this.getBounds().width, 0, width, 0).getX(), 0));
    this.nameField.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    this.nameField.setForeground(new Color(171, 171, 171));
    this.nameField.setFont(new Font("BlinkMacSystemFont", Font.PLAIN, 18));
    this.nameField.setBorder(BorderFactory.createCompoundBorder(
        this.nameField.getBorder(),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    this.nameField.setVisible(true);
  }

  private void initName() {
    this.nameField = new JTextField("Name");

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
    this.nameField.setVisible(false);
    this.add(this.nameField);
  }

  private void setStyleMessage() {
    Font f = new Font("BlinkMacSystemFont", Font.PLAIN, 20);
    this.message.setFont(f);
    int width = 335;
    int height = 20;
    Point pos = layoutBuilder.next(width, height,
        new Margin(30, 0, LayoutHelper.getCenter(this.getWidth(), this.getHeight(), width, height).x, 0));
    this.message.setBounds((int) pos.getX(), (int) pos.getY(), width, height);
    this.message.setVisible(true);
  }

  private void initMessage() {
    this.message = new JLabel("Please fill the informations below");
    this.message.setVisible(false);
    // this.message.setBackground(Color.YELLOW);
    // this.message.setOpaque(true);
    this.add(this.message);
  }

  private void setStyleWelcome() {
    Font f = new Font("BlinkMacSystemFont", Font.PLAIN, 30);
    this.welcome.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    this.welcome.setBounds(0, 0, 0, 30);
    layoutBuilder.next(0, 30, new Margin(30, 0, 0, 0));
    this.welcome.setVisible(true);
  }

  private void initWelcome() {
    this.welcome = new JLabel(
        "Welcome " + Store.getInstance().getUser().getNickName());
    this.welcome.setVisible(false);
    this.add(this.welcome);
  }

  private void initClassAttributes() {
    this.layoutBuilder = new LayoutBuilder(0, 0, LayoutBuilder.VERTICAL_ALIGN);
    this.name = "";
    this.surname = "";
    this.age = 18;
    this.hobbie1 = "Cars";
    this.hobbie2 = "Memes";
    this.dontUpdateName = false;
    this.dontUpdateSurname = false;
    this.setFocusable(true);
    this.setLayout(null);
    this.setBounds((int) props.position.getX(), (int) props.position.getY(), Utils.SCREEN_WIDTH,
        Utils.SCREEN_HEIGHT - 130);
    this.setBackground(Color.WHITE);
    this.setOpaque(true);
  }

  private void setStyleComponents() {
    this.layoutBuilder.reset(LayoutBuilder.VERTICAL_ALIGN);
    this.setStyleWelcome();
    this.setStyleMessage();
    this.setStyleName();
    this.setStyleSurname();
    this.setStyleHobbiesLabel();
    this.setStyleHobbies();
    this.setStyleAgeTitle();
    this.setStyleAgeSelector();
    this.setStyleNextButton();
  }

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

  public void updateProps(ComponentProps props) {
    try {
      if (props.getPropsTypeName().equals(ContentProps.TYPE_NAME)) {
        this.props = (ContentProps) props;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Content(String name, com.alexis.common.Component.Component parent, ContentProps props) {
    super(name, parent);
    this.props = props;
    this.initClassAttributes();
    this.initWelcome();
    this.initMessage();
    this.initName();
    this.initSurname();
    this.initHobbiesLabel();
    this.initHobbies();
    this.initAgeTitle();
    this.initAgeSelector();
    this.initNextButton();
    this.setStyleComponents();
  }
}
