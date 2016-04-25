package pqs.ps4.connect4.viewtest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.logging.FileHandler;

import org.junit.Test;

import pqs.ps4.connect4.model.Model;
import pqs.ps4.connect4.view.LogFactory;

public class LogFactoryTest {

  private LogFactory logFactory;
  private Model model;
  
  @Test
  public void testNullLogFactory() throws SecurityException, IOException {
    this.logFactory = new LogFactory();
    this.model = new Model();
    
    assertEquals(logFactory.getLog(null, null), null);
    assertTrue(logFactory.getLog(model, null) != null);
    assertTrue(logFactory.getLog(model, new FileHandler("log")) != null);
  }

}
