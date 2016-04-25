package pqs.ps4.connect4.modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

import pqs.ps4.connect4.model.Config;
import pqs.ps4.connect4.model.Config.COLOR;
import pqs.ps4.connect4.model.Config.PLAYERTYPE;
import pqs.ps4.connect4.model.Config.PLAYMODE;
import pqs.ps4.connect4.model.Config.PLAYRESULT;

public class ConfigTest {

  @Test
  public void testConfig() {
    Config config = new Config();
    assertTrue(config != null);
  }
  
  @Test
  public void testWINDOW_WIDTH() {
    assertEquals(Config.WINDOW_WIDTH, (int)Config.WINDOW_WIDTH);
    assertEquals(Config.WINDOW_WIDTH, 600);
  }

  @Test
  public void testWINDOW_HEIGHT() {
    assertEquals(Config.WINDOW_HEIGHT, (int)Config.WINDOW_HEIGHT);
    assertEquals(Config.WINDOW_HEIGHT, 400);
  }
  
  @Test
  public void testNUM_COL() {
    assertEquals(Config.NUM_COL, (int)Config.NUM_COL);
    assertEquals(Config.NUM_COL, 7);
  }

  @Test
  public void testNUM_ROW() {
    assertEquals(Config.NUM_ROW, (int)Config.NUM_ROW);
    assertEquals(Config.NUM_ROW, 6);
  }

  @Test
  public void testWIN_NUM() {
    assertEquals(Config.WIN_NUM, (int)Config.WIN_NUM);
    assertEquals(Config.WIN_NUM, 4);
  }

  @Test
  public void testPLAYERTYPE() {
    assertEquals(PLAYERTYPE.PRIMARY, PLAYERTYPE.valueOf(PLAYERTYPE.PRIMARY
        .toString()));
    assertEquals(PLAYERTYPE.PLAY, PLAYERTYPE.valueOf(PLAYERTYPE.PLAY
        .toString()));
    assertEquals(PLAYERTYPE.COMPUTER, PLAYERTYPE.valueOf(PLAYERTYPE.COMPUTER
        .toString()));
    assertEquals(PLAYERTYPE.WATCH, PLAYERTYPE.valueOf(PLAYERTYPE.WATCH
        .toString()));
  }
  
  @Test
  public void testPLAYMODE() {
    assertEquals(PLAYMODE.SINGLE, PLAYMODE.valueOf(PLAYMODE.SINGLE.toString()));
    assertEquals(PLAYMODE.MULTI, PLAYMODE.valueOf(PLAYMODE.MULTI.toString()));
  }
  
  @Test
  public void testPLAYRESULT() {
    assertEquals(PLAYRESULT.WIN, PLAYRESULT.valueOf(PLAYRESULT.WIN.toString()));
    assertEquals(PLAYRESULT.DRAW, PLAYRESULT.valueOf(PLAYRESULT.DRAW
        .toString()));
    assertEquals(PLAYRESULT.GO, PLAYRESULT.valueOf(PLAYRESULT.GO.toString()));
  }
  
  @Test
  public void testCOLOR() {
    assertEquals(COLOR.YELLOW.toString(), "YELLOW");
    assertEquals(COLOR.BLUE.toString(), "BLUE");
    assertEquals(COLOR.RED.toString(), "RED");
    assertEquals(COLOR.YELLOW, COLOR.valueOf(COLOR.YELLOW.toString()));
    assertEquals(COLOR.BLUE, COLOR.valueOf(COLOR.BLUE.toString()));
    assertEquals(COLOR.RED, COLOR.valueOf(COLOR.RED.toString()));
  }
  
  @Test
  public void testGet() {
    assertEquals(COLOR.get("YELLOW"), Color.YELLOW);
    assertEquals(COLOR.get("BLUE"), Color.BLUE);
    assertEquals(COLOR.get("RED"), Color.RED);
    assertEquals(COLOR.get("YLOW"), null);
  }
}
