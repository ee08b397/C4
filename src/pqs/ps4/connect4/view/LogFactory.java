package pqs.ps4.connect4.view;

import java.util.logging.FileHandler;

import pqs.ps4.connect4.model.Model;

/**
 * Logger factory to give ad-hoc logger. 
 * <p>
 * Use default Equals(), toString(), hashCode() is good enough. 
 * 
 * @author Songxiao Zhang
 */
public class LogFactory {

    /**
     * Get logger by setting. 
     * 
     * @param model     Current model. 
     * @param handler   Set logger by null if print to console. Otherwise save 
     *                  to file. 
     * @return          Null if model is null. The ad-hoc logger. 
     */
    public Log getLog(Model model, FileHandler handler) {
      if (model == null) {
        return null;
      }
      
      if (handler != null) {
        return new Log(model, handler);
      } 
      else {
        return new Log(model, null);
      }
    }
    
}
