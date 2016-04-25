package pqs.ps4.connect4.model;


/**
 * Encapsulate disc coordinate in grid: row and column. 
 * <p>
 * Use default Equals(), hashCode() is good enough. 
 * 
 * @author Songxiao Zhang
 *
 */
public class Coord {
  
  /**
   * Row of disc coordinate in grid.
   */
  private final int row;
  
  /**
   * Column of disc coordinate in grid.
   */
  private final int col;
  
  /**
   * Constructor of coordiate.  
   * 
   * @param row                      Row of disc coordinate in grid.
   * @param col                      Column of disc coordinate in grid.
   * @throws IllegalStateException   If drops off grid
   */
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
  
  @Override
  public String toString() {
    return String.format("[%d,%d]", this.row, this.col);
  }
}
