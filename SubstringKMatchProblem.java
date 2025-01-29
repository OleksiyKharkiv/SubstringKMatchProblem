import java.io.*;
import java.util.*;

public class SubstringKMatchProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));

        // Read input data
        String[] firstLine = br.readLine().split(" ");
        int q = Integer.parseInt(firstLine[1]); // Number of queries

        String s = br.readLine();

        // Process each query
        for (int i = 0; i < q; i++) {
            String[] query = br.readLine().split(" ");
            int l = Integer.parseInt(query[0]) - 1; // Adjust to 0-based indexing
            int r = Integer.parseInt(query[1]);     // Right boundary (exclusive)
            int k = Integer.parseInt(query[2]) - 1; // Adjust to 0-based indexing

            // Extract the substring S[l..r-1]
            String substring = s.substring(l, r);
            System.out.println("Query " + (i + 1) + ": Substring = " + substring);

            // Create lists for the indices of 'A' and 'B' in the substring
            List<Integer> aPositions = new ArrayList<>();
            List<Integer> bPositions = new ArrayList<>();
            for (int j = 0; j < substring.length(); j++) {
                if (substring.charAt(j) == 'A') {
                    aPositions.add(j);
                } else {
                    bPositions.add(j);
                }
            }

            // Find the k-th character in the substring
            char targetChar = substring.charAt(k);

            // Find the x-th occurrence of targetChar in the substring
            int x = -1;
            if (targetChar == 'A') {
                x = aPositions.indexOf(k);
            } else if (targetChar == 'B') {
                x = bPositions.indexOf(k);
            }

            // Find the corresponding position in the other list
            int match = -1;
            if (targetChar == 'A') {
                if (x < bPositions.size()) {
                    match = bPositions.get(x) + 1; // Adjust to 1-based indexing
                }
            } else if (targetChar == 'B') {
                if (x < aPositions.size()) {
                    match = aPositions.get(x) + 1; // Adjust to 1-based indexing
                }
            }
            bw.write(match + "\n");
        }

        br.close();
        bw.close();
    }
}