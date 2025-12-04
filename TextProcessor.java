import java.io.*;
import java.nio.file.*;
import java.util.concurrent.*;

public class TextProcessor {

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Void> task1 = () -> {
            processFile("book1.txt", "book1_output.txt");
            return null;
        };

        Callable<Void> task2 = () -> {
            processFile("book2.txt", "book2_output.txt");
            return null;
        };

        executor.invokeAll(java.util.List.of(task1, task2));
        executor.shutdown();

        long end = System.currentTimeMillis();
        System.out.println("Multithreaded time: " + (end - start) + " ms");
    }

    private static void processFile(String input, String output) throws Exception {
        String text = Files.readString(Path.of(input));

        // Example processing: Capitalize every word
        StringBuilder sb = new StringBuilder();
        for (String word : text.split(" ")) {
            sb.append(word.toUpperCase()).append(" ");
        }

        Files.writeString(Path.of(output), sb.toString());
    }
}
