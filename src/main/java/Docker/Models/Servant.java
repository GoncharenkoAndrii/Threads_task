package Docker.Models;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;

public class Servant extends TimerTask{
    private static final Logger LOG = LogManager.getLogger();
    private Port port = Port.getPort();

    @Override
    public void run() {
        LOG.info("servant started");
        port.checkPort();
        LOG.info("servant ended");
    }
}
