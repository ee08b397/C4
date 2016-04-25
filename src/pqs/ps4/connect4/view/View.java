package pqs.ps4.connect4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import pqs.ps4.connect4.model.Config;
import pqs.ps4.connect4.model.Model;
import pqs.ps4.connect4.model.Player;

public class View implements Listener {

  private Model model;
  private final Player player;
  
  private JFrame frame;
  private JPanel gamePanel;
  private JPanel dropPanel;
  private JPanel gridPanel;
  private JPanel gmaeControlPanel;
  private JButton[] dropButtonList;
  private JPanel[][] cellList;
  private JLabel statusLabel;
  private JButton onePlayerButton;
  private JButton twoPlayerButton;
  private JButton quitGameButton;
  private boolean[] colFilled; 
  
  /*
   * Builder class for View
   * viewName, playerType, color are called before player
   */
  public static class Builder {
    
    // Required parameters
    private Model model;
    private Player player;
    private String viewName;
    private Config.PLAYERTYPE playerType;
    private Config.COLOR color;
    // Optional parameters (no)
    
    public Builder model(Model model) {
      if (model == null) {
        throw new IllegalStateException("model cannot be null");
      }
      this.model = model;
      return this;
    }
    
    public Builder viewName(String viewName) {
      if (viewName == null) {
        throw new IllegalStateException("viewName cannot be null, can not " +
            "play");
      }
      this.viewName = viewName;
      return this;
    }
    
    public Builder playerType(Config.PLAYERTYPE playerType) {
      if (playerType == null) {
        throw new IllegalStateException("playerType cannot be null");
      }
      this.playerType = playerType;
      return this;
    }
    
    public Builder color(Config.COLOR color) {
      if (color == null) {
        throw new IllegalStateException("color cannot be null, can not play");
      }
      this.color = color;
      return this;
    }
    
    public Builder player() {
      if (this.viewName == null) {
        throw new IllegalStateException("viewName cannot be null, can not " +
            "play");
      }
      if (this.playerType == null) {
        throw new IllegalStateException("playerType cannot be null");
      }
      if (this.color == null) {
        throw new IllegalStateException("color cannot be null, can not play");
      }
      this.player = new Player(this.viewName, this.playerType, 
          this.color);
      return this;
    }
    
    public View build() {
      if (this.model == null) {
        throw new IllegalStateException("Build failed: model cannot be null");
      }
      if (this.viewName == null) {
        throw new IllegalStateException("viewName cannot be null, can not " +
            "play");
      }
      if (this.playerType == null) {
        throw new IllegalStateException("playerType cannot be null");
      }
      if (this.color == null) {
        throw new IllegalStateException("color cannot be null, can not play");
      }
      if (this.player == null) {
        throw new IllegalStateException("player cannot be null, can not play");
      }
      
      return new View(this);
    }
  }
  
