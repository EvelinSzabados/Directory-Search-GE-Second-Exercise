package data_manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataAnalyzer {
    private List<File> files;
    private List<String> results = new ArrayList<String>();
    List<String> keywordsBeforeColumnName = new ArrayList<String>(Arrays.asList("COLUMN", "ADD", "ALTER", "DROP", "MODIFY",
            "CHANGE", "RENAME ", "IF", "EXISTS", "NOT"));


    public DataAnalyzer(List<File> files) {
        this.files = files;
    }
    public void readFile() {
        try {
            for (File f : files) {

                File fileToRead = new File(f.toString());
                Scanner reader = new Scanner(fileToRead);
                while (reader.hasNextLine()) {
                    String[] line = reader.nextLine().split(" ");
                    //Checks if query starts with ALTER
                    if (line[0].toUpperCase().equals("ALTER")) {
                        String tableName = setTableName(line);
                        if (!tableName.matches(".*dw[0-9][0-9].*")) {
                            String columnName = setColumnName(line, tableName);
                            //If setColumnName() method returns "", that means only table was modified,
                            // no columns were affected. For example: ALTER TABLE table_name ORDER BY value;
                            if(!columnName.equals(""))
                                results.add(tableName + "." + columnName);
                            //This method writes the results into txt file in tableName.columnName format.

                        }

                    }
                }
                reader.close();
            }
            FileWriter.writeToFile(results);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String setTableName(String[] query) {
        int tableNameIndex = 0;
        // F.e: ALTER [ONLINE] [IGNORE] TABLE [IF EXISTS] tbl_name
        //Checks if there are any keywords between ALTER and TABLE
        if (query[1].toUpperCase().equals("TABLE")) {
            if (!query[2].toUpperCase().equals("IF EXISTS")) {
                tableNameIndex = 2;
            } else {
                tableNameIndex = 3;
            }
            // Checks if there are any keywords between TABLE and tbl_Name
        } else if (query[2].toUpperCase().equals("TABLE")) {
            if (!query[3].toUpperCase().equals("IF EXISTS")) {
                tableNameIndex = 3;
            } else {
                tableNameIndex = 4;
            }

        }
        return query[tableNameIndex];

    }
    public String setColumnName(String[] query, String tableName) {

        //The first ALTER statement is not included in the iteration
        for (int i = 2; i < query.length; i++) {
            // columnName found = If the word before the current word (index i-1) IS a SQL keyword,
            // and the current word is NOT a keyword, also NOT the table's name

            if (keywordsBeforeColumnName.contains(query[i - 1]) &&
                    !keywordsBeforeColumnName.contains(query[i])) {
                if (!query[i].equals(tableName)) {
                    return query[i];
                }
            }
        }
        return "";
    }
}
