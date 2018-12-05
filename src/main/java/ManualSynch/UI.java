package ManualSynch;

public class UI {
    public static void main(String[] args) {
        ShipGenerator g1 = new ShipGenerator("g1");
        ShipGenerator g2 = new ShipGenerator("g2");
        UI.startGeneration(g1);
        UI.startGeneration(g2);


    }
    private static void startGeneration(final ShipGenerator cur){
        Thread th = new Thread(new Runnable() {
            public void run() {
                for(int i =0; i<11; i++){
                    cur.putShipToDock();
                }
            }
        });
        th.start();
    }
}
