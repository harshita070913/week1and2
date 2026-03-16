import java.util.*;

class DNSEntry {
    String ip;
    long expiryTime;

    DNSEntry(String ip, long expiryTime) {
        this.ip = ip;
        this.expiryTime = expiryTime;
    }
}

public class DNSCache {

    private HashMap<String, DNSEntry> cache = new HashMap<>();
    private int TTL = 300;

    public String resolve(String domain) {

        long current = System.currentTimeMillis() / 1000;

        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);

            if (entry.expiryTime > current) {
                return "Cache HIT → " + entry.ip;
            }
        }

        String ip = queryDNS(domain);
        cache.put(domain, new DNSEntry(ip, current + TTL));

        return "Cache MISS → " + ip;
    }

    private String queryDNS(String domain) {
        return "172.217.14." + new Random().nextInt(255);
    }

    public static void main(String[] args) {

        DNSCache dns = new DNSCache();

        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com"));
    }
}