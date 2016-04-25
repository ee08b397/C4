package pqs.ps4.connect4.model;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.UUID;

import pqs.ps4.connect4.view.Listener;
import pqs.ps4.connect4.view.View;

/**
 * The Connect Four game "engine". Model is in Observer pattern and it powers 
 * the game and communicate with viewers by listener interfaces. 
 * <p>
 * Two players (2 people or 1 people vs computer) first choose a color and then
 * take turns dropping colored discs from the top into a Config.NUM_COL by 
 * Config.NUM_ROW vertically suspended grid. The pieces fall straight down, 
 * occupying the next available space within the column. The objective of the 
 * game is to connect four of one's own discs of the same color next to each 
 * other vertically, horizontally, or diagonally before your opponent. 
 * <p>
 * Make a new Model by constructor. E.g., <code>Model model = new Model();
 * </code> and let viewer pass the model. 
 * <p>
 * Use default Equals(), toString(), hashCode() as default is good.
 * 
 * @author Songxiao Zhang
 *
 */
public class Model {
  
  /** listeners list keeps track of all listeners hearing model's Listener
   * interface. 
   */
  private List<Listener> listeners = new ArrayList<Listener>();
  
  /**
   * players list keeps track of all players in the game.  
   */
  private List<Player> players = new ArrayList<Player>();
  
  /**
   * The Connect Four game grid under the hood in singleton. 
   */
  private Grid grid = Grid.getInstance();

  /**
   * The computer player in 1 player game mode.  
   */
  private Player computer = new Player("C", Config.PLAYERTYPE.COMPUTER, 
      Config.COLOR.BLUE);
  
  /**
   * Game mode, single by default 
   */
  private Config.PLAYMODE currentMode = Config.PLAYMODE.SINGLE;
  
  /**
   * Add listener to listening list via Listener interface
   * 
   * @param listener    listener implementing Listener interface in Observer 
   *                    pattern
   */
  public void addListener(Listener listener) {
    this.listeners.add(listener);
  }
  
  /**
   * Remove listener from listening list via Listener interface
   * 
   * @param listener    listener implementing Listener interface in Observer 
   *                    pattern
   */
  public void removeListener(Listener listener) {
    this.listeners.remove(listener);
  }
  
  /**
   * Start connect four game and tell all listeners. 
   */
  public void startGame() {
    for (Listener lisenter: listeners) {
      lisenter.gameStarted();
    }
  }
  
  /**
   * Drop discs by player and intented column to the bottom. It implements a 
   * round-robin lock: all player's drop panel is hidden, the lock is exclusive 
   * to one player at a time. It means only the one player can drop disc has 
   * the drop panel shown;
   *
   * @param player    player who's about to drop a disc
   * @param col       the column that disc is about to drop to the bottom
   */
  public void dropDisc(Player player, int col) {
    int droppedRow = this.grid.dropDisc(player, col);
    boolean go = true;
    if (droppedRow > -1) {
      for (Listener lisenter: this.listeners) {
        lisenter.discDropped(player, droppedRow, col);
      }
      
      Coord dropCoord = new Coord(droppedRow, col); 
      go = checkResult(player, dropCoord);
    }
    
    if (this.currentMode == Config.PLAYMODE.SINGLE && go) {
      try {
        // to simulate computer "think" about next step
        Thread.sleep(100);
      } catch (InterruptedException ignored) {
      }
      
      Coord droppedComputer = this.grid.dropDiscComputer(this.computer);
      for (Listener lisenter: this.listeners) {
        lisenter.discDropped(this.computer, droppedComputer.getRow(), 
            droppedComputer.getCol());
      }
      
      Coord dropCoord = new Coord(droppedComputer.getRow(), 
          droppedComputer.getCol()); 
      checkResult(this.computer, dropCoord);
    }
  }
  
  /**
   * Check is current status a win/draw/keep going.  
   * 
   * @param player                      player just dropped a disc
   * @param dropCoord                   disc coordinate 
   * @return                            return true for keep going; false if
   *                                    win/draw
   * @throws IllegalArgumentException   If unknown Config.PLAYRESULT
   */
  private boolean checkResult(Player player, Coord dropCoord) {
    Config.PLAYRESULT result = this.grid.checkAllConditions(player, dropCoord);
    switch(result) {
      case WIN:
        for (Listener lisenter: this.listeners) {
          lisenter.win(player);
        }
        return false;
      case DRAW:
        for (Listener lisenter: this.listeners) {
          lisenter.draw();
        }
        return false;
      case GO:
        break;
      default:
        throw new IllegalArgumentException("Unknown Config.PLAYRESULT " + 
            result);
    }
    return true;
  }

  /**
   * Add new player to game. 
   * 
   * @param player    the new player
   */
  public void addPlayer(Player player) {
    this.players.add(player);
  }
  
  /**
   * Remove a player from game. 
   * 
   * @param player    the player to remove
   */
  public void removePlayer(Player player) {
    this.players.remove(player);
  }
  
  /**
   * Add a computer player (no view)
   */
  private void addComputerPlayer() {
    this.players.add(this.computer);
  }
  
  /**
   * Remove the computer player (no view)
   */
  private void removeComputerPlayer() {
    this.players.remove(this.computer);
  }

  /**
   * Update game mode, 1 player or 2 player game. 
   * 
   * @param mode                        new game mode
   * @param player                      the player changed mode
   * @throws IllegalArgumentException   If unknown Config.PLAYMODE
   */
  public void modeUpdate(Config.PLAYMODE mode, Player player) {
    this.grid.initialize();
    
    // switch from single to multiple mode
    if (this.currentMode != mode) {
      switch(mode) {
        case MULTI:
          this.currentMode = Config.PLAYMODE.MULTI;
            removeComputerPlayer();
            new View.Builder().model(this).viewName("B").playerType(Config
                .PLAYERTYPE.PLAY).color(Config.COLOR.YELLOW).player().build();
          break;
        case SINGLE:
          this.currentMode = Config.PLAYMODE.SINGLE;
            // PLAY Views will remove themselves in modeUpdated()
            addComputerPlayer();
          break;
        default:
          throw new IllegalArgumentException("Unknown Config.PLAYMODE");
      }
    }
    
    // "AWT-EventQueue-0" java.util.ConcurrentModificationException would be 
    // thrown by removing a listener while iterating through, no harm 
    try {
      for (Listener lisenter: this.listeners) {
        lisenter.modeUpdated(mode);
      }
    }
    catch(ConcurrentModificationException ignored) {
      
    }
  }
  
}
