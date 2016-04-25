package pqs.ps4.connect4.model;

import java.util.Random;

/**
 * The Connect Four game grid under the hood. It records each player dropped
 * discs and check if one player win or game draw. It also set computer player
 * next move and check if there is a next winning move, if so take it. 
 * <p>
 * Use default Equals(), hashCode() as default is good.  
 * 
 * @author Songxiao Zhang
 *
 */
public class Grid {
  
  /**
   * The Connect Four game grid under the hood in singleton. It is a 2D String
   * array. 
   */
  private final static Grid instance = new Grid();
  private String[][] grid;
  
  private Grid() {
    this.grid = new String[Config.NUM_ROW][Config.NUM_COL];
    initialize();
  }

  /**
   * Singleton Grid, get its instance
   * 
   * @return    Grid singleton instance
   */
  public static Grid getInstance() {
    return instance;
  }
  
  /**
   * Player is trying to drop a disc to a column. 
   * 
   * @param player    game player wants to drop a disc
   * @param col       the column player wants to drop
   * @return          Returns the row number if column not filled. Otherwise, 
   *                  returns -1. 
   */
  public int dropDisc(Player player, int col) {
    for (int row = Config.NUM_ROW - 1; row >= 0; row--) {
      if (this.grid[row][col].equals("")) {
        this.grid[row][col] = player.getPlayerName();
        return row;
      }
    }
    return -1;
  }

  /**
   * initialize empty grid. 
   */
  public void initialize() {
    for (int row = 0; row < this.grid.length; row++) {
      for (int col = 0; col < this.grid[row].length; col++) {
        this.grid[row][col] = "";
      }
    }
  }

  /**
   * Computer player drops disc. If no winning move, randomly picks an unfilled
   * column. 
   * 
   * @param computer    computer player
   * @return            drop position
   */
  public Coord dropDiscComputer(Player computer) {
    Coord winDrop = checkWinDrop(computer);
    if (winDrop == null) {
      Random random = new Random();
      int row = -1;
      int randCol = random.nextInt(Config.NUM_COL);
      
      // keep trying until an unfilled column is found
      while (row == -1) {
        randCol = random.nextInt(Config.NUM_COL);
        row = dropDisc(computer, randCol);
      }
      return new Coord(row, randCol);
    }
    return winDrop;
  }

  /**
   * Check winning drop. 
   * 
   * @param player    game player if win next
   * @return          win drop coordinate
   */
  private Coord checkWinDrop(Player player) {
    for (int row = 0; row < Config.NUM_ROW; row++) {
      for (int col = 0; col < Config.NUM_COL; col++) {
        if (this.grid[row][col].equals("") && checkAllConditions(player, new 
            Coord(row, col)) == Config.PLAYRESULT.WIN) {
          
          return new Coord(row, col);
        }
      }
    } 
    return null;
  }
  
  /**
   * Check player win/draw/keep going
   * 
   * @param player    game player
   * @param drop      drop coordinate
   * @return          return current player status
   */
  public Config.PLAYRESULT checkAllConditions(Player player, Coord drop) {
    if (checkDraw()) {
      return Config.PLAYRESULT.DRAW;
    }
    if (checkVertical(player, drop) || checkHorizontal(player, drop) ||
        checkDiagonal(player, drop)) {
      
      return Config.PLAYRESULT.WIN;
    }
    return Config.PLAYRESULT.GO;
  }
  
  /**
   * Check if game draw by looking for empty cell. 
   * 
   * @return        Return true if game draw, otherwise, return false. 
   */
  private boolean checkDraw() {
    int emptySlotNum = 0;
    for (int row = 0; row < Config.NUM_ROW; row++) {
      for (int col = 0; col < Config.NUM_COL; col++) {
        if (this.grid[row][col].equals("")) {
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

  /**
   * Check if player win by connecting diagonally. 
   * 
   * @param player    game player dropped a disc
   * @param drop      drop coordinate
   * @return          Return true if player wins, otherwise, return false. 
   */
  private boolean checkDiagonal(Player player, Coord drop) {
    int straight = 0;
    int dropRow = drop.getRow();
    int dropCol = drop.getCol();
    int row = 0;
    int col = 0;
    
    // check top-left to bottom-right from top-left point
    if (dropRow >= dropCol) {
      row = dropRow - dropCol;
      col = 0;
    }
    else {
      row = 0;
      col = dropCol - dropRow;
    }
    while (row < Config.NUM_ROW && col < Config.NUM_COL &&
        row >= 0 && col >=0) {
      
      if (this.grid[row][col].equals(player.getPlayerName())) {
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
      
      if (this.grid[row][col].equals(player.getPlayerName())) {
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

  /**
   * Check if player win by connecting horizontally. 
   * 
   * @param player    game player dropped a disc
   * @param drop      drop coordinate
   * @return          Return true if player wins, otherwise, return false. 
   */
  private boolean checkHorizontal(Player player, Coord drop) {
    int straight = 0;
    int dropRow = drop.getRow();
    
    for (int col = 0; col < Config.NUM_COL; col++) {
      if (this.grid[dropRow][col].equals(player.getPlayerName())) {
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

  /**
   * Check if player win by connecting vertically. 
   * 
   * @param player    game player dropped a disc
   * @param drop      drop coordinate
   * @return          Return true if player wins, otherwise, return false. 
   */
  private boolean checkVertical(Player player, Coord drop) {
    int straight = 0;
    int dropCol = drop.getCol();
    
    for (int row = 0; row < Config.NUM_ROW; row++) {
      if (this.grid[row][dropCol].equals(player.getPlayerName())) {
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
  
  @Override
  public String toString() {
    String out = "";
    
    for (int row = 0; row < Config.NUM_ROW; row++) {
      out += "Row " + row + " |  ";
      for (int col = 0; col < Config.NUM_COL; col++) {
        out += this.grid[row][col].toString() + " ";
      }
      out += "\n";
    }
    
    return out;
  }
  
}
