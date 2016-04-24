package pqs.ps4.connect4.view;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import pqs.ps4.connect4.model.Config;
import pqs.ps4.connect4.model.Config.PLAYMODE;
import pqs.ps4.connect4.model.Model;
import pqs.ps4.connect4.model.Player;

public class LoggerC4 implements Listener {
  
  private Model model;
  private static final Logger log = Logger.getLogger("Connect Four Log");
  
  public LoggerC4(Model model, String name, Config.PLAYERTYPE playerType, 
      Config.COLOR color) {
    
    this.model = model;
    model.addListener(this);
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
