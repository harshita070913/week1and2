import java.util.*;

public class AutocompleteSystem {

    private HashMap<String, Integer> frequency = new HashMap<>();

    public void addQuery(String query) {
        frequency.put(query, frequency.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {

        PriorityQueue<String> pq = new PriorityQueue<>(
                (a, b) -> frequency.get(b) - frequency.get(a));

        for (String q : frequency.keySet()) {
            if (q.startsWith(prefix))
                pq.add(q);
        }

        List<String> result = new ArrayList<>();

        int k = 10;
        while (!pq.isEmpty() && k-- > 0)
            result.add(pq.poll());

        return result;
    }

    public static void main(String[] args) {

        AutocompleteSystem system = new AutocompleteSystem();

        system.addQuery("java tutorial");
        system.addQuery("javascript guide");
        system.addQuery("java download");

        System.out.println(system.search("jav"));
    }
}