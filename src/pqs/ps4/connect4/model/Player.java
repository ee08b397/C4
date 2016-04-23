package pqs.ps4.connect4.model;

import java.awt.Color;

import pqs.ps4.connect4.model.Config.PLAYERTYPE;

/*
 * playerId and playerType are not supposed to be changed, so final and no 
 * setter for them. A player can only be removed from list, not updated. 
 */
public class Player {
  
  private final String playerId;
  private final String playerName;
  private final Config.PLAYERTYPE playerType;
  private final Config.COLOR color;
  
  public Player(String playerId, String playerName, 
      Config.PLAYERTYPE playerType, Config.COLOR color) {
    
    this.playerId = playerId;
    this.playerName = playerName;
    this.playerType = playerType;
    this.color = color;
  }

  public String getPlayerId() {
    return playerId;
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