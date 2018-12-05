package ManualSynch;

public class ShipGenerator {
    final static int TIMER = 1000;
    private int PROD_NUM = 0;
    private String nameModifier ="";

    public ShipGenerator(String name){
        this.nameModifier = name;
    }

    public void putShipToDock() {
        synchronized (Dock.class) {
            int dockNumber = Dock.checkDockStatus();

            if (dockNumber != -1) {
                Ship temp = new Ship(nameModifier+"_"+PROD_NUM,dockNumber);
                PROD_NUM++;
                Dock.putShip(temp);
                Administrator admin = new Administrator(dockNumber);
                admin.clearTheDockWhenTime(TIMER);

            } else {
                System.out.println("please wait");
                try {
                    Dock.class.wait();
                    putShipToDock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Dropped on ship-to-port connection");
                }
            }

        }
    }




}
