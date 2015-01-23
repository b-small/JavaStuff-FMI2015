package wordTypeCount;
// Program counts the number of occurrences of each word in a string

import java.io.*;
import java.util.*;
import javax.swing.*;

/*
 * @author bas
 */
public class WordTypeCount {

    private final Map<String, Integer> map;
    private Scanner scanner;
    private static File current;
    private static File outputFile;

    public WordTypeCount() throws IOException {
        map = new HashMap<>();
        selectFiles();
        createMap();
        displayMap();
    }

    private void selectFiles() {

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setApproveButtonText("Select file to read");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
        current = fileChooser.getSelectedFile();

        fileChooser.setApproveButtonText("Select output file");
        result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
        outputFile = fileChooser.getSelectedFile();
    }

    private void createMap() {

        String input = getFileContent(current);

        System.out.println(input);
        StringTokenizer tokenizer = new StringTokenizer(input);

        // processing input text 
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken().toLowerCase();

            if (map.containsKey(word)) {
                int count = map.get(word);
                map.put(word, count++);
            } else {
                map.put(word, 1);
            }
        }
    }

    private void displayMap() throws IOException {
        Set<String> keys = map.keySet();

        TreeSet<String> sortedKeys = new TreeSet<>(keys);
        StringBuilder writer = new StringBuilder();
        writer.append(String.format("Map contains:\nKey\tValue\n"));

        // generate output for each key in map
        for (String key : sortedKeys) {
            writer.append(String.format("%s\t%s\n", key, map.get(key)));
        }

        /* with a functional operation -> 2secs slower
         sortedKeys.stream().forEach((key) -> {
         System.out.printf("%-10s%10s\n", key, map.get(key));
         });
         */
        writer.append(String.format("\nsize:%d\nisEmpty:%b\n", map.size(),
                map.isEmpty()));

        writeToFile(writer.toString(), outputFile);

        JTextArea txaInput = new JTextArea();
        txaInput.setText(getFileContent(current));
        JScrollPane jsp1 = new JScrollPane(txaInput);
        JOptionPane.showMessageDialog(null, jsp1);

        JTextArea txaOutput = new JTextArea();
        txaOutput.setText(getFileContent(outputFile));
        JScrollPane jsp = new JScrollPane(txaOutput);
        JOptionPane.showMessageDialog(null, jsp);

    }

    public static void writeToFile(String content, File fileName) {
        try (Formatter formatter = new Formatter(fileName)) {
            formatter.format("%s", content);

        } catch (FileNotFoundException ex) {
            System.err.printf("File %s was not found!", fileName.getName());
        }
    }

    public static String getFileContent(File fileName) {
        StringBuilder builder = new StringBuilder();

        try (Scanner scanner = new Scanner(fileName)) {
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
                builder.append("\n");
            }
        } catch (FileNotFoundException ex) {
            System.err.printf("File %s was not found!\n", fileName.getName());
        }
        return builder.toString();
    }

    public static void main(String args[]) throws IOException {
          WordTypeCount wordTypeCount = new WordTypeCount();
    }
}
