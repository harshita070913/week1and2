import java.util.*;

public class UsernameChecker {

    private HashMap<String, Integer> usernameToUserId = new HashMap<>();
    private HashMap<String, Integer> attemptFrequency = new HashMap<>();

    public boolean checkAvailability(String username) {
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);
        return !usernameToUserId.containsKey(username);
    }

    public void registerUser(String username, int userId) {
        usernameToUserId.put(username, userId);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;
            if (!usernameToUserId.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        return suggestions;
    }

    public String getMostAttempted() {
        String maxUser = null;
        int max = 0;

        for (String user : attemptFrequency.keySet()) {
            if (attemptFrequency.get(user) > max) {
                max = attemptFrequency.get(user);
                maxUser = user;
            }
        }

        return maxUser;
    }

    public static void main(String[] args) {
        UsernameChecker checker = new UsernameChecker();

        checker.registerUser("john_doe", 1);

        System.out.println(checker.checkAvailability("john_doe"));
        System.out.println(checker.checkAvailability("jane_smith"));
        System.out.println(checker.suggestAlternatives("john_doe"));
    }
}
