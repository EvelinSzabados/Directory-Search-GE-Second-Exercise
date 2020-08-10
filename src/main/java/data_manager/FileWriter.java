package data_manager;

import java.io.*;
import java.util.List;

public class FileWriter {
    private static File resultFile = new File("result.txt");

    public static void writeToFile(List<String> results) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(resultFile);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        for (String result : results) {
            writer.write(result);
            writer.newLine();
        }

        writer.close();
        System.out.println("Modified table and column names were written out to ./results.txt.");
    }
}
