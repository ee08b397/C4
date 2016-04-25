package pqs.ps4.connect4.view;

import pqs.ps4.connect4.model.Config;
import pqs.ps4.connect4.model.Player;

/**
 * Observer pattern Listener interfaces between model and views. 
 *  
 * @author Songxiao Zhang
 */
public interface Listener {
  
  /**
   * Game started. 
   */
  void gameStarted();
  /**
   * @param player        player just dropped a disc
   * @param row           new disc row
   * @param col           new disc column
   */
  void discDropped(Player player, int row, int col);
  /**
   * Game mode is updated. 
   * 
   * @param modeUpdate    new game mode
   */
  void modeUpdated(Config.PLAYMODE modeUpdate);
  /**
   * One player wins. 
   * 
   * @param player        winer
   */
  void win(Player player);
  /**
   * Game draw. 
   */
  void draw();
  
}
