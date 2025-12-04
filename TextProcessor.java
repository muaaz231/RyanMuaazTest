//Muaaz Aslam, Ryan Sharma, 12/4/25

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.time.*;

public class TextProcessor {

    // Utility method to simulate heavy processing on a line
    private static String processLine(String line) {
        // Capitalize every word (slow due to immutability)
        String[] words = line.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (!w.isEmpty()) {
                sb.append(Character.toUpperCase(w.charAt(0)))
                  .append(w.substring(1))
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }

    // Process one file, single-threaded
    private static void processFile(String inputFile, String outputFile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFile));
        List<String> processed = new ArrayList<>();

        for (String line : lines) {
            processed.add(processLine(line));
        }

        Files.write(Paths.get(outputFile), processed);
    }

    public static void main(String[] args) {
        try {
            Instant start = Instant.now();

            System.out.println("Starting single-threaded processing...");

            processFile("book1.txt", "book1_output_single.txt");
            processFile("book2.txt", "book2_output_single.txt");

            Instant end = Instant.now();
            long ms = Duration.between(start, end).toMillis();

            System.out.println("Single-threaded processing complete.");
            System.out.println("Total time: " + ms + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

