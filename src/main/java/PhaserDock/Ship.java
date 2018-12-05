package PhaserDock;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Phaser;

public class Ship implements Runnable {
    private Phaser phaser;
    private String name;
    private int selfCapacity;
    private Dock destinationDock;
    private Dock loadDock;

    private Queue<Goods> itemsOnShip = new ArrayDeque<>() ;

    public Ship(Phaser phaser, String name, int capacity, Dock destinationDock,Dock loadDock) {
        this.phaser = phaser;
        this.name = name;
        this.selfCapacity = capacity;
        this.destinationDock = destinationDock;
        this.loadDock = loadDock;
        // each element in phaser  have to register itself with register method;
        this.phaser.register();
    }

    public void run() {
        loadShip();
        phaser.arriveAndAwaitAdvance();
        goShip();
        phaser.arriveAndAwaitAdvance();
        unloadShip();
        phaser.arriveAndDeregister();

    }

    private void goShip() {
        try {
            Thread.sleep(500);
            System.out.println("Ship" + name + " starts");
            Thread.sleep(500);
            System.out.println("Ship" + name + " stops");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void unloadShip () {
        int size = itemsOnShip.size() ;
        System.out.println("Ship" + name + "starts to unload in Port");
        for (int i = 0; i< size; i++){
            // Take good of the ship
            Goods current = itemsOnShip.poll();
            //add it to the port
            destinationDock.setGood(current);
            // report
            System.out.println("Unloaded" + current.getRegistrationNum() + "good");
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void loadShip () {
        for (int i=0; i < selfCapacity; i++){
            Goods current = loadDock.getGood();
            //stop loading the ship if the keep is empty
            if (current == null){return;}
            itemsOnShip.add(current);
            System.out.println("Ship"+name+"have loaded good number" + current.getRegistrationNum());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
