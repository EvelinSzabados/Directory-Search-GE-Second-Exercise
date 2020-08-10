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
                    }
                }
                reader.close();
            }

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
}
