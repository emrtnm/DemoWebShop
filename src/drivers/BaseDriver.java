package drivers;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDriver {
    public static Logger logger;

    static {
        logger = Logger.getLogger("");
        logger.setLevel(Level.SEVERE);
    }
}
