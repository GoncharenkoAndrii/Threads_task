package ManualSynch;

public class Administrator {
    private int dockNum;

    public Administrator(int dN) {
        this.dockNum = dN;
    }

    public void clearTheDockWhenTime(final long time) {
        final Thread cleaner = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Dock.class) {
                    Dock.clearDock(dockNum);
                    Dock.class.notify();
                }
            }
        });
        cleaner.start();

    }
}
