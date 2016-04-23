package pqs.ps4.connect4.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pqs.ps4.connect4.model.Config.PLAYMODE;
import pqs.ps4.connect4.view.Listener;
import pqs.ps4.connect4.view.View;

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

  public void addPlayer(Player player) {
    players.add(player);
  }
  
  public void removePlayer(Player player) {
    players.remove(player);
  }

  public void modeUpdate(Config.PLAYMODE mode, Player player) {
    this.grid.initialize();
    if (mode == Config.PLAYMODE.MULTI) {
      if (players.size() < 2) {
        new View(this, "B", Config.PLAYERTYPE.PLAY, Config.COLOR.YELLOW);
      }
    }
    for (Listener lisenter: listeners) {
      lisenter.modeUpdated(mode);
    }
  }
}
