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
  
  public View(Model model, String name, Config.PLAYERTYPE playerType, 
      Config.COLOR color) {
    
    this.model = model;
    model.addListener(this);
    player = new Player(name, name, playerType, color);
    model.addPlayer(player);
    
    frame = new JFrame("Connect Four");
    gamePanel = new JPanel(new BorderLayout());
    dropPanel = new JPanel(new GridLayout(1, Config.NUM_COL));
    if (playerType == Config.PLAYERTYPE.PRIMARY) {
      dropPanel.setVisible(false);
    }
    dropButtonList = new JButton[Config.NUM_COL];
    
    for (int col = 0; col < dropButtonList.length; col++) {
      dropButtonList[col] = new JButton("Drop " + col);
      dropButtonList[col].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
          String[] dropCmd = event.getActionCommand().split(" ");
          dropButtonClicked(Integer.parseInt(dropCmd[1]));
        }
      });
      dropPanel.add(dropButtonList[col]);
    }
    
    gridPanel = new JPanel(new GridLayout(
        Config.NUM_ROW, Config.NUM_COL));
    cellList = new JPanel[Config.NUM_ROW][Config.NUM_COL];
    
    for (int row = 0; row < cellList.length; row++) {
      for (int col = 0; col < cellList[row].length; col++) {
        cellList[row][col] = new JPanel();
        cellList[row][col].setBorder(
            BorderFactory.createLineBorder(Color.black, 1, true));
        gridPanel.add(cellList[row][col]);
      }
    }
    
    gmaeControlPanel = new JPanel(new GridLayout(1, 4));
    statusLabel = new JLabel("Hello");
    onePlayerButton = new JButton("One Player");
    twoPlayerButton = new JButton("Two Player");
    quitGameButton = new JButton("Quit Game");
    
    onePlayerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        initializePanel();
        modeButtonClicked(Config.PLAYMODE.SINGLE);
        statusLabel.setText("1-player game");
      }
    });
    
    twoPlayerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        initializePanel();
        modeButtonClicked(Config.PLAYMODE.MULTI);
        statusLabel.setText("2-player game");
      }
    });
    
    if (playerType == Config.PLAYERTYPE.PLAY) {
      onePlayerButton.setVisible(false);
      twoPlayerButton.setVisible(false);
    }
    
    quitGameButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        System.exit(0);
      }
    });
    
    gmaeControlPanel.add(statusLabel);
    gmaeControlPanel.add(onePlayerButton);
    gmaeControlPanel.add(twoPlayerButton);
    gmaeControlPanel.add(quitGameButton);
    
    gamePanel.add(dropPanel, BorderLayout.NORTH);
    gamePanel.add(gridPanel, BorderLayout.CENTER);
    gamePanel.add(gmaeControlPanel, BorderLayout.SOUTH);
    frame.getContentPane().add(gamePanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
    frame.setVisible(true);
  }
  
  private void modeButtonClicked(Config.PLAYMODE mode) {
    model.modeUpdate(mode, player);
  }
  
  private void dropButtonClicked(int col) {
    model.dropDisc(this.player, col);
    lockDropPanel();
  }
  
  private void initializePanel() {
    unlockDropPanel();
    for (JPanel[] rowList : cellList) {
      for (JPanel cell : rowList) {
        cell.setBackground(UIManager.getColor("Panel.background"));
      }
    }
    statusLabel.setText("Hello");
  }
  
  private void unlockDropPanel() {
    dropPanel.setVisible(true);
    for (JButton dropButton : dropButtonList) {
      dropButton.setEnabled(true);
    }
  }
  
  private void lockDropPanel() {
    for (JButton dropButton : dropButtonList) {
      dropButton.setEnabled(false);
    }
  }
  
  @Override
  public void gameStarted() {
    dropPanel.setVisible(false);
  }

  @Override
  public void draw() {
    dropPanel.setVisible(false);
  }

  @Override
  public void win(Player player) {
    
  }

  @Override
  public void modeUpdated(Config.PLAYMODE modeUpdate) {
    initializePanel();
    if (modeUpdate == Config.PLAYMODE.SINGLE && player.getPlayerType() == 
        Config.PLAYERTYPE.PLAY) {
      
      model.removeListener(this);
      model.removePlayer(player);
      frame.dispose();
    }
  }

  @Override
  public void discDropped(Player player, int row, int col) {
    this.cellList[row][col].setBackground(player.getColor());
    statusLabel.setText(String.format("%s at [%d,%d]", player.getPlayerName(), 
        row, col));
    if (row == 0) {
      dropButtonList[col].setEnabled(false);
    }
    if (player != this.player) {
      unlockDropPanel();
    }
  }
}
