package pqs.ps4;

import java.util.ArrayList;
import java.util.List;

public class ConnectFourModel {
  
  private List<ConnectFourListener> listeners = new ArrayList<ConnectFourListener>();
  
  public void addConnectFourListener(ConnectFourListener connectFourListener) {
    listeners.add(connectFourListener);
  }
  
  public void removeConnectFourListener(ConnectFourListener connectFourListener) {
    listeners.remove(connectFourListener);
  }
  
  public void startGame() {
    
  }
}
