package data_manager;

import java.io.*;
import java.util.List;

public class FileWriter {
    private static File resultFile = new File("result.txt");

    public static void writeToFile(List<String> results) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(resultFile);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        for (String result : results) {
            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        System.out.println("Modified table and column names were written out to ./results.txt.");
    }
}
