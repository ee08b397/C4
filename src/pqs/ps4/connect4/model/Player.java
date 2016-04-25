package pqs.ps4.connect4.model;

import java.awt.Color;

/**
 * The Connect Four game player that plays on grid.  
 * playerId, playerType color are not supposed to be changed, so final and no 
 * setter for them. A player can only be removed from list, not updated. 
 * <p>
 * Use default Equals(), toString(), hashCode() is good enough.  
 * 
 * @author Songxiao Zhang
 *
 */
public class Player {
  
  /**
   * Player name that will distinguish itself in grid.  
   */
  private final String playerName;
  
  /**
   * Player type that will distinguish itself or computer. 
   */
  private final Config.PLAYERTYPE playerType;
  
  /**
   * Player color that will distinguish itself in view.  
   */
  private final Config.COLOR color;
  
  /**
   * Player constructor. 
   * 
   * @param playerName    Player name that will distinguish itself in grid.  
   * @param playerType    Player type that will distinguish itself or computer.
   * @param color         Player color that will distinguish itself in view.  
   */
  public Player(String playerName, Config.PLAYERTYPE playerType,
      Config.COLOR color) {
    
    this.playerName = playerName;
    this.playerType = playerType;
    this.color = color;
  }

  public String getPlayerName() {
    return playerName;
  }

  public Config.PLAYERTYPE getPlayerType() {
    return playerType;
  }

  public Color getColor() {
    return Config.COLOR.get(color.toString());
  }
}
