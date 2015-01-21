package recursiveGenericSelSort;

import java.io.*;
import java.util.*;
import javax.swing.*;
import static recursiveGenericSelSort.RecursiveGenericSelectionSort.*;

/**
 *
 * @author bas
 */
public class RecursiveGenericSelectionSortTest {

    public static void main(String[] args) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        Integer[] arrIntegers = new Integer[15];

        for (int i = 0; i < arrIntegers.length; i++) {
            arrIntegers[i] = random.nextInt(9) + 1;
            sb.append(arrIntegers[i]).append(" ");
        }

        selectionSort(arrIntegers, 0);
        sb.append("\n");

        for (Integer arrInteger : arrIntegers) {
            sb.append(String.format("%d", arrInteger)).append(" ");
        }

        sb.append("\n");

        Double[] arrDoubles = new Double[15];

        for (int i = 0; i < arrDoubles.length; i++) {
            arrDoubles[i] = random.nextDouble() + random.nextInt(8) + 1;
            sb.append(String.format("%.2f ", arrDoubles[i]));
        }

        selectionSort(arrDoubles, 0);

        sb.append("\n");
        for (Double arrDouble : arrDoubles) {
            sb.append(String.format("%.2f", arrDouble)).append(" ");
        }

        try {
            writeToFile(sb.toString(), "arrays.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JTextArea txaOutput = new JTextArea();
        txaOutput.setText(getFileContent("arrays.txt"));
        JScrollPane jsp = new JScrollPane(txaOutput);
        JOptionPane.showMessageDialog(null, jsp);

        // Task02 - being printed on the netbeans console 
        //after we close the JOptionPane window :)
        Box[] boxes = new Box[15];
        
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new Box<>(random.nextInt(8) + 1);
        }
       
        selectionSort(boxes, 0);
    }

    public static void writeToFile(String content, String fileName) throws IOException {
        try (Formatter formatter = new Formatter(fileName)) {
            formatter.format("%s", content);

        } catch (FileNotFoundException ex) {
            System.err.printf("File %s was not found!", fileName);
        }
    }

    public static String getFileContent(String fileName) {
        StringBuilder builder = new StringBuilder();
        
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
                builder.append("\n");
            }
        } catch (FileNotFoundException ex) {
            System.err.printf("File %s was not found!\n", fileName);
        }
        return builder.toString();
    }
}
