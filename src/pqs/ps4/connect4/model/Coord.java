package pqs.ps4.connect4.model;

/*
 * 
 */
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
  
  public String toString() {
    return String.format("[%d,%d]", this.row, this.col);
  }
}
