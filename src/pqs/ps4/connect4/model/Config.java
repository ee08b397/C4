package pqs.ps4.connect4.model;

import java.awt.Color;

public class Config {
  public final static int WINDOW_WIDTH = 600;
  public final static int WINDOW_HEIGHT = 400;
  
  public final static int NUM_COL = 7;
  public final static int NUM_ROW = 6;
  
  public final static int WIN_NUM = 4;
  
  public enum PLAYERTYPE {
    PRIMARY, PLAY, COMPUTER, WATCH;
  }
  
  public enum PLAYMODE {
    SINGLE, MULTI;
  }
  
  public enum PLAYRESULT {
    WIN, DRAW, GO;
  }
  
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
