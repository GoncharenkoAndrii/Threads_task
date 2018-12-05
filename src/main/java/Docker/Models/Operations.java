package Docker.Models;

import java.util.Random;

public enum Operations {
    LOAD, UNLOAD, LOADthenUNLOAD;

    public static Operations generateOperation() {
        Random randomIndex = new Random();
        Integer oppIndex = randomIndex.nextInt(3);
        switch (oppIndex) {
            case 0:
                return LOAD;
            case 1:
                return UNLOAD;
            case 2:
                return LOADthenUNLOAD;
            default:
                return LOAD;
        }
    }
}
