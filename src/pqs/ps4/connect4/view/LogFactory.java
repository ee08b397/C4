package pqs.ps4.connect4.view;

import java.util.logging.FileHandler;

import pqs.ps4.connect4.model.Model;

public class LogFactory {

    public Log getLog(Model model, FileHandler handler) {
      if (model == null) {
        return null;
      }
      if (handler != null) {
        return new Log(model, handler);
      } else if (handler == null) {
        return new Log(model, null);
      }
      return null;
    }
}
