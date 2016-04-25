package pqs.ps4.connect4.view;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import pqs.ps4.connect4.model.Config.PLAYMODE;
import pqs.ps4.connect4.model.Model;
import pqs.ps4.connect4.model.Player;

/**
 * Log game to console or file. 
 * <p>
 * Use default Equals(), toString(), hashCode() is good enough.  
 * 
 * @author Songxiao Zhang
 *
 */
public class Log implements Listener {
  
  /**
   * Current model. 
   */
  private Model model;
  
  /**
   * Singleton logger.
   */
  private static final Logger log = Logger.getLogger("Connect Four Log");
  
  /**
   * Logger constructor. 
   * 
   * @param model     Current model. 
   * @param handler   Null if print to console. Otherwise save to file. 
   */
  public Log(Model model, FileHandler handler) {
    
    this.model = model;
    model.addListener(this);
    
    if (handler != null) {
      log.addHandler(handler);
      SimpleFormatter formatter = new SimpleFormatter();
      handler.setFormatter(formatter);
    }
  }

  @Override
  public void gameStarted() {
    log.info("Game started.");
  }

  @Override
  public void discDropped(Player player, int row, int col) {
    log.info(String.format("Player %s dropped disc in row %d and column %d", 
        player.getPlayerName(), row, col));
  }

  @Override
  public void modeUpdated(PLAYMODE modeUpdate) {
    log.info(String.format("New game. Play mode is now %s. ", 
        modeUpdate.toString()));
  }

  @Override
  public void win(Player player) {
    log.info(String.format("Player %s win! ", player.getPlayerName()));
  }

  @Override
  public void draw() {
    log.info("Game draw! ");
  }
}
