package PhaserDock;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Phaser;

public class DockAdmin {
    public static void main(String[] args) {
        // Making a collection of goods
        Goods[] goods = new Goods[10];
        for (int i = 0; i < goods.length; i++) {
            goods[i] = new Goods(i + 1);

        }
        List<Goods> listedGoods = Arrays.asList(goods);

        Dock dock1 = Dock.createDock(listedGoods.size()*10,listedGoods);
        Dock dock2 = Dock.createDock(listedGoods.size()*10,listedGoods);
        Dock dock3 = Dock.createDock(listedGoods.size()*10,listedGoods);
        Dock dock4 = Dock.createDock(listedGoods.size()*10,listedGoods);
        Dock dock5 = Dock.createDock(listedGoods.size()*10,listedGoods);

        // to synchronize movement of sequence of ships
        // we use phaser
        Phaser phaser = new Phaser();
        phaser.register();
        int currentPhase;
        //creating a fleet
        Thread firstShip = new Thread(new Ship(phaser, "Nautilus", 2, dock1, dock4));
        Thread secondShip = new Thread(new Ship(phaser, "Titanic", 2, dock2, dock5));
        Thread thirdShip = new Thread(new Ship(phaser, "Variag", 2, dock4, dock3));
        // Watching what goods are currently at dock
        printGoods("goods at dock 1", dock1);
        printGoods("goods at dock 2", dock2);
        printGoods("goods at dock 3", dock3);
        printGoods("goods at dock 4", dock4);
        printGoods("goods at dock 5", dock5);
        // go go go
        firstShip.start();
        secondShip.start();
        thirdShip.start();
        //synchronize of loading
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("loading phase" + currentPhase + " complete");
        //synchronize of unloading
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Travel phase" + currentPhase + " completed ");
        //synch last phase
        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Unloading phase" + currentPhase + "completed");
        phaser.arriveAndDeregister();
        if (phaser.isTerminated()){
            System.out.println("phases are synch");
        }
        printGoods("goods at dock 1", dock1);
        printGoods("goods at dock 2", dock2);
        printGoods("goods at dock 3", dock3);
        printGoods("goods at dock 4", dock4);
        printGoods("goods at dock 5", dock5);



    }

    public static void printGoods(String title, Dock dockStorage) {
        System.out.println(title);
        Iterator<Goods> goodsIterator = dockStorage.iterator();
        while (goodsIterator.hasNext()) {
            System.out.print(goodsIterator.next().getRegistrationNum()+" ");
        }
        System.out.println("");
    }
}
