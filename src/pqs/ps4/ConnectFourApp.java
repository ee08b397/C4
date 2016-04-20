package pqs.ps4;

public class ConnectFourApp {

  public static void main(String[] args) {
    new ConnectFourApp().startGame();
  }

  public void startGame() {
    ConnectFourModel model = new ConnectFourModel();
    new ConnectFourView(model);
    model.startGame();
  }
}