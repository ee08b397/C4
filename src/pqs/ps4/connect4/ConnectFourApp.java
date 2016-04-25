package pqs.ps4.connect4;

import pqs.ps4.connect4.model.Config;
import pqs.ps4.connect4.model.Model;
import pqs.ps4.connect4.view.Log;
import pqs.ps4.connect4.view.LogFactory;
import pqs.ps4.connect4.view.View;

/**
 * The Connect Four game application. Model is in Observer pattern and it  
 * powers the game and viewers communicate with model by listener interfaces. 
 * <p>
 * Two players (2 people or 1 people vs computer) first choose a color and then
 * take turns dropping colored discs from the top into a Config.NUM_COL by 
 * Config.NUM_ROW vertically suspended grid. The pieces fall straight down, 
 * occupying the next available space within the column. The objective of the 
 * game is to connect four of one's own discs of the same color next to each 
 * other vertically, horizontally, or diagonally before your opponent. 
 * <p>
 * Make a new application by get instance from singleton. E.g., <code>
 * ConnectFourApp connectFourApp = ConnectFourApp.getInstance();</code>
 * and startGame(). 
 * <p>
 * Use default Equals(), toString(), hashCode() is good enough. v1 Game is 
 * unit testted under testsrc/ and 93.0% code coverage. 
 * 
 * @author    Songxiao Zhang
 * @version   1.0
 */
public class ConnectFourApp {

  /**
   * Singleton Connect Four game application ConnectFourApp
   */
  private final static ConnectFourApp instance = new ConnectFourApp();
  
  private ConnectFourApp() {
    
  }
  
  /**
   * Get singleton Connect Four game application
   * 
   * @return    Return singleton Connect Four game application instance. 
   */
  public static ConnectFourApp getInstance() {
    return instance;
  }

  /**
   * Start Connect Four game application! Make a new Model E.g., <code>Model 
   * model = new Model();</code> and add viewer and logger. startGame() at 
   * last.
   */
  public void startGame() {
    Model model = new Model();
    new View.Builder().model(model).viewName("A").playerType(Config.PLAYERTYPE
        .PRIMARY).color(Config.COLOR.RED).player().build();
    LogFactory logFactory = new LogFactory();
    logFactory.getLog(model, null);
    model.startGame();
  }
  
  public static void main(String[] args) {
    ConnectFourApp connectFourApp = ConnectFourApp.getInstance();
    connectFourApp.startGame();
  }
}