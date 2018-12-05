package ManualSynch;

public class Dock {
    private static final Ship[] shipsInDocks = new Ship[5];

    public static int checkDockStatus() {
        int status = -1;
        for (int i=0;i<shipsInDocks.length;i++){
            if (shipsInDocks[i] == null){
                status = i;
                return status;
            }
        }
        return status;
    }
    public static void putShip(Ship ship){
        System.out.println(" ManualSynch.Ship " + ship.name + " come to dock number " + ship.port);
        shipsInDocks[ship.port] = ship ;
    }
    public static void clearDock(int dockNum){
        System.out.println(" ManualSynch.Ship " + shipsInDocks[dockNum].name + " left dock number " + shipsInDocks[dockNum].port);
        shipsInDocks[dockNum].port = -1;
        shipsInDocks[dockNum] = null;

    }
}
