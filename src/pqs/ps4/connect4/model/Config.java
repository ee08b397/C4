package pqs.ps4.connect4.model;

import java.awt.Color;

/**
 * All configurations of gmae. 
 * 
 * @author Songxiao Zhang
 *
 */
public class Config {
  /**
   * Width of view window size. 
   */
  public final static int WINDOW_WIDTH = 600;
  /**
   * Height of view window size. 
   */
  public final static int WINDOW_HEIGHT = 400;
  
  /**
   * Number of columns in grid. 
   */
  public final static int NUM_COL = 7;
  /**
   * Number of rows in grid. 
   */
  public final static int NUM_ROW = 6;
  
  /**
   * Connect how many discs straight to win. 
   */
  public final static int WIN_NUM = 4;
  
  /**
   * Player type: primary to control windows, normal player, computer player
   * or just watch play. 
   */
  public enum PLAYERTYPE {
    PRIMARY, PLAY, COMPUTER, WATCH;
  }
  
  /**
   * Game mode: 1 to 1, or 1 to computer. 
   */
  public enum PLAYMODE {
    SINGLE, MULTI;
  }
  
  /**
   * Game status: one player wins, game draw, keep going. 
   */
  public enum PLAYRESULT {
    WIN, DRAW, GO;
  }
  
  /**
   * Disc color for players in view. 
   */
  public enum COLOR {
    YELLOW (Color.YELLOW), BLUE(Color.BLUE), RED (Color.RED);
    
    private final Color color;
    
    private COLOR(Color color) { 
      this.color=color;
    }
    
    public static Color get(String name){
      for(COLOR a : COLOR.values()) {
         if(a.toString().equals(name))
            return a.color; 
      }
      return null;
   }
  }

}
