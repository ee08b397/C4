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
    Coord winDrop = checkWinDrop(computer);
    if (winDrop == null) {
      Random random = new Random();
      int row = -1;
      int randCol = random.nextInt(Config.NUM_COL);
      while (row == -1) {
        randCol = random.nextInt(Config.NUM_COL);
        row = dropDisc(computer, randCol);
      }
      return new Coord(row, randCol);
    }
    return winDrop;
  }

  private Coord checkWinDrop(Player player) {
    for (int row = 0; row < Config.NUM_ROW; row++) {
      for (int col = 0; col < Config.NUM_COL; col++) {
        if (grid[row][col].equals("") && checkAllConditions(player, new 
            Coord(row, col)) == Config.PLAYRESULT.WIN) {
          
          return new Coord(row, col);
        }
      }
    } 
    return null;
  }
  
  public Config.PLAYRESULT checkAllConditions(Player player, Coord drop) {
    if (checkVertical(player, drop) || checkHorizontal(player, drop) ||
        checkDiagonal(player, drop)) {
      
      return Config.PLAYRESULT.WIN;
    }
    if (checkDraw()) {
      return Config.PLAYRESULT.DRAW;
    }
    return Config.PLAYRESULT.GO;
  }
  
  private boolean checkDraw() {
    int emptySlotNum = 0;
    for (int row = 0; row < Config.NUM_ROW; row++) {
      for (int col = 0; col < Config.NUM_COL; col++) {
        if (grid[row][col].equals("")) {
          emptySlotNum++;
        }
      }
    }
    if (emptySlotNum == 0) {
      return true;
    }
    else {
      return false;
    }
  }

  private boolean checkDiagonal(Player player, Coord drop) {
    int straight = 0;
    int dropRow = drop.getRow();
    int dropCol = drop.getCol();
    int row = 0;
    int col = 0;
    
    // check top-left to bottom-right from top-left point
    if (dropRow >= dropCol) {
      row = 0;
      col = dropRow - dropCol;
    }
    else {
      row = dropCol - dropRow;
      col = 0;
    }
    while (row < Config.NUM_ROW && col < Config.NUM_COL &&
        row >= 0 && col >=0) {
      
      if (grid[row][col].equals(player.getPlayerName())) {
        straight++;
      }
      else {
        straight = 0;
      }
      
      if (straight == Config.WIN_NUM) {
        return true;
      }
      ++row;
      ++col;
    }
    
    // check top-right to bottom-left from top-left point
    straight = 0;
    if (dropRow + dropCol < Config.NUM_COL) {
      row = 0;
      col = dropRow + dropCol;
    }
    else {
      row = dropCol + dropRow - (Config.NUM_COL - 1);
      col = Config.NUM_COL - 1;
    }
    while (row < Config.NUM_ROW && col < Config.NUM_COL &&
        row >= 0 && col >=0) {
      
      if (grid[row][col].equals(player.getPlayerName())) {
        straight++;
      }
      else {
        straight = 0;
      }
      
      if (straight == Config.WIN_NUM) {
        return true;
      }
      ++row;
      --col;
    }
    return false;
  }

  private boolean checkHorizontal(Player player, Coord drop) {
    int straight = 0;
    int dropRow = drop.getRow();
    
    for (int col = 0; col < Config.NUM_COL; col++) {
      if (grid[dropRow][col].equals(player.getPlayerName())) {
        straight++;
      }
      else {
        straight = 0;
      }
      
      if (straight == Config.WIN_NUM) {
        return true;
      }
    }
    
    return false;
  }

  private boolean checkVertical(Player player, Coord drop) {
    int straight = 0;
    int dropCol = drop.getCol();
    
    for (int row = 0; row < Config.NUM_ROW; row++) {
      if (grid[row][dropCol].equals(player.getPlayerName())) {
        straight++;
      }
      else {
        straight = 0;
      }
      
      if (straight == Config.WIN_NUM) {
        return true;
      }
    }
    
    return false;
  }
  
}
