package pqs.ps4.connect4.view;

import pqs.ps4.connect4.model.Config;
import pqs.ps4.connect4.model.Player;

public interface Listener {
  
  void gameStarted();
  void discDropped(Player player, int row, int col);
  void modeUpdate(Config.PLAYMODE modeUpdate);
  void win(Player player);
  void draw();
  
}
