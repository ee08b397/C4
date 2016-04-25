package pqs.ps4.connect4.modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pqs.ps4.connect4.model.Config;
import pqs.ps4.connect4.model.Coord;
import pqs.ps4.connect4.model.Grid;
import pqs.ps4.connect4.model.Player;

public class GridTest {

  private Grid grid;
  private Player player1;
  private Player player2;
  private Player computer;
  
  @Before
  public void setUp() throws Exception {
    this.grid = Grid.getInstance();
    this.player1 = new Player("A", Config.PLAYERTYPE.PRIMARY, Config
        .COLOR.YELLOW);
    this.player2 = new Player("B", Config.PLAYERTYPE.PLAY, Config
        .COLOR.RED);
    this.computer = new Player("C", Config.PLAYERTYPE.COMPUTER, Config
        .COLOR.BLUE);
    this.grid.initialize();
  }

  @Test
  public void testDropDisc() {
    assertEquals(this.grid.dropDisc(player1, 0), Config.NUM_ROW - 1);
    
    // test invalid case where column 0 is filled
    for (int i = 0; i < Config.NUM_ROW; i++) {
      this.grid.dropDisc(player1, 0);
    }
    assertEquals(this.grid.dropDisc(player1, 0), -1);
  }
  
  @Test
  public void testDropDiscComputer() {
    for (int i = 0; i < Config.NUM_COL * Config.NUM_ROW + 2; i++) {
      this.grid.dropDiscComputer(computer);
    }
    assertTrue(true);
  }

  @Test
  public void testCheckAllConditions_checkDraw() {
    for (int i = 0; i < Config.NUM_COL * Config.NUM_ROW + 2; ++i) {
      if ((i & 1) == 0) {
        this.grid.dropDisc(player1, i % Config.NUM_COL);
      }
      else {
        this.grid.dropDisc(player2, i % Config.NUM_COL);
      }
    }
    assertEquals(this.grid.checkAllConditions(player1, new Coord(0,0)), 
        Config.PLAYRESULT.DRAW);
  }
  
  @Test
  public void testCheckAllCondition_checkVertical() {
    assertEquals(this.grid.checkAllConditions(player1, new Coord(0,0)), 
        Config.PLAYRESULT.GO);
    for (int i = 0; i < 4; i++) {
      this.grid.dropDisc(player1, 0);
      this.grid.dropDisc(player2, 1);
    }
    assertEquals(this.grid.checkAllConditions(player1, new Coord( 
        Config.NUM_ROW - 5, 0)), Config.PLAYRESULT.WIN);
  }
  
  @Test
    public void testCheckAllCondition_checkHorizontal() {
    assertEquals(this.grid.checkAllConditions(player1, new Coord(0,0)), 
        Config.PLAYRESULT.GO);
    for (int i = 0; i < 4; i++) {
      this.grid.dropDisc(player1, i);
      this.grid.dropDisc(player2, i);
    }
    assertEquals(this.grid.checkAllConditions(player1, new Coord( 
        Config.NUM_ROW - 1, 4)), Config.PLAYRESULT.WIN);
  }
  
  @Test
  public void testCheckAllCondition_checkDiagonal_up() {
    assertEquals(this.grid.checkAllConditions(player1, new Coord(0,0)), 
        Config.PLAYRESULT.GO);
    
    this.grid.dropDisc(player1, 0);
    
    this.grid.dropDisc(player2, 1);
    this.grid.dropDisc(player1, 1);
    
    this.grid.dropDisc(player2, 2);
    this.grid.dropDisc(player1, 3);
    this.grid.dropDisc(player2, 2);
    this.grid.dropDisc(player1, 2);
    
    this.grid.dropDisc(player2, 3);
    this.grid.dropDisc(player1, 4);
    this.grid.dropDisc(player2, 3);
    this.grid.dropDisc(player1, 3);
    
    assertEquals(this.grid.checkAllConditions(player1, new Coord( 
        Config.NUM_ROW - 4, 3)), Config.PLAYRESULT.WIN);
  }
  
  @Test
  public void testCheckAllCondition_checkDiagonal_down() {
    assertEquals(this.grid.checkAllConditions(player1, new Coord(0,0)), 
        Config.PLAYRESULT.GO);
    
    this.grid.dropDisc(player1, 3);
    
    this.grid.dropDisc(player2, 2);
    this.grid.dropDisc(player1, 2);
    
    this.grid.dropDisc(player2, 1);
    this.grid.dropDisc(player1, 0);
    this.grid.dropDisc(player2, 1);
    this.grid.dropDisc(player1, 1);
    
    this.grid.dropDisc(player2, 0);
    this.grid.dropDisc(player1, 4);
    this.grid.dropDisc(player2, 0);
    this.grid.dropDisc(player1, 0);
    
    assertEquals(this.grid.checkAllConditions(player1, new Coord( 
        2, 0)), Config.PLAYRESULT.WIN);
  }
  
  @Test
  public void testToString() {
    assertTrue(this.grid.toString() != null);
  }
  
}
