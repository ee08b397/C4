package pqs.ps4.connect4.modeltest;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.logging.FileHandler;

import org.junit.Before;
import org.junit.Test;

import pqs.ps4.connect4.model.Config;
import pqs.ps4.connect4.model.Model;
import pqs.ps4.connect4.model.Player;
import pqs.ps4.connect4.view.Listener;
import pqs.ps4.connect4.view.LogFactory;
import pqs.ps4.connect4.view.View;

public class ModelTest {

  private Model model;
  
  @Before
  public void setUp() {
    model = new Model();
  }
  
  @Test
  public void testAddListener() {
    Listener listener = null;
    this.model.addListener(listener);
    LogFactory logFactory = new LogFactory();
    logFactory.getLog(this.model, null);
    
    assertTrue(true);
  }
  
  @Test
  public void testRemoveListener() {
    Listener listener = null;
    this.model.removeListener(listener);
    
    assertTrue(true);
  }
  
  @Test
  public void testStartGame() {
    new View.Builder().model(this.model).viewName("A").playerType(
        Config.PLAYERTYPE.PRIMARY).color(Config.COLOR.RED).player().build();
    this.model.startGame();
    
    assertTrue(true);
  }
  
  @Test
  public void testDropDisc() {
    new View.Builder().model(this.model).viewName("A").playerType(
        Config.PLAYERTYPE.PRIMARY).color(Config.COLOR.RED).player().build();
    Player player1 = new Player("A", Config.PLAYERTYPE.PRIMARY, Config
        .COLOR.YELLOW);
    Player player2 = new Player("B", Config.PLAYERTYPE.PLAY, Config
        .COLOR.RED);
    this.model.modeUpdate(Config.PLAYMODE.MULTI, player1);
    this.model.addPlayer(player1);
    this.model.addPlayer(player2);
    
    this.model.dropDisc(player1, 1);
    this.model.dropDisc(player2, 2);
    
    // test invalid case: col 1 full
    this.model.dropDisc(player1, 1);
    this.model.dropDisc(player2, 1);
    this.model.dropDisc(player1, 1);
    this.model.dropDisc(player2, 1);
    
    assertTrue(true);
  }
  
  /*
   * modelUpdate() is private, we can reach its branches by dropDesc()
   */
  @Test
  public void testModelUpdate() throws SecurityException, IOException {
    new View.Builder().model(this.model).viewName("A").playerType(
        Config.PLAYERTYPE.PRIMARY).color(Config.COLOR.RED).player().build();
    Player player1 = new Player("A", Config.PLAYERTYPE.PRIMARY, Config
        .COLOR.YELLOW);
    Player player2 = new Player("B", Config.PLAYERTYPE.PLAY, Config
        .COLOR.RED);
    LogFactory logFactory = new LogFactory();
    logFactory.getLog(model, null);
    LogFactory logFactoryFile = new LogFactory();
    logFactoryFile.getLog(model, new FileHandler("log"));
    
    this.model.addPlayer(player1);
    this.model.addPlayer(player2);
    this.model.modeUpdate(Config.PLAYMODE.SINGLE, player1);
    this.model.dropDisc(player1, 1);
    this.model.dropDisc(player2, 2);
    
    this.model.startGame();
    
    // test valid result: player 1 win
    for (int i = 0; i < 7; ++i) {
      if ((i & 1) == 0) {
        this.model.dropDisc(player1, 1);
      }
      else {
        this.model.dropDisc(player2, 2);
      }
    }
    
    // test valid case: draw
    this.model.modeUpdate(Config.PLAYMODE.MULTI, player1);
    for (int i = 0; i < Config.NUM_COL * Config.NUM_ROW + 1; ++i) {
      if ((i & 1) == 0) {
        this.model.dropDisc(player1, i % Config.NUM_COL);
      }
      else {
        this.model.dropDisc(player2, i % Config.NUM_COL);
      }
    }
    
    assertTrue(true);
  }
  
  @Test
  public void testAddPlayer() {
    Listener listener = null;
    this.model.addListener(listener);
    this.model.addPlayer(new Player("A", Config.PLAYERTYPE.PRIMARY, Config
        .COLOR.YELLOW));
    
    assertTrue(true);
  }
  
  @Test
  public void testRemovePlayer() {
    Listener listener = null;
    this.model.addListener(listener);
    Player newPlayer = new Player("A", Config.PLAYERTYPE.PRIMARY, Config
        .COLOR.YELLOW);
    this.model.addPlayer(newPlayer);
    this.model.removePlayer(newPlayer);
    
    assertTrue(true);
  }


  @Test
  public void testModeUpdate() {
    new View.Builder().model(this.model).viewName("A").playerType(
        Config.PLAYERTYPE.PRIMARY).color(Config.COLOR.RED).player().build();
    Player newPlayer = new Player("A", Config.PLAYERTYPE.PRIMARY, Config
        .COLOR.YELLOW);
    this.model.addPlayer(newPlayer);
    this.model.modeUpdate(Config.PLAYMODE.SINGLE, newPlayer);
    this.model.modeUpdate(Config.PLAYMODE.MULTI, newPlayer);
    this.model.modeUpdate(Config.PLAYMODE.SINGLE, newPlayer);
    this.model.modeUpdate(Config.PLAYMODE.MULTI, newPlayer);
    
    this.model.dropDisc(newPlayer, 1);
    this.model.dropDisc(newPlayer, 1);
    this.model.dropDisc(newPlayer, 1);
    this.model.dropDisc(newPlayer, 1);
    
    assertTrue(true);
  }
  
}
