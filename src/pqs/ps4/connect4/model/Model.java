package pqs.ps4.connect4.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pqs.ps4.connect4.view.Listener;

public class Model {
  
  private List<Listener> listeners = new ArrayList<Listener>();
  private List<Player> players = new ArrayList<Player>();
  private Grid grid = Grid.getInstance();
  
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
    if (droppedRow > -1) {
      for (Listener lisenter: listeners) {
        lisenter.discDropped(player, droppedRow, col);
      }
    }
  }
}
