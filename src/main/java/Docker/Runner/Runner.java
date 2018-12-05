package Docker.Runner;

import Docker.Models.Port;
import Docker.Models.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Runner {
    private static final Logger LOG = LogManager.getLogger();
    private static final int NUMBER_OF_SHIPS = 5;

    public static void main(String[] args) {
        ArrayList<Thread> ships = new ArrayList<>();
        for(int i=0;i<NUMBER_OF_SHIPS;i++){
            Ship ship = new Ship(i,i+1);
            ships.add(new Thread(ship));
            ships.get(i).start();
            LOG.info(ship.toString());
        }
        for(int i=0;i<NUMBER_OF_SHIPS;i++){
            try {
                TimeUnit.SECONDS.timedJoin(ships.get(i),10);
            } catch (InterruptedException e) {
                LOG.error("Mistake in join()",e);
            }
        }
        Port.getTimer().cancel();
        LOG.info("Servant stopped");
    }
}
