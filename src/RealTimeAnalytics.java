import java.util.*;

public class RealTimeAnalytics {

    private HashMap<String, Integer> pageViews = new HashMap<>();
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    private HashMap<String, Integer> trafficSource = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.computeIfAbsent(url, k -> new HashSet<>()).add(userId);

        trafficSource.put(source, trafficSource.getOrDefault(source, 0) + 1);
    }

    public void printDashboard() {

        System.out.println("Top Pages:");
        for (String page : pageViews.keySet()) {
            System.out.println(page + " views: " + pageViews.get(page)
                    + " unique: " + uniqueVisitors.get(page).size());
        }

        System.out.println("\nTraffic Sources:");
        System.out.println(trafficSource);
    }

    public static void main(String[] args) {

        RealTimeAnalytics analytics = new RealTimeAnalytics();

        analytics.processEvent("/news", "u1", "google");
        analytics.processEvent("/news", "u2", "facebook");
        analytics.processEvent("/sports", "u1", "direct");

        analytics.printDashboard();
    }
}