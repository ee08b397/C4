package pqs.ps4.connect4.model;

import java.util.Random;

public class Grid {
  
  private final static Grid instance = new Grid();
  private String[][] grid;
  
  private Grid() {
    grid = new String[Config.NUM_ROW][Config.NUM_COL];
    initialize();
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

  public void initialize() {
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {
        grid[row][col] = "";
      }
    }
  }

  public Coord dropDiscComputer(Player computer) {
    Coord winDrop = checkWinDrop();
    if (winDrop == null) {
      Random random = new Random();
      int randCol = random.nextInt(Config.NUM_COL);
      int row = -1;
      while (row == -1) {
        randCol = random.nextInt(Config.NUM_COL);
        row = dropDisc(computer, randCol);
      }
      return new Coord(row, randCol);
    }
    return null;
  }

  private Coord checkWinDrop() {
    return null;
  }
  
  public class Coord {
    
    private final int row;
    private final int col;
    
    public Coord(int row, int col) {
      if (row >= 0 && row < Config.NUM_ROW && col >= 0 
          && col < Config.NUM_COL) {
        
        this.row = row;
        this.col = col;  
      }
      else {
        throw new IllegalArgumentException(String.format(
            "Illegal coordinate row: %d, col: %d. ", row, col));
      }
    }

    public int getRow() {
      return new Integer(this.row);
    }

    public int getCol() {
      return new Integer(this.col);
    }
  }
}
