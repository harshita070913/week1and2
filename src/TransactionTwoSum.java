import java.util.*;

class Transaction {
    int id;
    int amount;

    Transaction(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}

public class TransactionTwoSum {

    public static List<int[]> findTwoSum(List<Transaction> list, int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (Transaction t : list) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                result.add(new int[]{map.get(complement).id, t.id});
            }

            map.put(t.amount, t);
        }

        return result;
    }

    public static void main(String[] args) {

        List<Transaction> list = new ArrayList<>();

        list.add(new Transaction(1, 500));
        list.add(new Transaction(2, 300));
        list.add(new Transaction(3, 200));

        List<int[]> pairs = findTwoSum(list, 500);

        for (int[] p : pairs)
            System.out.println(p[0] + " " + p[1]);
    }
}