import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FlashSaleInventoryManager {

    private HashMap<String, AtomicInteger> stock = new HashMap<>();
    private HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int quantity) {
        stock.put(productId, new AtomicInteger(quantity));
        waitingList.put(productId, new LinkedList<>());
    }

    public String checkStock(String productId) {
        return stock.get(productId).get() + " units available";
    }

    public synchronized String purchaseItem(String productId, int userId) {

        AtomicInteger s = stock.get(productId);

        if (s.get() > 0) {
            int remaining = s.decrementAndGet();
            return "Success, remaining: " + remaining;
        }

        Queue<Integer> queue = waitingList.get(productId);
        queue.add(userId);
        return "Added to waiting list, position #" + queue.size();
    }

    public static void main(String[] args) {

        FlashSaleInventoryManager manager = new FlashSaleInventoryManager();

        manager.addProduct("IPHONE15", 2);

        System.out.println(manager.purchaseItem("IPHONE15", 1));
        System.out.println(manager.purchaseItem("IPHONE15", 2));
        System.out.println(manager.purchaseItem("IPHONE15", 3));
    }
}