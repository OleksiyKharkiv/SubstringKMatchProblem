import java.io.*;
import java.util.*;

public class SubstringKMatchProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        // Read input data
        String[] firstLine = br.readLine().split(" ");
        int q = Integer.parseInt(firstLine[1]); // Number of queries

        String s = br.readLine();
        StringBuilder result = new StringBuilder();

        // Process each query
        for (int i = 0; i < q; i++) {
            String[] query = br.readLine().split(" ");
            int l = Integer.parseInt(query[0]); // Left boundary (1-based indexing)
            int r = Integer.parseInt(query[1]); // Right boundary (1-based indexing)
            int k = Integer.parseInt(query[2]); // k-th character in the substring S [l…r]

            // Adjusting to 0-based indexing
            String substring = s.substring(l - 1, r); // Extract substring S [l…r] with 0-based indexing
            // Find the result for the current query
            int match = findMatch(substring, k);
            result.append(match).append("\n");
        }
        br.close();
        // Write output to file
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        bw.write(result.toString());
        bw.close();
    }

    // Function to find the k-th character match in the substring
    private static int findMatch(String substring, int k) {
        // Create lists for the indices of 'A' and 'B' in the substring
        List<Integer> aPositions = new ArrayList<>();
        List<Integer> bPositions = new ArrayList<>();

        // Populate lists for 'A' and 'B' characters in the substring
        for (int i = 0; i < substring.length(); i++) {
            if (substring.charAt(i) == 'A') {
                aPositions.add(i); // 0-based indexing
            } else {
                bPositions.add(i); // 0-based indexing
            }
        }

        // Identify the character at index k-1
        char targetChar = substring.charAt(k - 1); // Get the character at position k-1

        // We are finding the order of targetChar among 'A' or 'B'
        if (targetChar == 'A') {
            // Find its order among 'A' characters
            int orderA = aPositions.indexOf(k - 1); // Find the index in the 'A' list
            if (orderA != -1 && orderA < bPositions.size()) {
                return bPositions.get(orderA) + 1; // Return the corresponding 'B' position, adjusting for 1-based indexing
            }
        } else if (targetChar == 'B') {
            // Find its order among 'B' characters
            int orderB = bPositions.indexOf(k - 1); // Find the index in the 'B' list
            if (orderB != -1 && orderB < aPositions.size()) {
                return aPositions.get(orderB) + 1; // Return the corresponding 'A' position, adjusting for 1-based indexing
            }
        }

        return -1; // If no match is found, return -1
    }
}
