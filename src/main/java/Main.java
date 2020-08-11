import data_manager.DataAnalyzer;
import data_manager.SearchFiles;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        SearchFiles searchFiles = new SearchFiles();
        searchFiles.getFilesInDir("/home/evelin/Codecool/Test");

        DataAnalyzer dataAnalyzer = new DataAnalyzer(searchFiles.getFiles());
        dataAnalyzer.readFile();


    }
}