  private View(Builder builder) {
    this.model = builder.model;
    this.model.addListener(this);

    this.player = builder.player;
    this.model.addPlayer(player);
    
    this.frame = new JFrame("Connect Four - " + builder.viewName);
    this.gamePanel = new JPanel(new BorderLayout());
    this.dropPanel = new JPanel(new GridLayout(1, Config.NUM_COL));
    if (builder.playerType == Config.PLAYERTYPE.PRIMARY) {
      this.dropPanel.setVisible(false);
    }
    
    this.dropButtonList = new JButton[Config.NUM_COL];
    this.colFilled = new boolean[Config.NUM_COL]; 
    for (int col = 0; col < dropButtonList.length; col++) {
      this.dropButtonList[col] = new JButton("Drop " + col);
      this.dropButtonList[col].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
          String[] dropCmd = event.getActionCommand().split(" ");
          dropButtonClicked(Integer.parseInt(dropCmd[1]));
        }
      });
      this.dropPanel.add(dropButtonList[col]);
      this.colFilled[col] = false;
    }
    
    this.gridPanel = new JPanel(new GridLayout(
        Config.NUM_ROW, Config.NUM_COL));
    this.cellList = new JPanel[Config.NUM_ROW][Config.NUM_COL];
    
    for (int row = 0; row < this.cellList.length; row++) {
      for (int col = 0; col < this.cellList[row].length; col++) {
        this.cellList[row][col] = new JPanel();
        this.cellList[row][col].setBorder(
            BorderFactory.createLineBorder(Color.black, 1, true));
        this.gridPanel.add(this.cellList[row][col]);
      }
    }
    
    this.gmaeControlPanel = new JPanel(new GridLayout(1, 4));
    this.statusLabel = new JLabel("Hello");
    this.onePlayerButton = new JButton("One Player");
    this.twoPlayerButton = new JButton("Two Player");
    this.quitGameButton = new JButton("Quit Game");
    
    this.onePlayerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        initializePanel();
        modeButtonClicked(Config.PLAYMODE.SINGLE);
        statusLabel.setText("1-player game");
      }
    });
    
    this.twoPlayerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        initializePanel();
        modeButtonClicked(Config.PLAYMODE.MULTI);
        statusLabel.setText("2-player game");
      }
    });
    
    if (builder.playerType == Config.PLAYERTYPE.PLAY) {
      this.onePlayerButton.setVisible(false);
      this.twoPlayerButton.setVisible(false);
    }
    
    this.quitGameButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        System.exit(0);
      }
    });
    
    this.gmaeControlPanel.add(statusLabel);
    this.gmaeControlPanel.add(onePlayerButton);
    this.gmaeControlPanel.add(twoPlayerButton);
    this.gmaeControlPanel.add(quitGameButton);
    
    this.gamePanel.add(dropPanel, BorderLayout.NORTH);
    this.gamePanel.add(gridPanel, BorderLayout.CENTER);
    this.gamePanel.add(gmaeControlPanel, BorderLayout.SOUTH);
    this.frame.getContentPane().add(gamePanel);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
    this.frame.setVisible(true);
  }
  
  private void modeButtonClicked(Config.PLAYMODE mode) {
    this.model.modeUpdate(mode, this.player);
  }
  
  private void dropButtonClicked(int col) {
    lockDropPanel();
    this.model.dropDisc(this.player, col);
  }
  
  private void initializePanel() {
    for (int index = 0; index < this.colFilled.length; index++) {
      this.colFilled[index] = false;
    }
    unlockDropPanel();
    for (JPanel[] rowList : this.cellList) {
      for (JPanel cell : rowList) {
        cell.setBackground(UIManager.getColor("Panel.background"));
      }
    }
    this.statusLabel.setText("Hello");
  }
  
  private void unlockDropPanel() {
    this.dropPanel.setVisible(true);
    for (int col = 0; col < this.dropButtonList.length; col++) {
      if (this.colFilled[col] == false) {
        this.dropButtonList[col].setEnabled(true);
      }
    }
  }
  
  private void lockDropPanel() {
    for (JButton dropButton : this.dropButtonList) {
      dropButton.setEnabled(false);
    }
  }
  
  @Override
  public void gameStarted() {
    this.dropPanel.setVisible(false);
  }

  @Override
  public void draw() {
    this.dropPanel.setVisible(false);
  }

  @Override
  public void win(Player player) {
    this.statusLabel.setText(player.getPlayerName() + " won!");
    this.dropPanel.setVisible(false);
  }

  @Override
  public void modeUpdated(Config.PLAYMODE modeUpdate) {
    initializePanel();
    if (modeUpdate == Config.PLAYMODE.SINGLE && player.getPlayerType() == 
        Config.PLAYERTYPE.PLAY) {
      
      this.model.removePlayer(player);
      this.model.removeListener(this);
      this.frame.dispose();
    }
  }

  @Override
  public void discDropped(Player player, int row, int col) {
    this.cellList[row][col].setBackground(player.getColor());
    this.statusLabel.setText(String.format("%s at [%d,%d]", player.
        getPlayerName(), row, col));
    if (row == 0) {
      this.colFilled[col] = true;
    }
    if (player != this.player) {
      unlockDropPanel();
    }
  }
}
