import java.util.*;

class TokenBucket {
    int tokens;
    int maxTokens;
    long lastRefill;

    TokenBucket(int maxTokens) {
        this.tokens = maxTokens;
        this.maxTokens = maxTokens;
        this.lastRefill = System.currentTimeMillis();
    }
}

public class TokenBucketRateLimiter {

    private HashMap<String, TokenBucket> clients = new HashMap<>();
    private int limit = 1000;

    public boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(limit));

        TokenBucket bucket = clients.get(clientId);

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter();

        System.out.println(limiter.checkRateLimit("abc"));
        System.out.println(limiter.checkRateLimit("abc"));
    }
}