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
  
  public View(Model model, Config.PLAYERTYPE playerType, Config.COLOR color) {
    this.model = model;
    model.addListener(this);
    player = new Player("A", "A", playerType, color);
    
    frame = new JFrame("Connect Four");
    gamePanel = new JPanel(new BorderLayout());
    dropPanel = new JPanel(new GridLayout(1, Config.NUM_COL));
    dropPanel.setVisible(false);
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
        statusLabel.setText("1-player game");
      }
    });
    
    twoPlayerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        initializePanel();
        statusLabel.setText("2-player game");
      }
    });
    
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
  
  private void dropButtonClicked(int col) {
    model.dropDisc(this.player, col);
  }
  
  private void initializePanel() {
    unlockDropPanel();
    statusLabel.setText("Hello");
  }
  
  private void unlockDropPanel() {
    dropPanel.setVisible(true);
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
  public void modeUpdate(Config.PLAYMODE modeUpdate) {
    
  }

  @Override
  public void discDropped(Player player, int row, int col) {
    this.cellList[row][col].setBackground(Color.RED);
    if (row == 0) {
      dropButtonList[col].setEnabled(false);
    }
  }
}
