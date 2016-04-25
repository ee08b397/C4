package pqs.ps4.connect4.modeltest;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import pqs.ps4.connect4.model.Config;
import pqs.ps4.connect4.model.Player;

public class PlayerTest {

  private Player player;
  
  @Before
  public void setUp() throws Exception {
    player = new Player("A", Config.PLAYERTYPE.PRIMARY, Config
        .COLOR.YELLOW);
  }

  @Test
  public void testGetPlayerName() {
    assertEquals(player.getPlayerName(), "A");
  }
  
  @Test
  public void testGetPlayerType() {
    assertEquals(player.getPlayerType(), Config.PLAYERTYPE.PRIMARY);
  }
  
  @Test
  public void testGetColor() {
    assertEquals(player.getColor(), Color.YELLOW);
  }

}
