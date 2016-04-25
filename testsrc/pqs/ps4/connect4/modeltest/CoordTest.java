package pqs.ps4.connect4.modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pqs.ps4.connect4.model.Config;
import pqs.ps4.connect4.model.Coord;

public class CoordTest {

  private Coord coord;

  @Test
  public void testCoord_valid() {
    coord = new Coord(0, 0);
    assertTrue(true);
  }

  @Test
  public void testCoord_invalid() {
    boolean eeceptionThrown = false;
    try {
      coord = new Coord(0, Config.NUM_COL);
    }
    catch (IllegalArgumentException ignored) {
      eeceptionThrown = true;
    }
    assertTrue(eeceptionThrown);
  }
  
  @Test
  public void testGetRow() {
    coord = new Coord(0, 0);
    assertEquals(coord.getRow(), 0);
  }
  
  @Test
  public void testGetCol() {
    coord = new Coord(0, 0);
    assertEquals(coord.getRow(), 0);
  }
  
  @Test
  public void testToString() {
    coord = new Coord(0, 0);
    assertEquals(coord.toString(), "[0,0]");
  }
  
}
