package data_manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataAnalyzer {
    private List<File> files;
    private List<String> results = new ArrayList<String>();

    public DataAnalyzer(List<File> files) {
        this.files = files;
    }

    public void readFile() {
        try {
            for (File f : files) {

                File fileToRead = new File(f.toString());
                Scanner reader = new Scanner(fileToRead);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (line.split(" ")[0].toUpperCase().equals("ALTER")) {
                        String tableName = setTableName(line);
                        if (!tableName.matches(".*dw[0-9][0-9].*")) {
                            String columnName = setColumnName(line, tableName);

                            if(!columnName.equals(""))
                                results.add(tableName + "." + columnName);
                        }
                    }
                }
                reader.close();
            }
            FileWriter.writeToFile(results);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String setTableName(String query) {

        Matcher matcher = Pattern.compile("TABLE\\s*(IF EXISTS)?\\s*\\w+").matcher(query);
        if (matcher.find()) {

            String[] matches = matcher.group().split(" ");
            return matches[matches.length - 1];
        }
        return null;

    }

    public String setColumnName(String query, String tableName) {

        Matcher matcher = Pattern.compile(tableName +"\\s.*(ADD|DROP|CHANGE|MODIFY|RENAME|ALTER|IF EXISTS|IF NOT EXISTS|COLUMN)\\s\\w+").matcher(query);
        if (matcher.find()) {
            String[] matches = matcher.group().split(" ");
            System.out.println(matches[matches.length - 1]);
            return matches[matches.length - 1];
        }
        return "";
    }
}
