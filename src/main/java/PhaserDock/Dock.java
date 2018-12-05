package PhaserDock;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Dock implements Iterable<Goods> {
    public static final int DOCK_CAPACITY = 20;
    private Queue<Goods> goods = null;

    //constructor with default capacity
    private Dock() {
        goods = new LinkedBlockingQueue<Goods>(DOCK_CAPACITY);
    }

    private Dock(int capacity) {
        goods = new LinkedBlockingQueue<Goods>(capacity);
    }

    //Creating dock with default capacity and
    //filling it with goods
    public static Dock createDock(int capacity, List<Goods> incomingGoods) {
        Dock currentDock = new Dock(capacity);
        currentDock.goods.addAll(incomingGoods);
        return currentDock;
    }

    public static Dock createDock(int capacity) {
        Dock storage = new Dock(capacity);
        return storage;
    }

    public Goods getGood() {
        return goods.poll();
    }

    public boolean setGood(Goods someGood) {
        return goods.add(someGood);
    }

    public Iterator<Goods> iterator() {
        return goods.iterator();
    }
}
