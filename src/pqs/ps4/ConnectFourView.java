package pqs.ps4;

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

public class ConnectFourView implements ConnectFourListener {

  private ConnectFourModel model;
  
  public ConnectFourView(ConnectFourModel model) {
    this.model = model;
    JFrame frame = new JFrame("Connect Four");
    model.addConnectFourListener(this);
    JPanel gamePanel = new JPanel(new BorderLayout());
    
    JPanel dropPanel = new JPanel(new GridLayout(1, ConnectFourConfig.NUM_COL));
    JButton[] dropButtonList = new JButton[ConnectFourConfig.NUM_COL];
    
    for (int col = 0; col < dropButtonList.length; col++) {
      dropButtonList[col] = new JButton("Drop");
      //dropButtonList[col].setEnabled(false);
      dropButtonList[col].addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent event) {
          
        }
      });
      dropPanel.add(dropButtonList[col]);
    }
    
    

    JPanel gridPanel = new JPanel(new GridLayout(
        ConnectFourConfig.NUM_ROW, ConnectFourConfig.NUM_COL));
    JLabel[][] cellList = new JLabel[ConnectFourConfig.NUM_ROW][ConnectFourConfig.NUM_COL];
    
    for (int row = 0; row < cellList.length; row++) {
      for (int col = 0; col < cellList[row].length; col++) {
        cellList[row][col] = new JLabel();
        cellList[row][col].setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        gridPanel.add(cellList[row][col]);
      }
    }
    
    
    JPanel gmaeControlPanel = new JPanel(new GridLayout(1, 4));
    JLabel statusLabel = new JLabel("Hello");
    JButton onePlayerButton = new JButton("One Player");
    JButton twoPlayerButton = new JButton("Two Player");
    JButton quitGameButton = new JButton("Quit Game");
    
    onePlayerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        
      }
    });
    
    twoPlayerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        
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
    frame.setSize(ConnectFourConfig.WINDOW_WIDTH, ConnectFourConfig.WINDOW_HEIGHT);
    frame.setVisible(true);
  }
  
  @Override
  public void gameStarted() {
  }
}
