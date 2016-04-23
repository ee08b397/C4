package pqs.ps4.connect4.model;

public class Grid {
  
  private final static Grid instance = new Grid();
  private String[][] grid;
  
  private Grid() {
    grid = new String[Config.NUM_ROW][Config.NUM_COL];
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {
        grid[row][col] = "";
      }
    }
  }

  public static Grid getInstance() {
    return instance;
  }
  
  public int dropDisc(Player player, int col) {
    for (int row = Config.NUM_ROW - 1; row >= 0; row--) {
      if (grid[row][col].equals("")) {
        grid[row][col] = player.getPlayerName();
        return row;
      }
    }
    return -1;
  }
}
