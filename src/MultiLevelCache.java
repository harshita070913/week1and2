import java.util.*;

public class MultiLevelCache {

    LinkedHashMap<String, String> L1 = new LinkedHashMap<>(10000, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return size() > 10000;
        }
    };

    HashMap<String, String> L2 = new HashMap<>();

    HashMap<String, Integer> accessCount = new HashMap<>();

    public String getVideo(String id) {

        if (L1.containsKey(id)) {
            return "L1 HIT";
        }

        if (L2.containsKey(id)) {

            accessCount.put(id, accessCount.getOrDefault(id, 0) + 1);

            if (accessCount.get(id) > 3)
                L1.put(id, L2.get(id));

            return "L2 HIT";
        }

        String data = "VideoData_" + id;

        L2.put(id, data);
        accessCount.put(id, 1);

        return "DB HIT";
    }

    public static void main(String[] args) {

        MultiLevelCache cache = new MultiLevelCache();

        System.out.println(cache.getVideo("video1"));
        System.out.println(cache.getVideo("video1"));
        System.out.println(cache.getVideo("video1"));
    }
}