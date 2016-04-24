package pqs.ps4.connect4;

import pqs.ps4.connect4.model.Config;
import pqs.ps4.connect4.model.Model;
import pqs.ps4.connect4.view.Log;
import pqs.ps4.connect4.view.LogFactory;
import pqs.ps4.connect4.view.View;

public class ConnectFourApp {

  private final static ConnectFourApp instance = new ConnectFourApp();
  
  private ConnectFourApp() {
    
  }
  
  public static ConnectFourApp getInstance() {
    return instance;
  }

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