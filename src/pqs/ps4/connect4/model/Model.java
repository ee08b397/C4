package pqs.ps4.connect4.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pqs.ps4.connect4.view.Listener;
import pqs.ps4.connect4.view.View;

public class Model {
  
  private List<Listener> listeners = new ArrayList<Listener>();
  private List<Player> players = new ArrayList<Player>();
  private Grid grid = Grid.getInstance();
  private Player computer = new Player("C", "C", Config.PLAYERTYPE.COMPUTER, 
      Config.COLOR.BLUE);
  private Config.PLAYMODE currentMode = Config.PLAYMODE.SINGLE;
  
  public void addListener(Listener listener) {
    listeners.add(listener);
  }
  
  public void removeListener(Listener listener) {
    listeners.remove(listener);
  }
  
  private String generateNewPlayerId() {
    UUID playerId = UUID.randomUUID();
    return String.valueOf(playerId);
  }
  
  public void startGame() {
    
  }
  
  /*
   * round-robin lock: all player's drop panel is hidden, the lock is exclusive 
   * to one player at a time. It means only the one player can drop disc has 
   * the drop panel shown;
   */
  public void nextPlayerDrop() {
    
  }
  
  public void dropDisc(Player player, int col) {
    int droppedRow = this.grid.dropDisc(player, col);
    boolean go = true;
    if (droppedRow > -1) {
      for (Listener lisenter: listeners) {
        lisenter.discDropped(player, droppedRow, col);
      }
      
      Coord dropCoord = new Coord(droppedRow, col); 
      go = checkResult(player, dropCoord);
    }
    
    if (this.currentMode == Config.PLAYMODE.SINGLE && go) {
      try {
        Thread.sleep(50);
      } catch (InterruptedException ignored) {
      }
      
      Coord droppedComputer = this.grid.dropDiscComputer(this.computer);
      for (Listener lisenter: listeners) {
        lisenter.discDropped(this.computer, droppedComputer.getRow(), 
            droppedComputer.getCol());
      }
      
      Coord dropCoord = new Coord(droppedComputer.getRow(), 
          droppedComputer.getCol()); 
      checkResult(this.computer, dropCoord);
    }
  }
  
  private boolean checkResult(Player player, Coord dropCoord) {
    Config.PLAYRESULT result = this.grid.checkAllConditions(player, dropCoord);
    switch(result) {
      case WIN:
        for (Listener lisenter: listeners) {
          lisenter.win(player);
        }
        return false;
      case DRAW:
        for (Listener lisenter: listeners) {
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

  public void addPlayer(Player player) {
    this.players.add(player);
  }
  
  public void removePlayer(Player player) {
    this.players.remove(player);
  }
  
  private void addComputerPlayer() {
    this.players.add(this.computer);
  }
  
  private void removeComputerPlayer() {
    this.players.remove(this.computer);
  }

  public void modeUpdate(Config.PLAYMODE mode, Player player) {
    this.grid.initialize();
    
    // switch from single to multiple mode
    if (this.currentMode != mode) {
      switch(mode) {
        case MULTI:
            currentMode = Config.PLAYMODE.MULTI;
            removeComputerPlayer();
            new View(this, "B", Config.PLAYERTYPE.PLAY, Config.COLOR.YELLOW);
          break;
        case SINGLE:
          currentMode = Config.PLAYMODE.SINGLE;
            // PLAY Views will remove themselves in modeUpdated()
            addComputerPlayer();
          break;
        default:
          throw new IllegalArgumentException("Unknown Config.PLAYMODE");
      }
    }
    
    for (Listener lisenter: listeners) {
      lisenter.modeUpdated(mode);
    }
  }
}
