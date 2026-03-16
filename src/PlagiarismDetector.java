import java.util.*;

public class PlagiarismDetector {

    private HashMap<String, Set<String>> ngramIndex = new HashMap<>();
    private int N = 5;

    public void indexDocument(String docId, String text) {

        String[] words = text.split(" ");

        for (int i = 0; i <= words.length - N; i++) {

            StringBuilder sb = new StringBuilder();

            for (int j = i; j < i + N; j++) {
                sb.append(words[j]).append(" ");
            }

            String ngram = sb.toString().trim();

            ngramIndex.computeIfAbsent(ngram, k -> new HashSet<>()).add(docId);
        }
    }

    public int findMatches(String text) {

        String[] words = text.split(" ");
        int matches = 0;

        for (int i = 0; i <= words.length - N; i++) {

            StringBuilder sb = new StringBuilder();

            for (int j = i; j < i + N; j++) {
                sb.append(words[j]).append(" ");
            }

            String ngram = sb.toString().trim();

            if (ngramIndex.containsKey(ngram))
                matches++;
        }

        return matches;
    }

    public static void main(String[] args) {

        PlagiarismDetector detector = new PlagiarismDetector();

        detector.indexDocument("doc1", "the quick brown fox jumps over the lazy dog");

        int matches = detector.findMatches("quick brown fox jumps over the");

        System.out.println("Matching ngrams: " + matches);
    }
}